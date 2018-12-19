package prog;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FunzionamentoCentralinaA {
	
	public static void main(String[] args) throws BiffException, IOException {
		Random random = new Random();
		int minVel =0;
		int maxVel=110;
		int intornoVel = ((maxVel-minVel) + 1);
		int velocita= random.nextInt(intornoVel) + minVel;
		Random random2 = new Random();
		int minPos =0;
		int maxPos=543;
		int intornoPos = ((maxPos-minPos) + 1);
		int posizione= random2.nextInt(intornoPos) + minPos;
		String via;
		String percorsoCorrente = System.getProperty("user.dir");
		Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls")); // file excel contenente nome via e relative latitudine e longitudine
		Sheet sheet = wb.getSheet(0);		
		Cell cella = sheet.getCell(0,posizione); // nella prima colonna vi e' il nome della via
		via = cella.getContents();
		cella=sheet.getCell(1,posizione); // nella seconda colonna vi e' la latitudine
		double lat2=Double.valueOf(cella.getContents());
		cella=sheet.getCell(2,posizione); // nella terza colonna vi e' la longitudine
		double lon2=Double.valueOf(cella.getContents());
		CentralinaAuto auto=new CentralinaAuto(new Posizione(via, lat2, lon2), velocita);
		Thread t1=new Thread(auto);
		t1.start();
	}

}
