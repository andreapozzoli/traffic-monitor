package prog;

import java.io.*;

import jxl.read.biff.BiffException;

public class FunzionamentoApplicazione {

	public static void main(String[] args) throws IOException, BiffException  {
	

		ApplicazioneMobile app1=new ApplicazioneMobile();
		
		ApplicazioneClient appClient=new ApplicazioneClient(app1);
		
		Thread t1=new Thread(appClient);
		t1.start();
		
	
	}

}