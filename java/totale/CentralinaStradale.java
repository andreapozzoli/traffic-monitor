
public class CentralinaStradale extends Centralina {
	private int intervalloDiTempo;
	private Conteggio numeroVeicoli;
	private DatoTraffico datoTraffico;
	private float rapporto;
	private int intervalloMinimo;
	private String tipoStrada;
	private int idCentralinaStradale;
	public CentralinaStradale(int intervalloDiTempo, Posizione posizione, int intervalloMinimo, int id) {
		this.intervalloDiTempo=intervalloDiTempo;
		this.numeroVeicoli=new Conteggio();
		this.posizione=posizione;
		this.velocita=0;
		this.stato="accesa";
		this.intervalloMinimo=intervalloMinimo;
		this.idCentralinaStradale=id;
	}
	public void calcolaIntervallo() {
		float temp=this.numeroVeicoli.getNumeroVeicoli()/this.intervalloDiTempo;
		this.intervalloDiTempo=(int) (this.intervalloDiTempo*this.rapporto/temp);
		if (this.intervalloDiTempo<this.intervalloMinimo) {
			this.intervalloDiTempo=this.intervalloMinimo;
		}
		this.rapporto=temp;
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
	public void serIntervalloMinimo(int intervalloMinimo) {
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
			if (velocita>=20 && velocita<30)
				tipo="traffico elevato";
			if (velocita>=30 && velocita<40)
				tipo="velocita lenta";
			break;
		case "extraurbana":
			if (velocita<20)
				tipo="coda";
			if (velocita>=20 && velocita<40)
				tipo="traffico elevato";
			if (velocita>=40 && velocita<50)
				tipo="velocita lenta";
			break;
		case "superstrada":
			if (velocita<20)
				tipo="coda";
			if (velocita>=20 && velocita<50)
				tipo="traffico elevato";
			if (velocita>=50 && velocita<70)
				tipo="velocita lenta";
			break;
		case "autostrada":
			if (velocita<20)
				tipo="coda";
			if (velocita>=20 && velocita<60)
				tipo="traffico elevato";
			if (velocita>=60 && velocita<80)
				tipo="velocita lenta";
			break;
			
		}
		this.datoTraffico=new DatoTraffico(this.posizione, tipo, this.velocita);
	}
	public void inviaDatoTraffico() {
		GestoreCentraline.getIstance().segnalaDatabaseS(this.datoTraffico);
		//fare con rmi
	}
	public void calcolaVelocitaMedia(int velocita) {
		int temp=this.numeroVeicoli.getNumeroVeicoli();
		this.velocita=(this.velocita*(temp-1)+velocita)/temp;
	}
	public void setIdCentralinaStradale(int id) {
		this.idCentralinaStradale=id;
	}
	public int getIdCentralinaStradale() {
		return this.idCentralinaStradale;
	}


}
