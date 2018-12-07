package gestionetraffico;
import java.util.*;

public abstract class Centralina extends TimerTask {
	protected String stato;
	protected Posizione posizione;
	protected int velocita;
	protected RilevatoreVelocita rilevatoreVelocita;
	public String getStato() {
		return this.stato;
	}
	public void setStato(String stato) {
		this.stato=stato;
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