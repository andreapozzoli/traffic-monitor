package gestionetraffico;

import java.util.Random;

public class RilevatoreVelocitaS extends RilevatoreVelocita{
	
	private int sommaVelocita;

	public RilevatoreVelocitaS() {
		this.sommaVelocita = 0;
	}

	public int rilevaVelocita() {
		
		//da fare
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 100; // numero massimo
		int c = ((b-a) + 1);
		int velocita = random.nextInt(c) + a;
		this.sommaVelocita=this.sommaVelocita+velocita;
		return velocita;
	}

	public void resetSommaVelocita() {

		this.sommaVelocita = 0;
	}

	public int getSommaVelocita() {
		return this.sommaVelocita;
	}
}