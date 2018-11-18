
public class GestoreDatabase {
	private static GestoreDatabase istance=null;
	private DatoGenerico[] tabellaTraffico;
	private NotificaApplicazione[] listaNotificheApplicazioni;
	private DatoTraffico[] listaDatoTraffico;
	private StatoVeicolo[] listaStatoVeicolo;
    private GestoreDatabase() {
    	this.listaDatoTraffico=new DatoTraffico[100];
    	this.listaStatoVeicolo=new StatoVeicolo[8000];
    	this.listaNotificheApplicazioni=new NotificaApplicazione[20000];
    	this.tabellaTraffico=new DatoGenerico[50000];
    }
    public static GestoreDatabase getIstance() {
            if(istance==null)
                    istance = new GestoreDatabase();
            return istance;
    }
    public DatoGenerico[] getTabellaTraffico() {
    	return this.tabellaTraffico;
    }
    public void aggiungiNotificaApplicazione(NotificaApplicazione notifica) {
    	int i=0;
    	for (i=0; i<20000; i++) {
    		if (this.listaNotificheApplicazioni[i]==null) {
    			this.listaNotificheApplicazioni[i]=notifica;
    			break;
    		}
    	}
    	if (i==20000) {
    		System.out.println("immpossibile aggiungere notifica");
    	}
    }
    //public void aggiungiDatoTraffico
    //public void aggiungiStatoVeicolo
    //public void rimuoviNotificaApplicazione
    //public void rimuoviDatoTraffico
    //public void rimuoviStatoVeicolo
    //aggiornaTabellaTraffico

}
