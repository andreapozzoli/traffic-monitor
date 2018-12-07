package gestionetraffico;
import java.util.*;
public class RilevatoreVelocitaA extends RilevatoreVelocita{
	
	
	public RilevatoreVelocitaA () {
		
	}

	//modificato
	public void rilevaVelocita() {
		
		//da fare
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 100; // numero massimo
		int c = ((b-a) + 1);
		int velocita = random.nextInt(c) + a;
	}


}
