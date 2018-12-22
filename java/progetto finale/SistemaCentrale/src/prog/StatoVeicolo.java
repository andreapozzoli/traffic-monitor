package prog;

public class StatoVeicolo extends Notifica{
	
	private static final long serialVersionUID = -2994446088339863214L;
	private int velocita;
	private String tipo;
	
	public int getVelocita() {
		return this.velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita=velocita;
	}
	
	public void setTipo() {
		if (this.velocita<20) {
			this.tipo="V"+this.velocita+" Coda";
		}
		else if (this.velocita>=20&&this.velocita<30) {
			this.tipo="V"+this.velocita+" Traffico elevato";
		}
		else if (this.velocita>=30&&this.velocita<40) {
			this.tipo="V"+this.velocita+" Velocita lenta";
		}
		else {
			this.tipo="v"+this.velocita+" Traffico nella norma";
		}
	}
	
	public String getTipo() {
		return this.tipo;
	}

}