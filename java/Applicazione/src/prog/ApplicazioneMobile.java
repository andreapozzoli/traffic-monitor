package prog;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;

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
	private JLabel posizioneCorrente = new JLabel("Nessuna posizione rilevata.");

	public ApplicazioneMobile() throws BiffException, IOException, RemoteException {
		super();

		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();

	}
	
	public void impostaEtichettaPosizione(String via) {
		posizioneCorrente.setText("Ultima posizione rilevata: " + via);
	}
	
	public JLabel getLabelPosizione() {
		return posizioneCorrente;
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
			if (!(this.fissa)) { // in questo modo se l'utente vuole tenere fissa l'applicazione gli basta spuntare la casella dedicata 
			this.posizione=this.sensore.rilevaPosizione(); // e non viene percio' aggiornata la posizione dell'applicazione
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
		if (!(this.fissa)){ // in questo modo se l'utente vuole tenere fissa l'applicazione gli basta spuntare la casella dedicata 
			aggiornaPosizione(); // e non viene percio' aggiornata la posizione dell'applicazione
		}
		
		impostaEtichettaPosizione(this.posizione.getVia());
		return this.posizione;
	}


	public String getUsernameUtente(){
		if (this.utente==null) { // in questo modo se l'utente e' null non si accede ad un puntatore nullo
			return null;
		}
		return this.utente.getUsername();
	}

	public void segnalaUtente(NotificaApplicazione notifica) {
		//metodo per segnalare all'utente la ricezione di una notifica

		this.areaNotifiche.setContentType("text/html"); // in questo modo e' possibile colorare le notifiche in modo diverso

		HTMLDocument doc =(HTMLDocument)areaNotifiche.getStyledDocument(); 
		try {

			doc.insertBeforeStart(doc.getCharacterElement(0), notifica.stampaNotifica()+"<br>"); // viene stampata la notifica ricevuta nell'apposizta area
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}

	}

	public void setUtente(Utente utente) {
		this.utente=utente;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo){
		// metodo di creazione della notifica di tipo NotificaApplicazione
		return new NotificaApplicazione("SistemaCentrale",pos,tipo);
	}

	public void aggiungiNotificaInCoda(NotificaApplicazione notifica){
		// metodo che aggiunge la notifica ricevuta alla lista delle notifiche ricevute in precedenza
		this.listaNotificheRicevute.add(notifica);
		this.segnalaUtente(notifica); // viene quindi segnalato l'utente della ricezione
	}

	public void svuotaLista() {
		// metodo per suotare la lista delle notifiche ricevute
		this.listaNotificheRicevute.clear();
	}
	
	public ArrayList<NotificaApplicazione> getListaNotifiche(){
		return this.listaNotificheRicevute;
	}

}
