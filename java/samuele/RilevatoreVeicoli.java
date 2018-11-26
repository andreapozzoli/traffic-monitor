public abstract class RilevatoreVeicoli {
	private int numeroVeicoli;
	
	public RilevatoreVeicoli RilevatoreVeicoli(int numeroVeicoli) {
		this.numeroVeicoli = 0;

	public int getNumeroVeicoli() {
		return this.numeroVeicoli;
	}
	public void setNumeroVeicoli(int numeroVeicoli) {
		this.numeroVeicoli=numeroVeicoli;
	}

	public void reset() {
		this.numeroVeicoli = 0;
	}

	public int rilevaVeicoli() {
	}

}
