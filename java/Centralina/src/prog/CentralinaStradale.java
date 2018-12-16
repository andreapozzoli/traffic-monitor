package prog;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;


public class CentralinaStradale extends Centralina {	//è la classe che contiene il run della centralina stradale e 
														//funge da client nella connessione rmi con il gestore centraline
	
	private int intervalloDiTempo;
	private RilevatoreVeicoli rilevatoreVeicoli;
	private DatoTraffico datoTraffico;				//è il dato che viene inviato al sistema centrale
	private float rapporto;							//serve per calcolare il nuovo intervallo di tempo
	private int intervalloMinimo;
	private String tipoStrada;						//può essere urbana, extraurbana, superstrada
	private int idCentralinaStradale;
	private RilevatoreVelocitaS rilevatoreVelocita;
	private IGestoreCentraline centServer;			//interfaccia del server rmi del GestoreCentraline

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
	public void calcolaIntervallo(int numeroVeicoli) {			//metodo per l'aggiornamento dell'intervallo di tempo
																//in proporzione al traffico che è espresso in macchine al secondo
		
		float temp=((float)numeroVeicoli)/((float)this.intervalloDiTempo);		//rapporto tra il numero di veicoli transitati 
																				//nell'intervallo di tempo e l'intervallo di tempo
		if (temp>0.0) {
			this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);	//rapporto è la quantità di traffico dell'intervallo precedente
																						//all'aumentare delle macchine che transitano, diminuisce l'intervallo
			
			if (this.intervalloDiTempo<this.intervalloMinimo) {
				this.intervalloDiTempo=this.intervalloMinimo;			//l'intervallo non può essere minore di 10secondi
			}
			else if (this.intervalloDiTempo>90) {
				this.intervalloDiTempo=90;								//l'intervallo non può essere maggiore di 90 secondi
			}
			this.rapporto=temp;											//aggiornamento del rapporto
		}
		else {
			this.intervalloDiTempo=this.intervalloDiTempo*2;			//se non transitano veicoli, l'intervallo viene raddoppiato

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
	public void setIntervalloMinimo(int intervalloMinimo) {		//!!!!!!!!!!!!!!!!!!!
		this.intervalloMinimo=intervalloMinimo;
	}
	public String getTipoStrada() {
		return this.tipoStrada;
	}
	public void setTipoStrada(String tipoStrada) {
		this.tipoStrada=tipoStrada;
	}
	
	public void creaDatoTraffico() {			//viene settato il dato di traffico da inviare al sistema centrale
		if (this.velocita!=0) {					//se la velocità media è uguale a zero, si tiene la velocità e il tipo di traffico precedenti
			switch (this.tipoStrada){			//in base al tipo di strada, a seconda della velocità si decide il tipo di evento  di traffico
			case "urbana":
				if (velocita<20)
					tipo="S"+velocita+" Coda";
				else if (velocita>=20 && velocita<30)
					tipo="S"+velocita+" Traffico elevato";
				else if (velocita>=30 && velocita<40)
					tipo="S"+velocita+" Velocita lenta";
				else 
					tipo="S"+velocita+" Traffico nella norma";			//viene settato il tipo, S sta per centralina stradale
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
	public void inviaDatoTraffico() throws RemoteException {		//invia il dato di traffico al GestoreCentraline
		centServer.segnalaDatabaseS(this.datoTraffico);
	}
	public void calcolaVelocitaMedia(int numeroVeicoli, int somma) {
		if (numeroVeicoli!=0) {
			this.velocita=(int)(somma/numeroVeicoli);
		}
		else {							//!!!!!!!!!!!!!!!!!!!
			this.velocita=0;		
		}
	}

	public RilevatoreVeicoli getRilevatoreVeicoli() {
		return this.rilevatoreVeicoli;
	}

	public void run() {
		
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());		//setta il security manager di rmi
		System.setSecurityManager(cSM);

		Registry registry = null;				//connessione lato client rmi con il GestoreCentraline
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
			centServer.aggiungiCentralinaStradale(this.idCentralinaStradale);		//aggiunge la centralina alla lista centraline
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		while(true) {
			try {
				TimeUnit.SECONDS.sleep(this.intervalloDiTempo);			//la centralina aspetta che scada l'intervallo

				int numeroVeicoli=this.rilevatoreVeicoli.getNumeroVeicoli();		//raccoglie il numero di veicoli transitati e
				int sommaVelocita=this.rilevatoreVelocita.getSommaVelocita();		//la somma delle loro velocità
				if(numeroVeicoli!=0) {												//se è passato almeno un veicolo calcola la velocità media
					this.calcolaVelocitaMedia(numeroVeicoli,sommaVelocita);			//altrimenti la lascia uguale alla precedente
				}

				this.rilevatoreVeicoli.reset();										//si resettano i conteggi dei veicoli
				this.rilevatoreVelocita.resetSommaVelocita(this.velocita);			//e delle velocità
				this.calcolaIntervallo(numeroVeicoli);								//si calcola il nuovo intervallo

				this.creaDatoTraffico();					//viene creato il dato di traffico
				this.inviaDatoTraffico();					//e inviato
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.run();						//se avviene un interruzione si fa ripartire il run
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
