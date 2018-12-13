package prog;
import java.io.BufferedReader;
import java.rmi.*;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ChatClient extends Centralina implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7421613124536148336L;
	private int intervalloDiTempo;
	private RilevatoreVeicoli rilevatoreVeicoli;
	private DatoTraffico datoTraffico;
	private float rapporto;
	private int intervalloMinimo;
	private String tipoStrada;
	private int idCentralinaStradale;
	private RilevatoreVelocitaS rilevatoreVelocita;
	
	public ChatClient(int intervalloDiTempo, Posizione posizione,String tipoStrada) throws RemoteException{
		super();
		this.intervalloDiTempo=intervalloDiTempo;
		this.rilevatoreVeicoli=new RilevatoreVeicoli();
		this.rilevatoreVelocita=this.rilevatoreVeicoli.getRilevatoreVelocita();
		this.posizione=posizione;
		this.velocita=0;
		this.tipo="traffico nella norma";
		this.intervalloMinimo=10;
		this.tipoStrada=tipoStrada;
	}
	public void calcolaIntervallo(int numeroVeicoli) {
		float temp=((float)numeroVeicoli)/((float)this.intervalloDiTempo);
		if (temp>0.0) {
			this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);
			if (this.intervalloDiTempo<this.intervalloMinimo) {
				this.intervalloDiTempo=this.intervalloMinimo;
			}
			System.out.println("ho fatto if");
			this.rapporto=temp;
		}
		else {
			this.intervalloDiTempo=this.intervalloDiTempo*2;
			System.out.println("ho fatto else");
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
			case "autostrada":
				if (velocita<20)
					tipo="S"+velocita+" Coda";
				else if (velocita>=20 && velocita<60)
					tipo="S"+velocita+" Traffico elevato";
				else if (velocita>=60 && velocita<80)
					tipo="S"+velocita+" Velocita lenta";
				else 
					tipo="S"+velocita+" Traffico nella norma";
				break;

			}
		}
		this.datoTraffico=new DatoTraffico(this.posizione, tipo, this.velocita);
	}
	public void inviaDatoTraffico(ChatServerInterface server) throws RemoteException {
		server.segnalaDatabaseS(this.datoTraffico);
		//fare con rmi
	}
	public void calcolaVelocitaMedia(int numeroVeicoli, int somma) {
		if (numeroVeicoli!=0) {
			this.velocita=(int)(somma/numeroVeicoli);
		}
		else {
			this.velocita=0;
		}
	}

	//nuovo
	public RilevatoreVeicoli getRilevatoreVeicoli() {
		return this.rilevatoreVeicoli;
	}
	
	
	/*public void init() {
		   customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		   System.setSecurityManager(cSM);
		}*/

	//private static final long serialVersionUID = -7192642191671755062L;

	


	public void run() {
		
		
		ChatServerInterface hello;
		try {
			hello = (ChatServerInterface)Naming.lookup("rmi://localhost/ABC");
			
			
		
		
		
		
		
		
		/*try {
			
			//System.setSecurityManager(new SecurityManager());
			
			
			customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
			   System.setSecurityManager(cSM);
			
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
			
			ChatServerInterface server = (ChatServerInterface) registry.lookup("chatServer");
			
			*/
			
			
			
			
			
			while(true) {
				try {
					System.out.println("intervallo "+this.intervalloDiTempo);
					Thread.sleep(this.intervalloDiTempo*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int numeroVeicoli=this.rilevatoreVeicoli.getNumeroVeicoli();
				int sommaVelocita=this.rilevatoreVelocita.getSommaVelocita();
				if(numeroVeicoli!=0) {
					this.calcolaVelocitaMedia(numeroVeicoli,sommaVelocita);
				}
				System.out.println("somma "+sommaVelocita);
				System.out.println("numero "+numeroVeicoli);
				System.out.println("velocita in ce "+this.velocita);
				this.rilevatoreVeicoli.reset();
				this.rilevatoreVelocita.resetSommaVelocita(this.velocita);
				this.calcolaIntervallo(numeroVeicoli);
				System.out.println("intervallo calcolato");
				System.out.println("velocita in ce2 "+this.velocita);
				this.creaDatoTraffico();		
				System.out.println("dato traffico creato");
				try {
					//this.inviaDatoTraffico(server);
					hello.segnalaDatabaseS(this.datoTraffico);
					System.out.println(hello.sommma(5, 3));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("dato traffico inviato");
			}
			
			
	/*	} catch (Exception e) {
			e.printStackTrace();
		}*/
	}catch (Exception e) {
		System.out.println("HelloClient exception: " + e);
		}
	}




	
}
