public abstract class RilevatoreVelocita {
	protected int velocita;
	
	public RilevatoreVelocita RilevatoreVelocita(int velocita) {
		this.velocita = velocita;

	public int getVelocita() {
		return this.velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita=velocita;
	}

	public abstract int rilevaVelocita() {
	}

}
