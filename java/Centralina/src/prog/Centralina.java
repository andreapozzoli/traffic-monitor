package prog;

public abstract class Centralina implements Runnable {
	protected String tipo;				//tipo indica il tipo di evento di traffico: coda, traffico elevato, velocità lenta o traffico nella norma
	protected Posizione posizione;
	protected int velocita;				//per velocità si intende la velocità media in un intervallo di tempo
	protected RilevatoreVelocita rilevatoreVelocita;
	
	
	//di seguito ci sono i setter e i getter di tipo, posizione e velocità
	
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public Posizione getPosizione() {
		return this.posizione;
	}
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
	public int getVelocita() {
		return this.velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita=velocita;
	}

}
