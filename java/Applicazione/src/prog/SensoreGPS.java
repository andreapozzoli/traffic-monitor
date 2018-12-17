package prog;

import jxl.read.biff.BiffException;
import java.io.IOException;
import java.io.Serializable;

public abstract class SensoreGPS implements Serializable{
	
	private static final long serialVersionUID = -5390680567909102648L;
	protected Posizione posizione;
	protected static Posizione listaPosizioni;
	
	public SensoreGPS() throws BiffException, IOException {

	}
	
	public abstract Posizione rilevaPosizione() throws BiffException, IOException ;
	
	
}
