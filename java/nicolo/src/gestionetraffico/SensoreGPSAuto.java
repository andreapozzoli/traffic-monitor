package gestionetraffico;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SensoreGPSAuto extends SensoreGPS{
	public SensoreGPSAuto() throws BiffException, IOException
	{
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
		//System.out.println(via);
		cella=sheet.getCell(1,miavar);
		//System.out.println(cella.getContents());
		String lat=cella.getContents();
		latitudine=Double.valueOf(cella.getContents());
		cella=sheet.getCell(2,miavar);
		//System.out.println(cella.getContents());
		longitudine=Double.valueOf(cella.getContents());


		this.posizione=new Posizione(via,latitudine,longitudine);
		return this.posizione;

	}

	public static void main(String[] args) throws BiffException, IOException
	{

		try {
			String percorsoCorrente = System.getProperty("user.dir");


			Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls"));
			Sheet sheet = wb.getSheet(0);
			Cell c = sheet.getCell(0,0);
			String stringa1 = c.getContents();
			//System.out.print(stringa1);
		}
		catch(java.io.IOException e) {
			System.out.println("Errore di lettura del file");
		}


	}



}
