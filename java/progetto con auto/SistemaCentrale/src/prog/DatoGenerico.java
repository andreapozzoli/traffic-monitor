package prog;
import java.util.*;

public class DatoGenerico { // classe per salvare le notifiche ricevute nel database e ricondurle quindi ad un unico tipo
	private String data;
	private String ora;
	private Posizione posizione;
	private String tipo;		//con tipo si intende il tipo di evento di traffico
	private int minutoArrivo;
	private int oraArrivo;
	
	public DatoGenerico() {
		
	}
	
	public DatoGenerico(Posizione posizione, String tipo, String data, String ora, int minutoArrivo, int oraArrivo) {
		this.posizione=posizione;
		this.tipo=tipo;
		this.data=data; 
		this.ora=ora;
		this.minutoArrivo=minutoArrivo; //intero indicante il minuto di arrivo della notifica necessario per la rimozione delle notifiche dalla mappa dopo un certo tempo
		this.oraArrivo=oraArrivo; //intero indicante l'ora di arrivo della notifica necessario per la rimozione delle notifiche dalla mappa dopo un certo tempo
	}
	public void setData() {
		//per settare la data del dato generico
		GregorianCalendar dat = new GregorianCalendar();
		this.data = dat.get(Calendar.DAY_OF_MONTH)+ "/" + dat.get(Calendar.MONTH) + "/" + dat.get(Calendar.YEAR);		
	}
	
	public void setOra() {
		//per settare l'ora del dato generico
		//si mette l'ora nel formato hh:mm:ss, quindi se i valori rilevati sono minori di 10, viene anteposto uno zero
				GregorianCalendar dat = new GregorianCalendar();
				int a=dat.get(Calendar.HOUR);
				String a1="";
				if (a<10) {
					a1="0"+a;
				}
				else {
					a1=""+a;
				}
				int b=dat.get(Calendar.MINUTE);
				String b1="";
				if (b<10) {
					b1="0"+b;
				}
				else {
					b1=""+b;
				}
				int c=dat.get(Calendar.SECOND);
				String c1="";
				if (c<10) {
					c1="0"+c;
				}
				else {
					c1=""+c;
				}
				this.ora = a1 + ":" + b1 + ":" + c1;	
	}
	
	public String getData() {
		return this.data;
	}
	
	public String getOra() {
		return this.ora;
	}
	public Posizione getPosizione(){
		return this.posizione;
	}
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
	public String getTipo(){
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}

	public int getMinA() {
		return this.minutoArrivo;
	}
	
	public int getOraA() {
		return this.oraArrivo;
	}
	
	@Override
	public boolean equals(Object obj) {
		// override del metodo equals della classe Object necessario per comparare questo tipo di notifiche 
		DatoGenerico b=(DatoGenerico)obj;
		if (this.posizione.getVia().equals(b.getPosizione().getVia())&&
				this.data.equals(b.getData())&&this.ora.equals(b.getOra())&&
				this.tipo.equals(b.getTipo())&&this.minutoArrivo==b.getMinA()&&
				this.oraArrivo==b.getOraA()) {
			return true;
		}
		return false;
	}
	
}
