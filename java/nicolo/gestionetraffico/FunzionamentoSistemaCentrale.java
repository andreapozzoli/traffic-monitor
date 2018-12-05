package gestionetraffico;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.*;

public class FunzionamentoSistemaCentrale {

	public static MappaGrafica visualizzazioneMappaBase() {
		MappaGrafica mappa = new MappaGrafica();
		mappa.setVisible(true);
		
		return mappa;
	}
	
	public static void posizionaCentraline(ArrayList<CentralinaStradale> listaCS, MappaGrafica mappa) {
		for (Centralina c : listaCS) {
			mappa.aggiungiCentralinaVuota("Centralina", c.getPosizione().getLatitudine(), c.getPosizione().getLongitudine());
		}
			
	}
	
	public static void main(String[] args) {
	
		//instanziazione gestori
		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		GestoreCentraline GCent=GestoreCentraline.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
		
		//� necessario un thread per ogni gestore 
		//thread gestore amministratori 
		while(true) {
			while(true) {
				System.out.println("Digita 'l' se sei gi� registrato, 'r' se sei un nuovo amministratore");
				Scanner sc= new Scanner(System.in);
				String login=sc.nextLine();
				if (login.equals("r")) {
					GAmm.registraAmministratore();
					break;
				}
				else if (login.equals("l")) {
					if(GAmm.login()) {
					break;
					}
					else {
						System.out.println("Username o password non corretti");
					}
				}
			}
		//visualizzazione mappa
		
		MappaGrafica mappa = visualizzazioneMappaBase();
		
		Posizione p1 = new Posizione((float)11.3, (float)11.5);
		Posizione p2 = new Posizione((float)6.3, (float)31.5);

		CentralinaStradale c1 = new CentralinaStradale(10, p1, 3, "extraurbana");
		CentralinaStradale c2 = new CentralinaStradale(50, p2, 3, "urbana");

		ArrayList<CentralinaStradale> listaTest = new ArrayList<CentralinaStradale>();
		listaTest.add(c1);
		listaTest.add(c2);
		
		posizionaCentraline(listaTest, mappa);
		
		while(true) {
			System.out.println("Premi 'm' se vuoi visualizzare la mappa, 'd' se vuoi visualizzare il diagramma,'o' se vuoi fare il logout");
			Scanner sc= new Scanner(System.in);
			String comando=sc.nextLine();
			if (comando.equals("m")) {
				MapMarkerDot dinamica = mappa.aggiungiApplicazioneMobile("App mobile dinamica", 11, 23);
				MapMarkerDot dinamica2 = mappa.aggiungiApplicazioneMobile("App mobile dinamica 2", 70, 23);
				mappa.rimuoviMarcatore(dinamica2);
			}
		else if (comando.equals("d")) {
			//visualizzazione diagramma
		}
		else if (comando.equals("o"));{
			break;
		}
	}
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


