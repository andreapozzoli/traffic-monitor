package gestionetraffico;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;

import jxl.read.biff.BiffException;


public class ApplicazioneMobile /*extends UnicastRemoteObject implements IApplicazioneMobile*/ {
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;
	
	public ApplicazioneMobile() throws BiffException, IOException /*throws RemoteException*/{
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);
	}
	
	public ApplicazioneMobile(int id) throws BiffException, IOException /*throws RemoteException*/ {
		super ();
		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);

	}
	
	public void segnalaCoda() throws BiffException, IOException  {
		
		this.posizione=this.sensore.rilevaPosizione();
		NotificaApplicazione notifica=new NotificaApplicazione(this.utente.getUsername(),this.posizione, "coda");
		//nuovo
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica);
		
		
		
	}
	
	public int getIdentificativo() {
		return this.identificativo;
	}
	
	public void setIdentificativo(int id) {
		this.identificativo=id;
	}
	
	public void aggiornaPosizione() throws BiffException, IOException {
		this.posizione=this.sensore.rilevaPosizione();
	}
	
	public Posizione getPosizione() throws BiffException, IOException {
		aggiornaPosizione();
		return this.posizione;
	}
	
	public String getUsenameUtente() {
		return this.utente.getUsername();
	}
	
	//modifica
	public void segnalaUtente(NotificaApplicazione notifica ) {
		//metodo per segnalare all'utente la ricezione di una notifica
		notifica.stampaNotifica();
	}
	
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) {
		return new NotificaApplicazione("SistemaCentrale",pos,tipo);
	}
	
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) {
		this.listaNotificheRicevute.add(notifica);
		this.segnalaUtente(notifica);
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
		if(GestoreApplicazioni.getInstance().verificaAccesso(username, password)) {
			this.utente=GestoreApplicazioni.getInstance().passaggioUtente(username);
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
		if(GestoreApplicazioni.getInstance().verificaAccesso(username)) {
			System.out.println("Username già in uso");
		}	
		else {
			break;
		}
		}
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		this.utente=new Utente(username,password);
		GestoreApplicazioni.getInstance().registraUtente(utente);
		return true;
	}	
	
	//nuova
	public void logout() {
		this.utente=null;
	}

}
