package gestionetraffico;

public abstract class SensoreGPS {
	protected Posizione posizione;
	
	public SensoreGPS() {
	}
	
	public abstract Posizione rilevaPosizione() ;

}
