package prog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import jxl.read.biff.BiffException;


public class ApplicazioneMobile extends UnicastRemoteObject implements IApplicazioneMobile, Serializable{
	
	
	private static final long serialVersionUID = 907504994966013534L;
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;
	private boolean fissa=false;


	private JFrame frame;

	private JTextPane areaNotifiche = new JTextPane();
	private JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);
	private JScrollBar scorrimentoVerticale = this.paneNotifiche.getVerticalScrollBar();






	public ApplicazioneMobile() throws BiffException, IOException, RemoteException {
		super();

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

	public JTextPane getAreaNotifiche() {
		return this.areaNotifiche;
	}

	public void setAreaNotifiche(JTextPane area) {
		this.areaNotifiche=area;
	}


	public JScrollPane getPaneNotifiche() {
		return this.paneNotifiche;
	}

	public void setPaneNotifiche(JScrollPane pane) {
		this.paneNotifiche=pane;
	}


	

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
			if (!(this.fissa)) {
			this.posizione=this.sensore.rilevaPosizione();
			}
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFissa(boolean val) {
		this.fissa=val;
	}
	
	public boolean getFissa() {
		return this.fissa;
	}

	public Posizione getPosizione() {
		if (!(this.fissa)){
			aggiornaPosizione();
		}
		return this.posizione;
	}

	public String getUsernameUtente(){
		if (this.utente==null) {
			return null;
		}
		return this.utente.getUsername();
	}

	//modifica
	public void segnalaUtente(NotificaApplicazione notifica) {
		//metodo per segnalare all'utente la ricezione di una notifica



		this.areaNotifiche.setContentType("text/html");

		//HTMLEditorKit doc=(HTMLEditorKit)areaNotifiche.getEditorKit();

		HTMLDocument doc =(HTMLDocument)areaNotifiche.getStyledDocument();
		try {

			doc.insertBeforeStart(doc.getCharacterElement(0), notifica.stampaNotifica()+"<br>");
		} catch (BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*

		String precedente = this.areaNotifiche.getText();

		System.out.println(notifica.stampaNotifica()+"<br>"+precedente);
		this.areaNotifiche.setText(notifica.stampaNotifica()+"<br>"+precedente);*/
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
