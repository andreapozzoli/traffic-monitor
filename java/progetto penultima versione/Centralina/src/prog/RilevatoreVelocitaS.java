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
		int a;
		int b;
		if (this.velocita>15) {
		     a = this.velocita-15; // numero minimo
		}
		else {
		      a =0;
		}
		System.out.println(this.velocita);
		if(this.velocita<95) {
		b = this.velocita+15;
		} // numero massimo
		else {
			b=110;
		}
		int c = ((b-a) + 1);
		
		int vel = random.nextInt(c) + a;
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