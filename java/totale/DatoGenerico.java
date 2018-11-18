
public class DatoGenerico {
	private Posizione posizione;
	private boolean stato;
	private String tipo;
	public DatoGenerico(Posizione posizione, boolean stato, String tipo) {
		this.posizione=posizione;
		this.stato=stato;
		this.tipo=tipo;
	}
	public Posizione getPosizione(){
		return this.posizione;
	}
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
	public boolean getStato(){
		return this.stato;
	}
	public void setStato(boolean stato) {
		this.stato=stato;
	}
	public String getTipo(){
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}

}
