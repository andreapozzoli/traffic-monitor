
public class CentralinaAutomobilistica extends Centralina {
	private int periodo;
	private int idVeicolo;
	private StatoVeicolo statoVeicolo
	public CentralinaAutomobilistica (int periodo, int idVeicolo) {
		this.idVeicolo=idVeicolo;
		this.periodo=periodo;
	}
	public int getPeriodo() {
		return this.periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo=periodo;
	}
	public int getIdVeicolo() {
		return this.idVeicolo;
	}
	public void setIdVeicolo(int idVeicolo) {
		this.idVeicolo=idVeicolo;
	}
	public void creaStatoVeicolo() {
		this.posizione= new Posizione();
		this.velocita=rilevaVelocita();
		this.stato="accesa";
		this.statoVeicolo= new StatoVeicolo (this.stato, this.velocita, this.posizione);
	}
	public void inviaStatoVeicolo() {
		GestoreCentraline.getInstance().segnalaDatabaseA(statoVeicolo);
	}
	public int rilevaVelocita() {
		//rileva la velocità auto
	}

}
