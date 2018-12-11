package gestionetraffico;

import java.util.ArrayList;

public class NotificaApplicazione extends Notifica {
	private String tipo;	
	private String mittente;
	public NotificaApplicazione(String mittente,Posizione pos, String tipo) {
		this.tipo=tipo;
		this.setData(); 
		this.setOra();
		this.posizione=pos;
		this.mittente=mittente;
	}

	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public String getMittente() {
		return this.mittente;
	}
	
	public void setMittente(String mittente) {
		this.tipo=mittente;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	//nuovo
	public String stampaNotifica() {
		
		String finale = new String();
		
		if(this.getTipo().substring(this.getTipo().indexOf(' ')+1).equals("Coda"))
		{
			finale = "<font color=\"red\">";
		}
		else if(this.getTipo().substring(this.getTipo().indexOf(' ')+1).equals("Traffico elevato"))
		{
			finale = "<font color=\"black\">";
		}
		else if(this.getTipo().substring(this.getTipo().indexOf(' ')+1).equals("Velocita lenta"))
		{
			finale = "<font color=\"blue\">";
		}
		else
		{
			finale = "<font color=\"green\">";
		}
		finale += this.getData()
				+ ", "
				+ this.getOra()
				+ " | "
				+ this.getTipo().substring(this.getTipo().indexOf(' ')+1)
				+ " in "
				+ this.posizione.getVia();
		if(this.getTipo().substring(0,1).equals("M"))
		{
			// niente
		}
		else
		{
			finale += " ad una velocità media di "
					+ this.getTipo().substring(1, this.getTipo().indexOf(' '))
					+ " km/h";
		}
		finale += "</font>";


		return finale;
		//return ("tipo: "+this.tipo+" via: "+this.posizione.getVia()+ " posizione: "+this.posizione.getLatitudine()+" ; "+this.posizione.getLongitudine());
	}
	
	
}
