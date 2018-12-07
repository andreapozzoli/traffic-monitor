package gestionetraffico;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ApplicazioneMobile /*extends UnicastRemoteObject implements IApplicazioneMobile*/ {
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;

	public ApplicazioneMobile() /*throws RemoteException*/{
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);
	}

	public ApplicazioneMobile(int id) /*throws RemoteException*/ {
		super ();
		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);

	}

	public void segnalaCoda()  {

		this.posizione=this.sensore.rilevaPosizione();
		NotificaApplicazione notifica=new NotificaApplicazione(this.posizione, "coda");
		//nuovo
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica);



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

	//modifica
	public void segnalaUtente(NotificaApplicazione notifica ) {
		//metodo per segnalare all'utente la ricezione di una notifica
		notifica.stampaNotifica();
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

	//modificato
	public boolean login() {
		//registrato inserisce dati e li verifica
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username=sc.nextLine();
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		if(GestoreUtenti.getInstance().riconosciUtente(username, password)) {
			this.utente=GestoreUtenti.getInstance().getUtente(username);
			return true;
		}
		else
			return false;

	}

	//modificato
	public boolean registraUtente() {
		//registrare nuovo utente
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username;
		while(true) {
			username=sc.nextLine();
			if(GestoreUtenti.getInstance().riconosciUtente(username)) {
				System.out.println("Username gi� in uso");
			}	
			else {
				break;
			}
		}
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		this.utente=new Utente(username,password);
		GestoreUtenti.getInstance().aggiungiUtente(this.utente);
		return true;
	}	

	//nuova
	public void logout() {
		this.utente=null;
	}

}
