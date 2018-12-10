package gestionetraffico;
import java.util.*;

public class DatoGenerico {
	private String data;
	private String ora;
	private Posizione posizione;
	private String tipo;
	
	public DatoGenerico() {
		
	}
	
	public DatoGenerico(Posizione posizione, String tipo, String data, String ora) {
		this.posizione=posizione;
		this.tipo=tipo;
		this.data=data; 
		this.ora=ora;
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

}
