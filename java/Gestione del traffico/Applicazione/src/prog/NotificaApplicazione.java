package prog;

import java.io.Serializable;

public class NotificaApplicazione extends Notifica implements Serializable{
	//classe per le notifiche inviate e ricevute dall'applicazione
	
	private static final long serialVersionUID = -2638683854845954842L;
	private String tipo;	
	private String mittente;
	public NotificaApplicazione(String mittente,Posizione pos, String tipo) {
		this.tipo=tipo;		//con tipo si intende il tipo di evento di traffico
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
		// metodo per stampare la notifica 
		// viene usato un colore diverso per il tipo di evento occorso
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
				+ inizialiMaiuscole(this.posizione.getVia());
		if(this.getTipo().substring(0,1).equals("M"))
		{
			// niente
		}
		else
		{
			finale += " ad una velocita' media di "
					+ this.getTipo().substring(1, this.getTipo().indexOf(' '))
					+ " km/h";
		}
		finale += "</font>";


		return finale;
	}
	
	private static String inizialiMaiuscole(String stringaDaTrasformare) {
		String[] parole = stringaDaTrasformare.split(" ");

		for(int p=0; p<parole.length; ++p) {
			parole[p] = parole[p].substring(0,1).toUpperCase() + parole[p].substring(1).toLowerCase();
		}
								
		return String.join(" ", parole);
	}
	
}
