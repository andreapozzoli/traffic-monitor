
public class CentralinaAutomobilistica extends Centralina {
	private int periodo;
	private int idVeicolo;
	private StatoVeicolo statoVeicolo;
	private SensoreGPS sensore;
	public CentralinaAutomobilistica (int periodo, int idVeicolo) {
		this.stato="accesa";
		this.idVeicolo=idVeicolo;
		this.periodo=periodo;
		this.rilevatoreVelocita=new RilevatoreVelocitaA();
		this.sensore=new SensoreGPSAuto();
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
		this.posizione=this.sensore.rilevaPosizione();
		this.velocita=this.rilevatoreVelocita.rilevaVelocita();
		this.statoVeicolo= new StatoVeicolo (this.posizione, this.idVeicolo, this.velocita);
	}
	public void inviaStatoVeicolo() {
		//GestoreCentraline.getInstance().segnalaDatabaseA(statoVeicolo);
	}
	

}
