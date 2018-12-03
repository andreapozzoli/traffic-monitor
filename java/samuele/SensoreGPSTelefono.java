package gestionetraffico;
import java.util.*;
import java.io.*;

public class SensoreGPSTelefono extends SensoreGPS {
	
	public SensoreGPSTelefono () {
	}
	
	public Posizione rilevaPosizione() {
		try {
			float latitudine=0;
		
		float longitudine=0;
		Random random=new Random();
		
		//accesso a file
		 FileReader f;
		 File file = new File("C:/Users/semmo/Desktop/vie.txt");
		 f=new FileReader(file);

		    BufferedReader b;
		    b=new BufferedReader(f);
		    String s;

		    s=b.readLine();
		    
		    System.out.println(Double.parseDouble(s));
		
		latitudine = random.nextFloat();

		longitudine = random.nextFloat();
		this.posizione=new Posizione(latitudine, longitudine);


		}
		finally {
		return this.posizione;
		}
		
	}
	
	

}
