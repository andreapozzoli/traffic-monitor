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
		// metodo per rilevare la data del momento in cui viene creata la notifica
		GregorianCalendar dat = new GregorianCalendar();
		this.data = dat.get(Calendar.DAY_OF_MONTH)+ "/" + (dat.get(Calendar.MONTH)+1) + "/" + dat.get(Calendar.YEAR);		
	}

	public void setOra() {
		// metodo per rilevare l'ora del momento in cui viene creata la notifica
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
		this.ora = a1 + ":" + b1 + ":" + c1;		this.oraArrivo=dat.get(Calendar.HOUR);
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
