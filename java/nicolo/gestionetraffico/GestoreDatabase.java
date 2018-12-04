package gestionetraffico;
import java.util.ArrayList;

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
    	aggiornaTabellaTraffico(notifica.getPosizione(), notifica.getTipo(), notifica.getData(), notifica.getOra());
    }
    
    public void aggiungiDatoTraffico (DatoTraffico dato) {
    	this.listaDatoTraffico.add(dato);
    	aggiornaTabellaTraffico(dato.getPosizione(), dato.getTipo(), dato.getData(), dato.getOra());
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
    
    public void aggiornaTabellaTraffico(Posizione pos, String tipo, String data, String ora) {
    	DatoGenerico datoGenerico=creaDatoGenerico(pos, tipo, data, ora);
    	for (DatoGenerico dato: this.tabellaTraffico) {
    		if (dato.getPosizione().equals(pos)) {
    			this.tabellaTraffico.remove(dato);
    		}
    	}
    	this.tabellaTraffico.add(datoGenerico);
    	//modificato
    	if (!(datoGenerico.getTipo().equals("traffico nella norma"))){
    	GestoreApplicazioni.getInstance().calcolaApplicazioniDaNotificare(pos, tipo);
    	}
    }
    
    public DatoGenerico creaDatoGenerico(Posizione pos, String tipo, String data, String ora) {
    	return new DatoGenerico(pos, tipo, data, ora);
    }

}
