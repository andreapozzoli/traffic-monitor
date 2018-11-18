

public class ApplicazioneMobile {
	private int identificativo;
	private Bottone bottone;
	private Posizione posizione;
	private NotificaApplicazione[] listaNotificheRicevute;
	private Utente utente;
	
	public ApplicazioneMobile(int id) {
		this.identificativo=id;
		this.bottone=new Bottone();
		this.listaNotificheRicevute=new NotificaApplicazione[50];
		for (int i=0;i<this.listaNotificheRicevute.length;i++) {
			this.listaNotificheRicevute[i]= null;
		}		
	}
	
	public int getIdentificativo() {
		return this.identificativo;
	}
	
	public void setIdentificativo(int id) {
		this.identificativo=id;
	}
	
	public void aggiornaPosizione() {
		this.posizione=new Posizione();
	}
	
	public Posizione getPosizione() {
		aggiornaPosizione();
		return this.posizione;
	}
	
	public void segnalaUtente() {
		//metodo per segnalare all'utente la ricezione di una notifica
	}
	
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) {
		return new NotificaApplicazione(pos,tipo);
	}
	
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) {
		int i=0;
		while (this.listaNotificheRicevute[i]!=null) {
			if(i==this.listaNotificheRicevute.length) {
				for (int j=1; j<this.listaNotificheRicevute.length;j++) {
					this.listaNotificheRicevute[j-1]=this.listaNotificheRicevute[j];
				}
				break;
			}
			i++;
		}
		this.listaNotificheRicevute[i]=notifica;
	}
	
	public void svuotaLista() {
		int i=0;
		while(this.listaNotificheRicevute[i]!=null) {
			this.listaNotificheRicevute[i]=null;
			i++;
		}
	}
	
	public boolean login() {
		//registrato inserisce dati e li verifica
		return true;
	}
	
	public boolean registraUtente() {
		//registrare nuovo utente
		return true;
	}	

}
