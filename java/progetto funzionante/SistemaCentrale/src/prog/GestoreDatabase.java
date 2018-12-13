package prog;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

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
			aggiornaTabellaTraffico(notifica.getMittente(),notifica.getPosizione(), notifica.getTipo(), notifica.getData(), notifica.getOra());
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void aggiungiDatoTraffico (DatoTraffico dato) throws NotBoundException {
		this.listaDatoTraffico.add(dato);
		try {
			aggiornaTabellaTraffico("centralina",dato.getPosizione(), dato.getTipo(), dato.getData(), dato.getOra());
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

	

	public synchronized void aggiornaTabellaTraffico(String mittente,Posizione pos, String tipo, String data, String ora) throws BiffException, IOException, NotBoundException {
		DatoGenerico datoGenerico=creaDatoGenerico(pos, tipo, data, ora);
		for (int i=this.tabellaTraffico.size()-1;i>=0;--i) {

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

		//modificato
		if (!(datoGenerico.getTipo().equals("traffico nella norma"))){
			GestoreApplicazioni.getInstance().calcolaApplicazioniDaNotificare(mittente,pos, tipo);
		}
	}

	public DatoGenerico creaDatoGenerico(Posizione pos, String tipo, String data, String ora) {
		return new DatoGenerico(pos, tipo, data, ora);
	}

}
