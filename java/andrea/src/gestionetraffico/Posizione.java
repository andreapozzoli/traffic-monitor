package gestionetraffico;

public class Posizione {
	private float latitudine;
	private float longitudine;
	private String via;
	
	public Posizione() {
	}
	
	public Posizione(String via,float lat, float lon) {
		this.via=via;
		this.latitudine = lat;
		this.longitudine = lon;
	} 
	
	public void setVia(String via) {
		this.via=via;
	}
	
	public String getVia() {
		return this.via;
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
