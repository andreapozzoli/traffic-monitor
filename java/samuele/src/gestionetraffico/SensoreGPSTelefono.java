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
		
		float latitudine=0;		
		float longitudine=0;
		String via;
		Workbook wb= Workbook.getWorkbook(new File("C://Users//semmo//Documents//eclipse//gestione traffico 2/vie.xls"));
		Sheet sheet = wb.getSheet(0);
		Random random = new Random();
		int a = 0; // numero minimo
		int b = 6; // numero massimo
		int c = ((b-a) + 1);
		int miavar = random.nextInt(c) + a;
		
		
		Cell cella = sheet.getCell(0,miavar);
		via = cella.getContents();
		cella=sheet.getCell(1,miavar);
		latitudine=Float.valueOf(cella.getContents());
		cella=sheet.getCell(2,miavar);
		longitudine=Float.valueOf(cella.getContents());
		
		
		
		this.posizione=new Posizione(via,latitudine,longitudine);
		return this.posizione;
		
	}
	
	

}
