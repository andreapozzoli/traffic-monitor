package gestionetraffico;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

import java.io.*;

public class SensoreGPSTelefono extends SensoreGPS {
	
	public SensoreGPSTelefono () throws BiffException, IOException {
		super();
	}
	
	public Posizione rilevaPosizione() throws BiffException, IOException {
		
		double latitudine=0;		
		double longitudine=0;
		String via;
		String percorsoCorrente = System.getProperty("user.dir");


		Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls"));
		
		Sheet sheet = wb.getSheet(0);
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 543; // numero massimo
		int c = ((b-a) + 1);
		int miavar = random.nextInt(c) + a;
		
		
		
		Cell cella = sheet.getCell(0,miavar);
		via = cella.getContents();
		cella=sheet.getCell(1,miavar);
		String lat=cella.getContents();
		latitudine=Double.valueOf(cella.getContents());
		cella=sheet.getCell(2,miavar);
		longitudine=Double.valueOf(cella.getContents());
		
		
		this.posizione=new Posizione(via,latitudine,longitudine);
		return this.posizione;
		
	}
	
	

}
