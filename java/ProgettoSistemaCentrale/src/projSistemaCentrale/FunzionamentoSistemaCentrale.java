package projSistemaCentrale;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.Demo;

public class FunzionamentoSistemaCentrale {

	public static void main(String[] args) {
		//instanziazione gestori
		GestoreApplicazioni gApp=GestoreApplicazioni.getInstance();
		GestoreCentraline gCent=GestoreCentraline.getInstance();
		GestoreUtenti gUt=GestoreUtenti.getInstance();
		GestoreAmministratori gAmm=GestoreAmministratori.getInstance();
		
		//� necessario un thread per ogni gestore 
		//thread gestore amministratori 
		Thread t1=new Thread(gAmm);
		t1.start();
		//fine gestore amministratori
		
		//thread gestore centraline
		Thread t2=new Thread(gCent);
		//fine gestore centraline
		
		//thread client gestore applicazioni
		Thread t3=new Thread(new GestoreApplicazioniClient());
		//fine gestore applicazioni client
		
		//thread gestore applicazioni server
		Thread t4=new Thread(new GestoreApplicazioniServer());
		//fine gestore applicazioni server
		
		
		
			
		
		

	}

}
