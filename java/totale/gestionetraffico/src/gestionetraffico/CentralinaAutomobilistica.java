package gestionetraffico;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class CentralinaAutomobilistica extends Centralina {
	private int periodo;
	private int idVeicolo;
	private StatoVeicolo statoVeicolo;
	private SensoreGPS sensore;
	private String stato;
	
	public CentralinaAutomobilistica (int periodo, int idVeicolo) throws BiffException, IOException {
		this.stato="accesa";
		this.idVeicolo=idVeicolo;
		this.periodo=periodo;
		this.rilevatoreVelocita=new RilevatoreVelocitaA();
		this.sensore=new SensoreGPSAuto();
		GestoreCentraline.getInstance().aggiungiCentralinaAuto(this);
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
	public void creaStatoVeicolo() throws BiffException, IOException {
		this.posizione=this.sensore.rilevaPosizione();
		this.velocita=this.rilevatoreVelocita.getVelocita();
		this.statoVeicolo= new StatoVeicolo (this.posizione, this.idVeicolo, this.velocita);
	}
	//modificato
	public void inviaStatoVeicolo() {
		GestoreCentraline.getInstance().segnalaDatabaseA(statoVeicolo);
	}
	
	//nuovo 
	public void run() {
		
	}
	

}
