package gestionetraffico;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import jxl.read.biff.BiffException;

public class FunzionamentoCentralinaA {

	public static void main(String[] args) throws BiffException, IOException {
		Posizione posizione=new Posizione();
		CentralinaAutomobilistica centralina=new CentralinaAutomobilistica(100,123);
		//deve solo aspettare che scada l'intervallo di tempo corrente
		//scaduto l'intervallo crea il dato veicolo
		//ci deve essere un thread in cui vi è il funzionamento dei rilevatori
		
		/*CLIENT
		 * System.setSecurityManager(new RMISecurityManager()); 
		   IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup("GestoreCentraline");*/

	}

}
