package prog;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

import java.io.*;

public class SensoreGPSTelefono extends SensoreGPS implements Serializable{
	
	private static final long serialVersionUID = -961906414779596754L;

	public SensoreGPSTelefono () throws BiffException, IOException {
		super();
	}
	
	public Posizione rilevaPosizione() throws BiffException, IOException {
		// metodo per la rilevazione casuale della posizione
		double latitudine=0;		
		double longitudine=0;
		String via;
		String percorsoCorrente = System.getProperty("user.dir");


		Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls")); // file excel contenente nome via e relative latitudine e longitudine
		
		Sheet sheet = wb.getSheet(0);
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 543; // numero massimo
		int c = ((b-a) + 1);
		int miavar = random.nextInt(c) + a;
		
		
		
		Cell cella = sheet.getCell(0,miavar); // nella prima colonna vi e' il nome della via
		via = cella.getContents();
		cella=sheet.getCell(1,miavar); // nella seconda colonna vi e' la latitudine
		latitudine=Double.valueOf(cella.getContents());
		cella=sheet.getCell(2,miavar); // nella terza colonna vi e' la longitudine
		longitudine=Double.valueOf(cella.getContents());
		
		
		this.posizione=new Posizione(via,latitudine,longitudine); // viene creata la posizione
		return this.posizione;
		
	}
	
	

}
