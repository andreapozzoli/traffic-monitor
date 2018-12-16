package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;


public class GestoreApplicazioniServer implements Runnable{

	public void run(){

		Registry registry = null;
		try {
			// viene caricata l'interfaccia del GestoreApplicazioni necessaria per la comunicazione rmi
			registry = LocateRegistry.createRegistry(12345);

			registry.rebind("gestApp", GestoreApplicazioni.getInstance());
			System.out.println("Server online");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il sistema per la gestione delle applicazioni mobili e' gia'  in uso");
		} 

	}

}
