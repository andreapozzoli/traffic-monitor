package prog;
import java.util.*;

public class DatoGenerico {
	private String data;
	private String ora;
	private Posizione posizione;
	private String tipo;
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
		GregorianCalendar dat = new GregorianCalendar();
		this.data = dat.get(Calendar.DAY_OF_MONTH)+ "/" + dat.get(Calendar.MONTH) + "/" + dat.get(Calendar.YEAR);		
	}
	
	public void setOra() {
		GregorianCalendar dat = new GregorianCalendar();
		this.ora = dat.get(Calendar.HOUR) + "." + dat.get(Calendar.MINUTE) + "." + dat.get(Calendar.SECOND);
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
