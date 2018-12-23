package prog;


public abstract class RilevatoreVelocita {
	//questa classe e' implementata perche' nel momento in cui si volesse creare un diverso rilevatore di velocita' si potrebbe ereditare la struttura
	protected int velocita;


	public int getVelocita() {
		return this.velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita=velocita;
	}

	public abstract void rilevaVelocita();


}
