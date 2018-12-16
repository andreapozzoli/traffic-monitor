package prog;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;


public class CentralinaStradale extends Centralina {
	private int intervalloDiTempo;
	private RilevatoreVeicoli rilevatoreVeicoli;
	private DatoTraffico datoTraffico;
	private float rapporto;
	private int intervalloMinimo;
	private String tipoStrada;
	private int idCentralinaStradale;
	private RilevatoreVelocitaS rilevatoreVelocita;
	private IGestoreCentraline centServer;

	public CentralinaStradale(int intervalloDiTempo, Posizione posizione,String tipoStrada)  {
		this.intervalloDiTempo=intervalloDiTempo;
		this.rilevatoreVeicoli=new RilevatoreVeicoli();
		this.rilevatoreVelocita=this.rilevatoreVeicoli.getRilevatoreVelocita();
		this.posizione=posizione;
		this.velocita=0;
		this.tipo="traffico nella norma";
		this.intervalloMinimo=10;
		this.tipoStrada=tipoStrada;
		this.idCentralinaStradale=0;

	}
	public void calcolaIntervallo(int numeroVeicoli) {
		float temp=((float)numeroVeicoli)/((float)this.intervalloDiTempo);
		if (temp>0.0) {
			this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);
			if (this.intervalloDiTempo<this.intervalloMinimo) {
				this.intervalloDiTempo=this.intervalloMinimo;
			}
			else if (this.intervalloDiTempo>90) {
				this.intervalloDiTempo=90;
			}
			this.rapporto=temp;
		}
		else {
			this.intervalloDiTempo=this.intervalloDiTempo*2;

		}
	}

	public void setIdCentralinaStradale(int id) {
		this.idCentralinaStradale=id;
	}

	public int getIdCentralinaStradale() {
		return this.idCentralinaStradale;
	}
	public int getIntervallo() {
		return this.intervalloDiTempo;
	}
	public float getRapporto () {
		return this.rapporto;
	}
	public int getIntervalloMinimo() {
		return this.intervalloMinimo;
	}
	public void setIntervalloMinimo(int intervalloMinimo) {
		this.intervalloMinimo=intervalloMinimo;
	}
	public String getTipoStrada() {
		return this.tipoStrada;
	}
	public void setTipoStrada(String tipoStrada) {
		this.tipoStrada=tipoStrada;
	}
	public void creaDatoTraffico() {
		if (this.velocita!=0) {
			switch (this.tipoStrada){
			case "urbana":
				if (velocita<20)
					tipo="S"+velocita+" Coda";
				else if (velocita>=20 && velocita<30)
					tipo="S"+velocita+" Traffico elevato";
				else if (velocita>=30 && velocita<40)
					tipo="S"+velocita+" Velocita lenta";
				else 
					tipo="S"+velocita+" Traffico nella norma";			
				break;

			case "extraurbana":
				if (velocita<20)
					tipo="S"+velocita+" Coda";
				else if (velocita>=20 && velocita<40)
					tipo="S"+velocita+" Traffico elevato";
				else if (velocita>=40 && velocita<50)
					tipo="S"+velocita+" Velocita lenta";
				else
					tipo="S"+velocita+" Traffico nella norma";
				break;
			case "superstrada":
				if (velocita<20)
					tipo="S"+velocita+" Coda";
				else if (velocita>=20 && velocita<50)
					tipo="S"+velocita+" Traffico elevato";
				else if (velocita>=50 && velocita<70)
					tipo="S"+velocita+" Velocita lenta";
				else
					tipo="S"+velocita+" Traffico nella norma";
				break;

			}
		}
		this.datoTraffico=new DatoTraffico(this.posizione, tipo, this.velocita);
	}
	public void inviaDatoTraffico() throws RemoteException {
		centServer.segnalaDatabaseS(this.datoTraffico);
	}
	public void calcolaVelocitaMedia(int numeroVeicoli, int somma) {
		if (numeroVeicoli!=0) {
			this.velocita=(int)(somma/numeroVeicoli);
		}
		else {
			this.velocita=0;
		}
	}

	public RilevatoreVeicoli getRilevatoreVeicoli() {
		return this.rilevatoreVeicoli;
	}

	public void run() {
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		System.setSecurityManager(cSM);

		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 12344);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		try {
			this.centServer = (IGestoreCentraline) registry.lookup("gestCent");
		} catch (RemoteException | NotBoundException e1) {
			e1.printStackTrace();
		}

		try {
			centServer.aggiungiCentralinaStradale(this.idCentralinaStradale);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		while(true) {
			try {
				TimeUnit.SECONDS.sleep(this.intervalloDiTempo);

				int numeroVeicoli=this.rilevatoreVeicoli.getNumeroVeicoli();
				int sommaVelocita=this.rilevatoreVelocita.getSommaVelocita();
				if(numeroVeicoli!=0) {
					this.calcolaVelocitaMedia(numeroVeicoli,sommaVelocita);
				}

				this.rilevatoreVeicoli.reset();
				this.rilevatoreVelocita.resetSommaVelocita(this.velocita);
				this.calcolaIntervallo(numeroVeicoli);

				this.creaDatoTraffico();		
				this.inviaDatoTraffico();
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.run();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
