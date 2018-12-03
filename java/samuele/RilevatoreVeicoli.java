package gestionetraffico;
import java.util.*;

public class RilevatoreVeicoli implements Runnable {
	private int numeroVeicoli;
	//secondo me necessario attributo rilevatore velocità associato e lo togliamo da centralina
	
	public RilevatoreVeicoli () {
		this.numeroVeicoli = 0;
	}

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
		//da fare
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 100; // numero massimo
		int c = ((b-a) + 1);
		this.numeroVeicoli = random.nextInt(c) + a;
		return this.numeroVeicoli;
	}
	
	//metodo run
	public void run() {
		while (true) {
			rilevaVeicoli();
		}
	}

}
