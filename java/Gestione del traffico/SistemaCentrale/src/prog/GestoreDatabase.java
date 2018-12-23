package prog;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

public class GestoreDatabase {
	private static GestoreDatabase instance=null;
	private ArrayList<DatoGenerico> tabellaTraffico;
	private ArrayList<NotificaApplicazione> listaNotificheApplicazioni;
	private ArrayList<DatoTraffico> listaDatoTraffico;
	private ArrayList<StatoVeicolo> listaStatoVeicolo;

	private GestoreDatabase() {
		this.listaDatoTraffico=new ArrayList<DatoTraffico>();
		this.listaNotificheApplicazioni=new ArrayList<NotificaApplicazione>();
		this.tabellaTraffico=new ArrayList<DatoGenerico>();
		this.listaStatoVeicolo=new ArrayList<StatoVeicolo>();
	}
	public static GestoreDatabase getInstance() {
		//per tenere la classe singleton
		if(instance==null)
			instance = new GestoreDatabase();
		return instance;
	}
	public ArrayList<DatoGenerico> getTabellaTraffico() {
		return this.tabellaTraffico;
	}
	public void aggiungiNotificaApplicazione(NotificaApplicazione notifica) throws NotBoundException {
		//metodo per aggiungere una nuova notifica del tipo NotificaApplicazione alla lista
		this.listaNotificheApplicazioni.add(notifica);
		try {
			//una volta aggiunta la notifica viene chiamato il metodo apposito per aggiornare la tabella di traffico
			aggiornaTabellaTraffico(notifica.getMittente(),notifica.getPosizione(), notifica.getTipo(), notifica.getData(), notifica.getOra(), notifica.getMinA(), notifica.getOraA());
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void aggiungiDatoTraffico (DatoTraffico dato) throws NotBoundException {
		this.listaDatoTraffico.add(dato);
		try {
			// aggiorna la tabella del traffico con un mittente "centralina"
			aggiornaTabellaTraffico("centralina",dato.getPosizione(), dato.getTipo(), dato.getData(), dato.getOra(), dato.getMinA(), dato.getOraA());
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}



	public void rimuoviNotificaApplicazione (NotificaApplicazione notifica) {
		//metodo per rimuovere una notifica del tipo NotificaApplicazione dalla lista
		this.listaNotificheApplicazioni.remove(notifica);
	}

	public void rimuoviDatoTraffico (DatoTraffico dato) {
		//metodo per rimuovere una notifica del tipo DatoTraffico dalla lista
		this.listaDatoTraffico.remove(dato);
	}



	public synchronized void aggiornaTabellaTraffico(String mittente,Posizione pos, String tipo, String data, String ora, int minA, int oraA) throws BiffException, IOException, NotBoundException {
		DatoGenerico datoGenerico=creaDatoGenerico(pos, tipo, data, ora, minA, oraA);
		GregorianCalendar dat = new GregorianCalendar();
		for (int i=this.tabellaTraffico.size()-1;i>=0;--i) {

			int oraAttuale=dat.get(Calendar.HOUR);
			int minAttuale=dat.get(Calendar.MINUTE);
			int minArr=this.tabellaTraffico.get(i).getMinA();
			int oraArr=this.tabellaTraffico.get(i).getOraA();
			
			// serve per eliminare le notifiche dalla mappa dopo due minuti dalla visualizzazione, tempo dopo il quale vengono considerate obsolete
			// In ogni caso le centraline continuano a notificare e, in caso di eventi di traffico, generalmente non passa molto tempo tra una notifica e la successiva
			// Per quanto riguarda le segnalazioni di coda da parte delle applicazioni, si suppone che in una situazione reale, laddove si formi una coda piu' persone continuino a notificarne la presenza
			// Una coda, inoltre, potrebbe esaurirsi in breve tempo per cause di vari tipi, di conseguenza mantenere una segnalazione troppo a lungo potrebbe trasmettere informazioni fuorvianti


			if (this.tabellaTraffico.get(i).getPosizione().equals(pos)||((oraAttuale==oraArr&&(minAttuale-minArr>2))||(oraAttuale>oraArr&&minAttuale>0)||(oraAttuale==0&&oraArr==11&&minAttuale>0))) {
				try {
					FunzionamentoSistemaCentrale.getMappa().rimuoviMarcatore(this.tabellaTraffico.get(i).getPosizione().getLatitudine(), this.tabellaTraffico.get(i).getPosizione().getLongitudine());
					this.tabellaTraffico.remove(i);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "La mappa non e' disponibile.");
				}
			}
		}
		this.tabellaTraffico.add(datoGenerico);

		try {
			FunzionamentoSistemaCentrale.getMappa().aggiungiPunto(datoGenerico);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "La mappa non e' disponibile.");
		}

		if (!(datoGenerico.getTipo().endsWith("Traffico nella norma"))){
			// Non ci sono notifiche se il traffico e' del tipo "Traffico nella norma"
			// le applicazioni da notificare sono quelle nel raggio di 5000 m dalla segnalazione
			GestoreApplicazioni.getInstance().calcolaApplicazioniDaNotificare(mittente,pos, tipo);
		}
	}

	public DatoGenerico creaDatoGenerico(Posizione pos, String tipo, String data, String ora, int minA, int oraA) {
		// metodo per trasformare una notifica di qualsiasi tipo in dato di tipo DatoGenerico
		return new DatoGenerico(pos, tipo, data, ora, minA, oraA);
	}
	public synchronized void aggiungiStatoVeicolo(StatoVeicolo stato) throws BiffException, IOException, NotBoundException {
		this.listaStatoVeicolo.add(stato);
		aggiornaTabellaTraffico("auto",stato.getPosizione(), stato.getTipo(), stato.getData(), stato.getOra(), stato.getMinA(), stato.getOraA());
		
	}



}
