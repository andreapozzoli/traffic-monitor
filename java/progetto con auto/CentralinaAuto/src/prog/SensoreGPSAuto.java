package prog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SensoreGPSAuto extends SensoreGPS{

	private ArrayList<Posizione> listaPosizioni;

	public SensoreGPSAuto(Posizione pos) throws BiffException, IOException {
		super();
		this.listaPosizioni=new ArrayList<Posizione>();
		this.posizione=pos;
	}
	private static final long serialVersionUID = 4376387777459825101L;

	public void calcolaListaPosizioni(double raggio) throws BiffException, IOException {

		this.listaPosizioni.clear();
		if (raggio!=0) {		
			double R = 6378.137;
			double lat1=this.posizione.getLatitudine();
			double lat2;
			double lon1=this.posizione.getLongitudine();
			double lon2;
			double dLat; 
			double dLon; 
			double a;
			double c;
			double d;
			String via;
			String percorsoCorrente = System.getProperty("user.dir");


			Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls")); // file excel contenente nome via e relative latitudine e longitudine

			Sheet sheet = wb.getSheet(0);

			int miavar;
			for (miavar=0; miavar<543;miavar++) {


				Cell cella = sheet.getCell(0,miavar); // nella prima colonna vi e' il nome della via
				via = cella.getContents();
				cella=sheet.getCell(1,miavar); // nella seconda colonna vi e' la latitudine
				lat2=Double.valueOf(cella.getContents());
				cella=sheet.getCell(2,miavar); // nella terza colonna vi e' la longitudine
				lon2=Double.valueOf(cella.getContents());

				dLat = lat1 * Math.PI/180 - lat2 * Math.PI/180; 
				dLon = lon1 * Math.PI/180 - lon2 * Math.PI/180; 
				a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI/180) * Math.cos(lat2 * Math.PI/180) * Math.sin(dLon/2) * Math.sin(dLon/2); 
				c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
				d = R * c;

				if (d<raggio) {
					this.listaPosizioni.add(new Posizione(via, lat2, lon2)); 
				}

			}

		}
	}

	public Posizione rilevaPosizione() throws BiffException, IOException {
		if (!this.listaPosizioni.isEmpty()) {
			Random random = new Random();
			int min=0;
			int max=this.listaPosizioni.size()-1;
			int intorno = ((max-min) + 1);
			int indice=random.nextInt(intorno) + min;
			this.posizione=this.listaPosizioni.get(indice);
		}
		System.out.println("posizione: "+this.posizione.getVia());
		return this.posizione;
	}

}
