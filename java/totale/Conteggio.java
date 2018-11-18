
public class Conteggio {
	private int numeroVeicoli;
	private boolean overflow;
	public int getNumeroVeicoli() {
		return this.numeroVeicoli;
	}
	public void setNumeroVeicoli(int numeroVeicoli) {
		this.numeroVeicoli=numeroVeicoli;
	}
	public void reset() {
		this.numeroVeicoli=0;
	}
	public Conteggio() {
		this.numeroVeicoli=0;
		this.overflow=false;
	}

}
