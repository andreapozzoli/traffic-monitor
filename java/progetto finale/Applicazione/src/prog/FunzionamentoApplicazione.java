package prog;

import java.io.*;

import jxl.read.biff.BiffException;

public class FunzionamentoApplicazione {

	public static void main(String[] args) throws IOException, BiffException  {
	

		ApplicazioneMobile app1=new ApplicazioneMobile(); // creazione nuova applicazione
		
		ApplicazioneClient appClient=new ApplicazioneClient(app1); 
		
		Thread t1=new Thread(appClient); // viene fatto partire il lato client dell'applicazione
		t1.start();
		
	
	}

}
