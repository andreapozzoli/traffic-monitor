package prog;
import java.util.Random;

public class RilevatoreVelocitaS extends RilevatoreVelocita{
	
	private int sommaVelocita;

	public RilevatoreVelocitaS() {
		this.velocita=50;
		this.sommaVelocita = 0;
	}

	public void rilevaVelocita() {
		
		//da fare
		Random random = new Random();
		int a;
		if (this.velocita>15) {
		     a = this.velocita-15; // numero minimo
		}
		else {
		      a =0;
		}
		System.out.println(this.velocita);
		int b = this.velocita+15; // numero massimo
		int c = ((b-a) + 1);
		System.out.println("a "+a);
		System.out.println("b "+b);
		System.out.println("c "+c);
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