
public class GestoreApplicazioni {
	private static GestoreApplicazioni istance=null;
	private int raggio;
	private ApplicazioneMobile[] listaApplicazioni;
	private Utente[] listaUtente;
    private GestoreApplicazioni() {
    	this.listaApplicazioni=new ApplicazioneMobile[20000];
    	this.listaUtente=new Utente[20000];
    	this.raggio=500;
    }
    public static GestoreApplicazioni getIstance() {
            if(istance==null)
                    istance = new GestoreApplicazioni();
            return istance;
    }
    public int getRaggio() {
    	return this.raggio;
    }
    public void setRaggio(int raggio) {
    	this.raggio=raggio;
    }
    public void aggiungiApplicazione(ApplicazioneMobile app) {
    	int i=0;
    	for (i=0; i<20000; i++) {
    		if (this.listaApplicazioni[i]==null) {
    			this.listaApplicazioni[i]=app;
    			break;
    		}
    	}
    	if (i==20000) {
    		System.out.println("immpossibile aggiungere app");
    	}
    }
    public void rimuoviApplicazione(int id) {
    	int i=0;
    	for (i=0; i<20000; i++) {
    		if (this.listaApplicazioni[i].getIdentificativo()==id) {
    			this.listaApplicazioni[i]=null;
    			break;
    		}
    	}
    	if (i==20000) {
    		System.out.println("non esiste applicazione con questo id");
    	}
    }
    public void aggiungiUtente(Utente utente) {
    	int i=0;
    	for (i=0; i<20000; i++) {
    		if (this.listaUtente[i]==null) {
    			this.listaUtente[i]=utente;
    			break;
    		}
    	}
    	if (i==20000) {
    		System.out.println("immpossibile aggiungere utente");
    	}
    }
    public void rimuoviUtente(String nome) {
    	int i=0;
    	for (i=0; i<20000; i++) {
    		if (this.listaUtente[i].getUsername()==nome) {
    			this.listaUtente[i]=null;
    			break;
    		}
    	}
    	if (i==20000) {
    		System.out.println("non esiste utente con questo nome");
    	}
    }
    public ApplicazioneMobile[] getListaApplicazioni() {
    	return this.listaApplicazioni;
    }
    public Utente[] getListaUtenti() {
    	return this.listaUtente;
    }
    public boolean segnalaDatabase(NotificaApplicazione notifica) {
    	GestoreDatabase.getIstance().aggiungiNotificaApplicazione(notifica);
    	return true;
    }
    public ApplicazioneMobile[] calcolaApplicazioniDaNotificare() {
    	return this.listaApplicazioni;
    }
    
    

}
