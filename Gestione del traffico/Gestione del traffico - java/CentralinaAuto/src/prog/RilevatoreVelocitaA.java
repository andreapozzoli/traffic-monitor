package prog;

import java.util.Random;

public class RilevatoreVelocitaA extends RilevatoreVelocita{

	public RilevatoreVelocitaA() {
		super();
		this.velocita=50;
	}
	
	
	public int rilevaVelocita() {
		Random random = new Random();
		int min;
		int max;
		if (this.velocita>20) {
			min = this.velocita-20; 
		}
		else {
			min =0;
		}
		if(this.velocita<75) {
			max = this.velocita+15;
		} 
		else {
			max=90;
		}
		int intorno = ((max-min) + 1);
		this.velocita= random.nextInt(intorno) + min;
		return this.velocita;
		}

}
