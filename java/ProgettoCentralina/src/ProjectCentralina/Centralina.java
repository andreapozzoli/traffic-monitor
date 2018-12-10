package ProjectCentralina;
import java.util.*;

public abstract class Centralina implements Runnable {
	protected String tipo;
	protected Posizione posizione;
	protected int velocita;
	protected RilevatoreVelocita rilevatoreVelocita;
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
