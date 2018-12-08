package ProjApplicazione;

public class NotificaApplicazione extends Notifica {
	private String tipo;	
	private String mittente;
	public NotificaApplicazione(String mittente,Posizione pos, String tipo) {
		this.tipo=tipo;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
		this.mittente=mittente;
	}

	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public String getMittente() {
		return this.mittente;
	}
	
	public void setMittente(String mittente) {
		this.tipo=mittente;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	//nuovo
	public void stampaNotifica() {
		System.out.println("tipo: "+this.tipo+" via: "+this.posizione.getVia()+ " posizione: "+this.posizione.getLatitudine()+" ; "+this.posizione.getLongitudine());
	}
	
	
}
