package gestionetraffico;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

public class FunzionamentoSistemaCentrale {

	public static void main(String[] args) {
		//instanziazione gestori
		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		GestoreCentraline GCent=GestoreCentraline.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
		//è necessario un thread per ogni gestore 
		//thread gestore amministratori 
		System.out.println("Premi 'm' se vuoi visualizzare la mappa, 'd' se vuoi visualizzare il diagramma");
		Scanner sc= new Scanner(System.in);
		String comando=sc.nextLine();
		if (comando.equals("m")) {
			//visualizzazione mappa
		}
		else if (comando.equals("d")) {
			//visualizzazione diagramma
		}
		//fine gestore amministratori
		/*SERVER GESTORE APPLICAZIONI
		 * System.setSecurityManager(new RMISecurityManager());
		   GestoreApplicazioni gestoreApplicazioni= GestoreApplicazioni.getInstance();
		   Naming.rebind("GestoreApplicazioni", gestoreApplicazioni);*/
		
		/*CLIENT GESTORE APPLICAZIONI
		 *System.setSecurityManager(new RMISecurityManager());
		  IApplicazioneMobile iApplicazioneMobile = (IApplicazioneMobile) Naming.lookup("ApplicazioneMobile");*/
		
		/*SERVER GESTORE CENTRALINE
		 * System.setSecurityManager(new RMISecurityManager()); 
		   GestoreCentraline gestoreCentraline = GestoreCentraline.getInstance();
		   Naming.rebind("GestoreCentraline", gestoreCentraline);*/
			
		
		

	}

}
