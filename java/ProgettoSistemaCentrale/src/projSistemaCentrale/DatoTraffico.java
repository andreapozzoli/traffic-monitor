package ProjSistemaCentrale;

public class DatoTraffico extends Notifica {
	private String tipo;
	private int velocita;
	
	public DatoTraffico(Posizione pos, String tipo, int vel) {
		this.tipo=tipo;
		this.velocita=vel;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public void setVelocita(int vel) {
		this.velocita=vel;
	}
	
	public int getVelocita() {
		return this.velocita;
	}
	

}
