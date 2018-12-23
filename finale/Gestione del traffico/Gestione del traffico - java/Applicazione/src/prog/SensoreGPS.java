package prog;

import jxl.read.biff.BiffException;
import java.io.IOException;
import java.io.Serializable;

public abstract class SensoreGPS implements Serializable{
	//questa classe e' stata implementata perche' se si volesse creare un altro tipo di sensore GPS si potrebbe ereditare la struttura
	private static final long serialVersionUID = -5390680567909102648L;
	protected Posizione posizione;
	protected static Posizione listaPosizioni;
	
	public SensoreGPS() throws BiffException, IOException {

	}
	
	public abstract Posizione rilevaPosizione() throws BiffException, IOException ;
	
	
}
