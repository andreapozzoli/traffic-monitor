
public class NotificaApplicazione extends Notifica {
	private boolean presenza;
	private String tipo;
	
	public NotificaApplicazione(Posizione pos, String tipo) {
		this.tipo=tipo;
		this.presenza=true;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
	}

	public void setPresenza(boolean pres) {
		this.presenza=pres;
	}
	
	public boolean getPresenza() {
		return this.presenza;
	}
	
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	
}
