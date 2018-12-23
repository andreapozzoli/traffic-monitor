package prog;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.lang.Math;


public class GestoreApplicazioni extends UnicastRemoteObject implements IGestoreApplicazioni {

	private static final long serialVersionUID = 4452171250708806895L;
	private static GestoreApplicazioni instance=null;
	private int raggio;
	private ArrayList<App> listaApplicazioni;
	private int idApp=0;


	private GestoreApplicazioni() throws RemoteException {
		super();
		this.listaApplicazioni=new ArrayList<App>();
		this.raggio=5000;
	}


	public static GestoreApplicazioni getInstance() throws RemoteException{
		// metodo per mantenere la classe singleton
		if(instance==null)
			instance = new GestoreApplicazioni();
		return instance;

	}

	public int getRaggio() {
		return this.raggio;
	}
	public void setRaggio(int raggio) {
		this.raggio=raggio;
	}
	public void aggiungiApplicazione(int id, Utente utente) throws RemoteException{
		// ogni volta che viene creata una nuova applicazione o un utente fa il login da un'applicazione gia'
		// presente tale applicazione viene aggiunta alla lista delle applicazioni notificabili dal sistema centrale
		this.listaApplicazioni.add(new App(id, utente));
	}
	public void rimuoviApplicazione(int id) throws RemoteException{
		// ogni volta che un'applicazione viene chiusa o l'utente fa il logout essa viene rimossa dall'elenco 
		// delle applicazioni notificabili dal sisema centrale
		for (App var: this.listaApplicazioni) {
			if (var.getId()==id) {
				this.listaApplicazioni.remove(var);
				break;
			}
		}
	}

	public ArrayList<App> getListaApplicazioni() {
		return this.listaApplicazioni;
	}

	public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException{
		// metodo per segnalare il database dell'arrivo di una notifica mandata da una applicazione
		// tale metodo fa aggiungere la notifica all'elenco delle notifiche nel database
		try {
			GestoreDatabase.getInstance().aggiungiNotificaApplicazione(notifica);
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(null,
					"Errore di connessione.\n"
							+ "\nLa finestra verra' chiusa.",
							"Errore di connessione",
							JOptionPane.ERROR_MESSAGE);
			System.exit(1);		
		}
	}

	public void calcolaApplicazioniDaNotificare(String mittente,Posizione posizione, String tipo) throws BiffException, IOException, NotBoundException {
		// metodo per calcolare le applicazioni da notificare nel momento in cui avviene un evento di traffico 
		// e viene notificato al sistema centale
		if (!(this.listaApplicazioni.isEmpty())) { // se la lista e' vuota non ci sono applicazioni da notificare
			for (App var: this.listaApplicazioni) {
				// se l'utente associato all'applicazione e' nullo o e' il mittente della notifica, l'applicazione non viene notificata
				if ((var.getUtente().getUsername()!=null)&&(!(var.getUtente().getUsername().equals(mittente)))) {
					//conversione distanza coordinate in metri
					IApplicazioneMobile appServer=this.getInterfacciaApp(var.getId());

					// viene calcolata la distanza tra la posizione dell'evento e quella dell'appliczione
					double R = 6378.137; // raggio della terra in km 
					double lat1=posizione.getLatitudine();
					double lat2=appServer.getPosizione().getLatitudine();
					double lon1=posizione.getLongitudine();
					double lon2=appServer.getPosizione().getLongitudine();
					double dLat = lat1 * Math.PI/180 - lat2 * Math.PI/180; 
					double dLon = lon1 * Math.PI/180 - lon2 * Math.PI/180; 
					double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI/180) * Math.cos(lat2 * Math.PI/180) * Math.sin(dLon/2) * Math.sin(dLon/2); 
					double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
					double d = R * c; 

					if (d*1000<this.raggio) { // se l'applicazione non e' all'interno del raggio non viene notificata
						NotificaApplicazione notifica;
						notifica=appServer.creaNotificaApplicazione(posizione, tipo);
						appServer.aggiungiNotificaInCoda(notifica);    
					}
				}
			}
		}
	}



	public boolean verificaAccesso(String username, String password) {
		// metodo per riconoscere un utente che sta facendo il login
		// necessario per fare da tramite tra GestoreUtenti e applicazione
		return GestoreUtenti.getInstance().riconosciUtente(username, password);
	}

	public boolean verificaAccesso(String username) {
		// metodo per evitare che un utente che si sta registrando utilizzi un username gia' in uso
		// necessario per fare da tramite tra GedtoreUtenti e applicazione
		return GestoreUtenti.getInstance().riconosciUtente(username);
	}

	public Utente passaggioUtente(String username) {
		// metodo per riassegnare l'utente gia' registrato all'applicazione da cui egli ha fatto il login
		// necessario per fare da tramite tra GedtoreUtenti e applicazione
		return GestoreUtenti.getInstance().getUtente(username);
	}

	public void registraUtente(Utente utente) {
		// nel momento in cui un nuovo utente si registra i suoi dati vengono passati al GestoreUtenti che li salva
		GestoreUtenti.getInstance().aggiungiUtente(utente);
	}


	public IApplicazioneMobile getInterfacciaApp(int id) throws RemoteException, NotBoundException {
		// metodo che caratterizza il lato client del GestoreApplicazioni
		// necessario per ottenere l'interfaccia dell'applicazione corretta nel momento in cui si calcola quali si deve notificare
		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		System.setSecurityManager(cSM);

		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12346+id); // viene usato l'id dell'applicazione per calcolare la porta esatta

		IApplicazioneMobile appSer = (IApplicazioneMobile) registry.lookup("appServer");
		return appSer;
	}


	public void setIdApp (int id) {
		this.idApp=id;
	}

	public int getIdApp() throws RemoteException{
		this.idApp++;
		return this.idApp;
	}

}


