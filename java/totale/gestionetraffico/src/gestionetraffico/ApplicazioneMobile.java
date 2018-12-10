package gestionetraffico;
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


public class ApplicazioneMobile /*extends UnicastRemoteObject implements IApplicazioneMobile*/{
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;
	private static int id=0;
	//private IGestoreApplicazioni iGestoreApplicazioni;
	
	private JFrame frame;
	private final long serialVersionUID = 1L;

	private JTextArea areaNotifiche = new JTextArea(10, 10);
	private JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);

	


	/*public ApplicazioneMobile() throws BiffException, IOException, RemoteException{
		this.identificativo=this.id;
		this.id++;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		iGestoreApplicazioni.aggiungiApplicazione(this);
	}*/

	public ApplicazioneMobile(int id) throws BiffException, IOException {


		this.identificativo=this.id;
		System.out.println("id="+this.identificativo);
		this.id++;

		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();

		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame=frame;
	}
	
	public JTextArea getAreaNotifiche() {
		return this.areaNotifiche;
	}
	
	public void setAreaNotifiche(JTextArea area) {
		this.areaNotifiche=area;
	}
	
	
	public JScrollPane getPaneNotifiche() {
		return this.paneNotifiche;
	}
	
	public void setPaneNotifiche(JScrollPane pane) {
		this.paneNotifiche=pane;
	}


	/*public void setInterfaccia(IGestoreApplicazioni iGestoreApplicazioni) {
		this.iGestoreApplicazioni=iGestoreApplicazioni;
	}*/
	
	public void setPosizione (Posizione pos) {
		this.posizione=pos;
	}

	public SensoreGPS getSensore () {
		return this.sensore;
	}
	
	


	public int getIdentificativo(){
		return this.identificativo;
	}

	public void setIdentificativo(int id){
		this.identificativo=id;
	}

	public void aggiornaPosizione() {
		try {
			this.posizione=this.sensore.rilevaPosizione();
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Posizione getPosizione() {
		aggiornaPosizione();
		return this.posizione;
	}

	public String getUsernameUtente(){
		if (this.utente==null) {
			return null;
		}
		return this.utente.getUsername();
	}

	//modifica
	public void segnalaUtente(NotificaApplicazione notifica ) {
		//metodo per segnalare all'utente la ricezione di una notifica
		this.areaNotifiche.setText(notifica.stampaNotifica()+"\n"+this.areaNotifiche.getText());
	}
	
	public void setUtente(Utente utente) {
		this.utente=utente;
	}
	
	public Utente getUtente() {
		return this.utente;
	}

	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo){
		return new NotificaApplicazione("SistemaCentrale",pos,tipo);
	}

	public void aggiungiNotificaInCoda(NotificaApplicazione notifica){
		this.listaNotificheRicevute.add(notifica);
		this.segnalaUtente(notifica);
	}

	public void svuotaLista() {
		this.listaNotificheRicevute.clear();
	}

}
