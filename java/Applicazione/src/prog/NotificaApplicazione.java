package prog;

import java.io.Serializable;


public class NotificaApplicazione extends Notifica implements Serializable{


	private static final long serialVersionUID = -2638683854845954842L;
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

	public String stampaNotifica() {

		/* La stringa è in formato HTML per permettere 
		 * all'utente di capire rapidamente la tipologia
		 * degli eventi di traffico che lo circondano.
		 * Con questo formato, infatti, è possibile scegliere
		 * colori diversi per notifiche diverse.
		 */
		String finale = new String();

		// Impostazione del colore in base al tipo di notifica
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
		else // Evento non definito (ad esempio traffico nella norma, oppure una stringa generica)
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
		if(this.getTipo().substring(0,1).equals("M")) // M si riferisce alle applicazioni mobili
		{
			// non viene aggiunto nulla perché le applicazioni mobili non registrano la velocità
		}
		else // caso generico (il formato standard è S per le centraline stradali)
		{
			finale += " ad una velocità media di "
					+ this.getTipo().substring(1, this.getTipo().indexOf(' '))
					+ " km/h";
		}
		finale += "</font>";


		return finale;
	}	
}
