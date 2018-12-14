package prog;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.lang.Math;


public class GestoreApplicazioni extends UnicastRemoteObject implements IGestoreApplicazioni {
	/**
	 * 
	 */
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
		this.listaApplicazioni.add(new App(id, utente));
	}
	public void rimuoviApplicazione(int id) throws RemoteException{
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
		try {
			GestoreDatabase.getInstance().aggiungiNotificaApplicazione(notifica);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void calcolaApplicazioniDaNotificare(String mittente,Posizione posizione, String tipo) throws BiffException, IOException, NotBoundException {
		
			if (!(this.listaApplicazioni.isEmpty())) {
				for (App var: this.listaApplicazioni) {
					if ((var.getUtente().getUsername()!=null)&&(!(var.getUtente().getUsername().equals(mittente)))) {
						//conversione distanza coordinate in metri
						IApplicazioneMobile appServer=this.getInterfacciaApp(var.getId());

						double R = 6378.137; // Radius of earth in KM 
						double lat1=posizione.getLatitudine();
						double lat2=appServer.getPosizione().getLatitudine();
						double lon1=posizione.getLongitudine();
						double lon2=appServer.getPosizione().getLongitudine();
						double dLat = lat1 * Math.PI/180 - lat2 * Math.PI/180; 
						double dLon = lon1 * Math.PI/180 - lon2 * Math.PI/180; 
						double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI/180) * Math.cos(lat2 * Math.PI/180) * Math.sin(dLon/2) * Math.sin(dLon/2); 
						double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
						double d = R * c; 
						//if (Math.sqrt(Math.pow(var.getPosizione().getLatitudine()-posizione.getLatitudine(),2)+Math.pow(var.getPosizione().getLongitudine()-posizione.getLongitudine(),2))<this.raggio){
						if (d*1000<this.raggio) {
						NotificaApplicazione notifica;
						notifica=appServer.creaNotificaApplicazione(posizione, tipo);
						appServer.aggiungiNotificaInCoda(notifica);    
							}
					}
				}
			}
		}

	

	public boolean verificaAccesso(String username, String password) {
		return GestoreUtenti.getInstance().riconosciUtente(username, password);
	}

	public boolean verificaAccesso(String username) {
		return GestoreUtenti.getInstance().riconosciUtente(username);
	}

	public Utente passaggioUtente(String username) {
		return GestoreUtenti.getInstance().getUtente(username);
	}

	public void registraUtente(Utente utente) {
		GestoreUtenti.getInstance().aggiungiUtente(utente);
	}


	public IApplicazioneMobile getInterfacciaApp(int id) throws RemoteException, NotBoundException {


		//System.setSecurityManager(new SecurityManager());

		customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
		System.setSecurityManager(cSM);

		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12346+id);
		
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


