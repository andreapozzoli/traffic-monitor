
public class Posizione {
	private float latitudine;
	private float longitudine;
	
	public Posizione() {
		rilevaPosizione();
	}
	
	public Posizione(float lat, float lon) {
		this.latitudine = lat;
		this.longitudine = lon;
	} 
	
	public void rilevaPosizione() {
		//rappresenta il sensore gps che fa la rilevazione
	}
	
	public float getLatitudine() {
		return this.latitudine;
	}
	
	public float getLongitudine() {
		return this.longitudine;
	}
	
	public void setLatitudine(float lat) {
		this.latitudine = lat;
	}
	
	public void setLongitudine(float lon) {
		this.longitudine = lon;
	}
	

}
