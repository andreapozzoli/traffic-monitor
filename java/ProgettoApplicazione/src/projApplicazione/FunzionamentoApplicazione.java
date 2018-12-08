package ProjApplicazione;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Random;
import java.util.Scanner;

import jxl.read.biff.BiffException;

public class FunzionamentoApplicazione {

	public static void main(String[] args) throws IOException, BiffException  {
	

		ApplicazioneMobile app1=new ApplicazioneMobile(123);
		
		Thread t1=new Thread(new ApplicazioneClient(app1));
		t1.start();
		
		Thread t2=new Thread(new ApplicazioneServer(app1));
		t2.start();
		
	
	}

}
