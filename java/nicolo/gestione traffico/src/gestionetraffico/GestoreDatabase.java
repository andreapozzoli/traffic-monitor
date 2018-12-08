package gestionetraffico;
import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

public class GestoreDatabase {
	private static GestoreDatabase instance=null;
	private ArrayList<DatoGenerico> tabellaTraffico;
	private ArrayList<NotificaApplicazione> listaNotificheApplicazioni;
	private ArrayList<DatoTraffico> listaDatoTraffico;
	private ArrayList<StatoVeicolo> listaStatoVeicolo;

	private GestoreDatabase() {
		this.listaDatoTraffico=new ArrayList<DatoTraffico>();
		this.listaStatoVeicolo=new ArrayList<StatoVeicolo>();
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
	public void aggiungiNotificaApplicazione(NotificaApplicazione notifica) {
		//verificare che non sia gia presente
		this.listaNotificheApplicazioni.add(notifica);
		try {
			aggiornaTabellaTraffico(notifica.getMittente(),notifica.getPosizione(), notifica.getTipo(), notifica.getData(), notifica.getOra());
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void aggiungiDatoTraffico (DatoTraffico dato) {
		this.listaDatoTraffico.add(dato);
		try {
			aggiornaTabellaTraffico("centralina",dato.getPosizione(), dato.getTipo(), dato.getData(), dato.getOra());
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void aggiungiStatoVeicolo (StatoVeicolo statoV) {
		this.listaStatoVeicolo.add(statoV);
		//elabora il tipo di traffico, aggiorna se lo facciamo
	}

	public void rimuoviNotificaApplicazione (NotificaApplicazione notifica) {
		this.listaNotificheApplicazioni.remove(notifica);
	}

	public void rimuoviDatoTraffico (DatoTraffico dato) {
		this.listaDatoTraffico.remove(dato);
	}

	public void rimuoviStatoVeicolo (StatoVeicolo statoV) {
		this.listaStatoVeicolo.remove(statoV);
	}

	public synchronized void aggiornaTabellaTraffico(String mittente,Posizione pos, String tipo, String data, String ora) throws BiffException, IOException {
		DatoGenerico datoGenerico=creaDatoGenerico(pos, tipo, data, ora);
		for (int i=this.tabellaTraffico.size()-1;i>=0;--i) {

			if (this.tabellaTraffico.get(i).getPosizione().equals(pos)) {
				this.tabellaTraffico.remove(i);
				FunzionamentoSistemaCentrale.getMappa().rimuoviMarcatore(pos.getLatitudine(), pos.getLongitudine());
			}
		}
		this.tabellaTraffico.add(datoGenerico);

		FunzionamentoSistemaCentrale.getMappa().aggiungiPunto(datoGenerico);
		
		//modificato
		if (!(datoGenerico.getTipo().equals("traffico nella norma"))){
			GestoreApplicazioni.getInstance().calcolaApplicazioniDaNotificare(mittente,pos, tipo);
		}
	}

	public DatoGenerico creaDatoGenerico(Posizione pos, String tipo, String data, String ora) {
		return new DatoGenerico(pos, tipo, data, ora);
	}

}
