package ProjSistemaCentrale;
import java.util.*;

public abstract class Notifica {
	protected String data;
	protected String ora;
	protected Posizione posizione;
	
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
	
	public void setPosizione(Posizione pos) {
		this.posizione = pos;		
	}
	
	public Posizione getPosizione() {
		return this.posizione;
	}
	
	

}
