
public class StatoVeicolo extends Notifica {
	private int idVeicolo;
	private int velocita;
	
	public StatoVeicolo(Posizione pos, int id, int vel) {
		this.idVeicolo=id;
		this.velocita=vel;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
	}
	
	public int getIdVeicolo() {
		return this.idVeicolo;
	}
	
	public void setIdVeicolo(int id) {
		this.idVeicolo=id;
	}
	
	public int getVelocita() {
		return this.velocita;
	}
	
	public void setVelocita(int vel) {
		this.velocita=vel;
	}

}
