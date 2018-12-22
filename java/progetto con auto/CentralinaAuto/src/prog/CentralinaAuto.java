package prog;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

public class CentralinaAuto extends Centralina{
	private IGestoreCentraline centServer;
	private int idCentralinaAuto;
	private int intervalloDiTempo=90;
	private SensoreGPSAuto sensore;
	private RilevatoreVelocitaA rilevatore;
	private StatoVeicolo stato;
	double raggio;
	private Posizione ultimaPosizione;
	
	
	public CentralinaAuto(Posizione pos, int velocita) throws BiffException, IOException {
		this.rilevatore=new RilevatoreVelocitaA();
		this.sensore=new SensoreGPSAuto(pos);
		this.raggio=1.50;
		this.velocita=velocita;
		this.posizione=pos;
		this.stato=new StatoVeicolo();
		
	}
	
	public void setId (int id) {
		this.idCentralinaAuto=id;
	}
	public int getId() {
		return this.idCentralinaAuto;
	}
	public void setIntervallo(int intervallo) {
		this.intervalloDiTempo=intervallo;
	}
	public int getIntervallo() {
		return this.intervalloDiTempo;
	}
	public SensoreGPSAuto getSensore() {
		return this.sensore;
	}
	public RilevatoreVelocitaA getRilevatore() {
		return this.rilevatore;
	}
	public void setStatoVeicolo(StatoVeicolo stato) {
		this.stato=stato;
	}
	public StatoVeicolo getStatoVeicolo() {
		return this.stato;
	}
	public void setRaggio(double raggio) {
		this.raggio=raggio;
	}
	public double getRaggio() {
		return this.raggio;
	}
	
	private void inviaStatoVeicolo() throws NotBoundException, BiffException, IOException {
		centServer.segnalaDatabaseA(this.stato);
	}

	private void creaStatoVeicolo(int velocita, Posizione posizione) {
		this.stato.setVelocita(velocita);
		this.stato.setPosizione(posizione);
		this.stato.setData();
		this.stato.setOra();
		this.stato.setTipo();
	}

	private void calcolaRaggio() {
		System.out.println("ultimaPosizione: "+this.ultimaPosizione.getVia());
		System.out.println("posizioneNuova: "+this.posizione.getVia());
		System.out.println("velocita: "+this.velocita);
		if(this.velocita==0) {
			this.raggio=0;
		}
		else if (this.ultimaPosizione.getLatitudine()==this.posizione.getLatitudine()&&this.ultimaPosizione.getLongitudine()==this.posizione.getLongitudine()) {
			this.raggio=this.raggio*2;
		}
		else {
			this.raggio=((float)(this.velocita))*((float)(this.intervalloDiTempo))/3600;
		}
		System.out.println("raggio: "+this.raggio);
	}
	

	public void run() {

		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());		//setta il security manager di rmi
		System.setSecurityManager(cSM);

		Registry registry = null;				//connessione lato client rmi con il GestoreCentraline
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 13344);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		try {
			this.centServer = (IGestoreCentraline) registry.lookup("gestCentAuto");
			centServer.aggiungiCentralinaAuto(this.idCentralinaAuto);		//aggiunge la centralina alla lista centraline

		} catch (RemoteException | NotBoundException e1) {
			e1.printStackTrace();
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

				this.sensore.calcolaListaPosizioni(this.raggio);
				this.ultimaPosizione=this.posizione;
				this.posizione=sensore.rilevaPosizione();
				this.velocita=rilevatore.rilevaVelocita();
				this.calcolaRaggio();
				this.creaStatoVeicolo(this.velocita, this.posizione);					//viene creato il dato di traffico
				this.inviaStatoVeicolo();					//e inviato
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.run();						//se avviene un interruzione si fa ripartire il run
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
