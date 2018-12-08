package ProjApplicazione;

import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Random;




public abstract class SensoreGPS {
	protected Posizione posizione;
	protected static Posizione listaPosizioni;
	
	public SensoreGPS() throws BiffException, IOException {

	}
	
	public abstract Posizione rilevaPosizione() throws BiffException, IOException ;
	
	
}
