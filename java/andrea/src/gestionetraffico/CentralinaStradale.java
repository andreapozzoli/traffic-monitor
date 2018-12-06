package gestionetraffico;

import java.util.*;

public class CentralinaStradale extends Centralina {
	private int intervalloDiTempo;
	private RilevatoreVeicoli rilevatoreVeicoli;
	private DatoTraffico datoTraffico;
	private float rapporto;
	private int intervalloMinimo;
	private String tipoStrada;
	private int idCentralinaStradale;
	private RilevatoreVelocitaS rilevatoreVelocita;
	
	//modificato
	public CentralinaStradale(int intervalloDiTempo, Posizione posizione, int intervalloMinimo,String tipoStrada) {
		this.intervalloDiTempo=intervalloDiTempo;
		this.rilevatoreVeicoli=new RilevatoreVeicoli();
		this.rilevatoreVelocita=new RilevatoreVelocitaS();
		this.posizione=posizione;
		this.velocita=0;
		this.stato="accesa";
		this.intervalloMinimo=intervalloMinimo;
		this.tipoStrada=tipoStrada;
		GestoreCentraline.getInstance().aggiungiCentralinaStradale(this);
	}
	public void calcolaIntervallo() {
		float temp=this.rilevatoreVeicoli.getNumeroVeicoli()/this.intervalloDiTempo;
		this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);
		if (this.intervalloDiTempo<this.intervalloMinimo) {
			this.intervalloDiTempo=this.intervalloMinimo;
		}
		this.rapporto=temp;
		this.rilevatoreVeicoli.reset();
		this.calcolaVelocitaMedia();
	}
	
	public void setIdCentralinaStradale(int id) {
		this.idCentralinaStradale=id;
	}
	
	public int getIdCentralinaStradale() {
		return this.idCentralinaStradale;
	}
	public int getIntervallo() {
		return this.intervalloDiTempo;
	}
	public float getRapporto () {
		return this.rapporto;
	}
	public int getIntervalloMinimo() {
		return this.intervalloMinimo;
	}
	public void setIntervalloMinimo(int intervalloMinimo) {
		this.intervalloMinimo=intervalloMinimo;
	}
	public String getTipoStrada() {
		return this.tipoStrada;
	}
	public void setTipoStrada(String tipoStrada) {
		this.tipoStrada=tipoStrada;
	}
	public void creaDatoTraffico() {
		String tipo= new String();
		switch (this.tipoStrada){
		case "urbana":
			if (velocita<20)
				tipo="coda";
			else if (velocita>=20 && velocita<30)
				tipo="traffico elevato";
			else if (velocita>=30 && velocita<40)
				tipo="velocita lenta";
			else 
				tipo="traffico nella norma";			
			break;
			
		case "extraurbana":
			if (velocita<20)
				tipo="coda";
			else if (velocita>=20 && velocita<40)
				tipo="traffico elevato";
			else if (velocita>=40 && velocita<50)
				tipo="velocita lenta";
			else
				tipo="traffico nella norma";
			break;
		case "superstrada":
			if (velocita<20)
				tipo="coda";
			else if (velocita>=20 && velocita<50)
				tipo="traffico elevato";
			else if (velocita>=50 && velocita<70)
				tipo="velocita lenta";
			else
				tipo="traffico nella norma";
			break;
		case "autostrada":
			if (velocita<20)
				tipo="coda";
			else if (velocita>=20 && velocita<60)
				tipo="traffico elevato";
			else if (velocita>=60 && velocita<80)
				tipo="velocita lenta";
			else 
				tipo="traffico nella norma";
			break;
			
		}
		this.datoTraffico=new DatoTraffico(this.posizione, tipo, this.velocita);
	}
	public void inviaDatoTraffico() {
		GestoreCentraline.getInstance().segnalaDatabaseS(this.datoTraffico);
		//fare con rmi
	}
	public void calcolaVelocitaMedia() {
		this.velocita=(int)(this.rilevatoreVelocita.getSommaVelocita()/this.rilevatoreVeicoli.getNumeroVeicoli());
		this.rilevatoreVelocita.resetSommaVelocita();
	}
	
	//nuovo
	public RilevatoreVeicoli getRilevatoreVeicoli() {
		return this.rilevatoreVeicoli;
	}
	
	//nuovo
	public void run() {
		this.calcolaIntervallo();
		System.out.println("intervallo calcolato");
		this.creaDatoTraffico();
		System.out.println("dato traffico creato");
		this.inviaDatoTraffico();
		System.out.println("dato traffico inviato");
	}


}
