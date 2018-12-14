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

	public void rilevaVeicoli() {
		//da fare
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 1; // numero massimo
		int c = ((b-a) + 1);
		int rilevato= random.nextInt(c) + a;
		System.out.println("veicolo: " + rilevato);
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
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
