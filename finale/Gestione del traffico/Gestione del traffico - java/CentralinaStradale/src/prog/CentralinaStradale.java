package prog;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


public class CentralinaStradale extends Centralina {	//e' la classe che contiene il run della centralina stradale e 
	//funge da client nella connessione rmi con il gestore centraline

	private int intervalloDiTempo;
	private RilevatoreVeicoli rilevatoreVeicoli;
	private DatoTraffico datoTraffico;				//e' il dato che viene inviato al sistema centrale
	private float rapporto;							//serve per calcolare il nuovo intervallo di tempo
	private int intervalloMinimo;
	private String tipoStrada;						//puo' essere urbana, extraurbana, superstrada
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
		//in proporzione al traffico che e' espresso in macchine al secondo

		float temp=((float)numeroVeicoli)/((float)this.intervalloDiTempo);		//rapporto tra il numero di veicoli transitati 
		//nell'intervallo di tempo e l'intervallo di tempo
		if (temp>0.0) {
			this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);	//rapporto e' la quantita' di traffico dell'intervallo precedente
			//all'aumentare delle macchine che transitano, diminuisce l'intervallo

			if (this.intervalloDiTempo<this.intervalloMinimo) {
				this.intervalloDiTempo=this.intervalloMinimo;			//l'intervallo non puo' essere minore di 10 secondi
			}
			else if (this.intervalloDiTempo>90) {
				this.intervalloDiTempo=90;								//l'intervallo non puo' essere maggiore di 90 secondi
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
	public DatoTraffico getDatoTraffico() {
		return this.datoTraffico;
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

	public void creaDatoTraffico() {			//viene settato il dato di traffico da inviare al sistema centrale
		if (this.velocita!=0) {					//se la velocita' media e' uguale a zero, si tiene la velocita' e il tipo di traffico precedenti
			switch (this.tipoStrada){			//in base al tipo di strada, a seconda della velocita' si decide il tipo di evento  di traffico
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
	public void inviaDatoTraffico() throws RemoteException, NotBoundException {		//invia il dato di traffico al GestoreCentraline
		centServer.segnalaDatabaseS(this.datoTraffico);
	}

	public void calcolaVelocitaMedia(int numeroVeicoli, int somma) {
		if (numeroVeicoli!=0) {
			this.velocita=(int)(somma/numeroVeicoli);
		}
		else {						
			this.velocita=0;		
		}

		FunzionamentoCentralinaS.impostaEtichettaVelocita(this.velocita);
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
			centServer.aggiungiCentralinaStradale(this.idCentralinaStradale);		//aggiunge la centralina alla lista centraline

		} catch (RemoteException | NotBoundException e1) {
			JOptionPane.showMessageDialog(null,
					"Il sistema centrale non e' disponibile.\nI dati possono essere trasmessi solo in presenza\ndi una connessione con il sistema centrale.\n"
							+ "\nIl pannello di configurazione della centralina verra' chiuso.",
							"Errore di connessione",
							JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}


		while(true) {
			try {
				TimeUnit.SECONDS.sleep(this.intervalloDiTempo);			//la centralina aspetta che scada l'intervallo

				int numeroVeicoli=this.rilevatoreVeicoli.getNumeroVeicoli();		//raccoglie il numero di veicoli transitati e
				int sommaVelocita=this.rilevatoreVelocita.getSommaVelocita();		//la somma delle loro velocita'
				if(numeroVeicoli!=0) {												//se e' passato almeno un veicolo calcola la velocita' media
					this.calcolaVelocitaMedia(numeroVeicoli,sommaVelocita);			//altrimenti la lascia uguale alla precedente
				}

				this.rilevatoreVeicoli.reset();										//si resettano i conteggi dei veicoli
				this.rilevatoreVelocita.resetSommaVelocita(this.velocita);			//e delle velocita'
				this.calcolaIntervallo(numeroVeicoli);								//si calcola il nuovo intervallo

				this.creaDatoTraffico();					//viene creato il dato di traffico
				try {
					this.inviaDatoTraffico();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					//e inviato
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.run();						//se avviene un interruzione si fa ripartire il run
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
