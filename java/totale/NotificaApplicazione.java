
public class NotificaApplicazione extends Notifica {
	private String tipo;	
	public NotificaApplicazione(Posizione pos, String tipo) {
		this.tipo=tipo;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
	}

	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	
}
