package gestionetraffico;

import java.util.Random;

public class SensoreGPSAuto extends SensoreGPS{
	public SensoreGPSAuto () {
	}
	
	public Posizione rilevaPosizione() {
		float latitudine=0;
		float longitudine=0;
        Random random=new Random();
		
		latitudine = random.nextFloat();

		longitudine = random.nextFloat();
		
		this.posizione=new Posizione(latitudine, longitudine);
		return this.posizione;
	}
	

}
