package prog;

import java.util.Random;

public class RilevatoreVelocitaS extends RilevatoreVelocita{
	
	private int sommaVelocita;

	public RilevatoreVelocitaS() {
		this.velocita=50;
		this.sommaVelocita = 0;
	}

	public void rilevaVelocita() {
		
		
		Random random = new Random();
		int min;
		int max;
		if (this.velocita>15) {
		     min = this.velocita-15; // numero minimo
		}
		else {
		      min =0;
		}
		System.out.println(this.velocita);
		if(this.velocita<95) {
		max = this.velocita+15;
		} // numero massimo
		else {
			max=110;
		}
		int intorno = ((max-min) + 1);
		
		int vel = random.nextInt(intorno) + min;
		System.out.println("vel "+vel);
		this.sommaVelocita=this.sommaVelocita+vel;
	}

	public void resetSommaVelocita(int velocita) {

		this.velocita=velocita; //indica la velocità dell'intervallo precedente
		this.sommaVelocita = 0;
	}

	public int getSommaVelocita() {
		return this.sommaVelocita;
	}
}