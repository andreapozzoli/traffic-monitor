
public class Posizione {
	private float latitudine;
	private float longitudine;
	
	public Posizione() {
	}
	
	public Posizione(float lat, float lon) {
		this.latitudine = lat;
		this.longitudine = lon;
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
