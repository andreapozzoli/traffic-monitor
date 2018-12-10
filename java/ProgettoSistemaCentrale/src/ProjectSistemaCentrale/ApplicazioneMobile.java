package ProjectSistemaCentrale;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import jxl.read.biff.BiffException;


public class ApplicazioneMobile extends UnicastRemoteObject implements IApplicazioneMobile{
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;
	private static int id=0;
	private IGestoreApplicazioni iGestoreApplicazioni;

	


	/*public ApplicazioneMobile() throws BiffException, IOException, RemoteException{
		this.identificativo=this.id;
		this.id++;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		iGestoreApplicazioni.aggiungiApplicazione(this);
	}*/

	public ApplicazioneMobile(int id) throws BiffException, IOException, RemoteException {

		super ();
		this.identificativo=this.id;
		this.id++;

		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();

		
	}

	public void setInterfaccia(IGestoreApplicazioni iGestoreApplicazioni) {
		this.iGestoreApplicazioni=iGestoreApplicazioni;
	}
	
	public void setPosizione (Posizione pos) {
		this.posizione=pos;
	}

	public SensoreGPS getSensore () {
		return this.sensore;
	}
	
	


	public int getIdentificativo() throws RemoteException{
		return this.identificativo;
	}

	public void setIdentificativo(int id) throws RemoteException{
		this.identificativo=id;
	}

	public void aggiornaPosizione() throws BiffException, IOException, RemoteException {
		this.posizione=this.sensore.rilevaPosizione();
	}

	public Posizione getPosizione() throws BiffException, IOException, RemoteException {
		aggiornaPosizione();
		return this.posizione;
	}

	public String getUsernameUtente() throws RemoteException{
		return this.utente.getUsername();
	}

	//modifica
	public void segnalaUtente(NotificaApplicazione notifica ) {
		//metodo per segnalare all'utente la ricezione di una notifica
		notifica.stampaNotifica();
	}
	
	public void setUtente(Utente utente) {
		this.utente=utente;
	}
	
	public Utente getUtente() {
		return this.utente;
	}

	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) throws RemoteException{
		return new NotificaApplicazione("SistemaCentrale",pos,tipo);
	}

	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) throws RemoteException{
		this.listaNotificheRicevute.add(notifica);
		this.segnalaUtente(notifica);
	}

	public void svuotaLista() {
		this.listaNotificheRicevute.clear();
	}

	//modificato

	


	//modificato
	/*public boolean registraUtente() {
		//registrare nuovo utente
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username;
		while(true) {
			username=sc.nextLine();
			if(GestoreApplicazioni.getInstance().verificaAccesso(username)) {
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
		GestoreApplicazioni.getInstance().registraUtente(utente);
		return true;
	}*/	

	//nuova
	

	/*	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//creaGUI();
				try {
					new ApplicazioneMobile();
				} catch (BiffException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}*/

}
