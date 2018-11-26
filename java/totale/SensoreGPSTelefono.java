
public class SensoreGPSTelefono extends SensoreGPS {
	
	public SensoreGPSTelefono () {
	}
	
	public Posizione rilevaPosizione() {
		float latitudine=0;
		float longitudine=0;
		
		this.posizione=new Posizione(latitudine, longitudine);
		return this.posizione;
	}
	
	

}
