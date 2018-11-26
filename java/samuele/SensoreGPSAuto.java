
public class SensoreGPSAuto extends SensoreGPS{
	public SensoreGPSAuto () {
	}
	
	public Posizione rilevaPosizione() {
		float latitudine=0;
		float longitudine=0;
		
		this.posizione=new Posizione(latitudine, longitudine);
		return this.posizione;
	}
	

}
