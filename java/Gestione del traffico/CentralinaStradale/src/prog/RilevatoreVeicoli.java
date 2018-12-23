package prog;
import java.util.*;

public class RilevatoreVeicoli implements Runnable {
	private int numeroVeicoli;
	private RilevatoreVelocitaS rilevatoreVelocita;

	public RilevatoreVeicoli () {
		this.rilevatoreVelocita=new RilevatoreVelocitaS();
		this.numeroVeicoli = 0;
	}

	public RilevatoreVelocitaS getRilevatoreVelocita() {
		return this.rilevatoreVelocita;
	}

	public int getNumeroVeicoli() {
		this.rilevaVeicoli();
		return this.numeroVeicoli;
	}
	public void setNumeroVeicoli(int numeroVeicoli) {
		this.numeroVeicoli=numeroVeicoli;
	}

	public void reset() {
		this.numeroVeicoli = 0;
	}

	public void rilevaVeicoli() {		//la rilevazione del transito di un veicolo e' un numero random tra 0 (non transitato) e 1 (transitato)
		Random random = new Random();
		int min = 0; // numero minimo
		int max = 1; // numero massimo
		int rilevazione = ((max-min) + 1);
		int rilevato= random.nextInt(rilevazione) + min;
		if (rilevato==1) {
			this.numeroVeicoli++;
			this.rilevatoreVelocita.rilevaVelocita();
		}
	}

	//metodo run
	public void run() {
		while (true) {
			rilevaVeicoli();
			try {
				Thread.sleep(3000);			//il rilevatore di veicoli viene chiamato ogni tre secondi
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
