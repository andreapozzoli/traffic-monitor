package gestionetraffico;

public class Posizione {
	private double latitudine;
	private double longitudine;
	private String via;
	
	public Posizione() {
	}
	
	public Posizione(String via,double lat, double lon) {
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
	
	public double getLatitudine() {
		return this.latitudine;
	}
	
	public double getLongitudine() {
		return this.longitudine;
	}
	
	public void setLatitudine(double lat) {
		this.latitudine = lat;
	}
	
	public void setLongitudine(double lon) {
		this.longitudine = lon;
	}
	

}
