import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;


public class ApplicazioneMobile extends UnicastRemoteObject implements IApplicazioneMobile {
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;
	
	public ApplicazioneMobile(int id) throws RemoteException {
		super ();
		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
	}
	
	public void segnalaCoda() {
		this.posizione=this.sensore.rilevaPosizione();
		NotificaApplicazione notifica=new NotificaApplicazione(this.posizione, "coda");
		
		//chiamata a segnalaDatabase() di gestore applicazioni
		
	}
	
	public int getIdentificativo() {
		return this.identificativo;
	}
	
	public void setIdentificativo(int id) {
		this.identificativo=id;
	}
	
	public void aggiornaPosizione() {
		this.posizione=this.sensore.rilevaPosizione();
	}
	
	public Posizione getPosizione() {
		aggiornaPosizione();
		return this.posizione;
	}
	
	public void segnalaUtente() {
		//metodo per segnalare all'utente la ricezione di una notifica
	}
	
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) {
		return new NotificaApplicazione(pos,tipo);
	}
	
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) {
		this.listaNotificheRicevute.add(notifica);
	}
	
	public void svuotaLista() {
		this.listaNotificheRicevute.clear();
	}
	
	public boolean login() {
		//registrato inserisce dati e li verifica
		return true;
	}
	
	public boolean registraUtente(String username, String password) {
		//registrare nuovo utente
		return true;
	}	

}
