package prog;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jxl.read.biff.BiffException;

public class GestoreDatabase {
	private static GestoreDatabase instance=null;
	private ArrayList<DatoGenerico> tabellaTraffico;
	private ArrayList<NotificaApplicazione> listaNotificheApplicazioni;
	private ArrayList<DatoTraffico> listaDatoTraffico;

	private GestoreDatabase() {
		this.listaDatoTraffico=new ArrayList<DatoTraffico>();
		this.listaNotificheApplicazioni=new ArrayList<NotificaApplicazione>();
		this.tabellaTraffico=new ArrayList<DatoGenerico>();
	}
	public static GestoreDatabase getInstance() {
		if(instance==null)
			instance = new GestoreDatabase();
		return instance;
	}
	public ArrayList<DatoGenerico> getTabellaTraffico() {
		return this.tabellaTraffico;
	}
	public void aggiungiNotificaApplicazione(NotificaApplicazione notifica) throws NotBoundException {
		//verificare che non sia gia presente
		this.listaNotificheApplicazioni.add(notifica);
		try {
			aggiornaTabellaTraffico(notifica.getMittente(),notifica.getPosizione(), notifica.getTipo(), notifica.getData(), notifica.getOra(), notifica.getMinA(), notifica.getOraA());
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void aggiungiDatoTraffico (DatoTraffico dato) throws NotBoundException {
		this.listaDatoTraffico.add(dato);
		try {
			aggiornaTabellaTraffico("centralina",dato.getPosizione(), dato.getTipo(), dato.getData(), dato.getOra(), dato.getMinA(), dato.getOraA());
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void rimuoviNotificaApplicazione (NotificaApplicazione notifica) {
		this.listaNotificheApplicazioni.remove(notifica);
	}

	public void rimuoviDatoTraffico (DatoTraffico dato) {
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
			
			if ((oraAttuale==oraArr&&(minAttuale-minArr>2))||(oraAttuale>oraArr&&minArr<58)||(oraAttuale==0&&oraArr==11&&minArr<58)) {
				try {
					FunzionamentoSistemaCentrale.getMappa().rimuoviMarcatore(this.tabellaTraffico.get(i).getPosizione().getLatitudine(), this.tabellaTraffico.get(i).getPosizione().getLongitudine());
					}catch(Exception e) {
						System.out.println("Mappa non disponibile");
					}
			}
			
			
			
			if (this.tabellaTraffico.get(i).getPosizione().equals(pos)) {
				this.tabellaTraffico.remove(i);
				try {
				FunzionamentoSistemaCentrale.getMappa().rimuoviMarcatore(pos.getLatitudine(), pos.getLongitudine());
				}catch(Exception e) {
					System.out.println("Mappa non disponibile");
				}
			}
		}
		this.tabellaTraffico.add(datoGenerico);

		try {
			FunzionamentoSistemaCentrale.getMappa().aggiungiPunto(datoGenerico);
		}
		catch(Exception e) {
			System.out.println("Mappa non disponibile2");
		}

		if (!(datoGenerico.getTipo().equals("traffico nella norma"))){
			GestoreApplicazioni.getInstance().calcolaApplicazioniDaNotificare(mittente,pos, tipo);
		}
	}

	public DatoGenerico creaDatoGenerico(Posizione pos, String tipo, String data, String ora, int minA, int oraA) {
		return new DatoGenerico(pos, tipo, data, ora, minA, oraA);
	}
	
	

}
