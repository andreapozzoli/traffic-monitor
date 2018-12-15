package prog;
import java.io.Serializable;
import java.util.*;

public abstract class Notifica implements Serializable{
	
	private static final long serialVersionUID = -6817643823377378061L;
	protected String data;
	protected String ora;
	protected Posizione posizione;
	protected int minutoArrivo;
	protected int oraArrivo;

	public void setData() {
		GregorianCalendar dat = new GregorianCalendar();
		this.data = dat.get(Calendar.DAY_OF_MONTH)+ "/" + (dat.get(Calendar.MONTH)+1) + "/" + dat.get(Calendar.YEAR);		
	}

	public void setOra() {
		GregorianCalendar dat = new GregorianCalendar();
		this.ora = dat.get(Calendar.HOUR) + "." + dat.get(Calendar.MINUTE) + "." + dat.get(Calendar.SECOND);
		this.oraArrivo=dat.get(Calendar.HOUR);
		this.minutoArrivo=dat.get(Calendar.MINUTE);
	}
	
	public int getMinA() {
		return this.minutoArrivo;
	}
	
	public int getOraA() {
		return this.oraArrivo;
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
