package gestionetraffico;

import java.util.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class FunzionamentoCentralinaS {

	public static void main(String[] args) {
		Posizione posizione=new Posizione();
		CentralinaStradale centralina=new CentralinaStradale(100,posizione,10,"urbana");
		
		//thread rilevatore veicoli
		Thread t1=new Thread(centralina.getRilevatoreVeicoli());
		t1.start();
		
		//deve solo aspettare che scada l'intervallo di tempo corrente
		//scaduto l'intervallo calcola immediatamente quello successivo e crea il dato di traffico
		Thread t2=new Thread();
		Timer     timer = new Timer();
		// aspetta 10 secondi prima dell'esecuzione
		timer.schedule( centralina, 10000 );
		
		/*CLIENT
		 * System.setSecurityManager(new RMISecurityManager()); 
		   IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup("GestoreCentraline");*/
		


	}

}
