package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;



public class GestCentAutoServer implements Runnable {

	public void run()  {
		Registry registry = null;
		try {
			// viene caricata l'interfaccia server del GestoreCentraline e configurato il lato server della comunicazione rmi con le centraline
			registry = LocateRegistry.createRegistry(13344);

			registry.rebind("gestCentAuto", GestoreCentraline.getInstance());
		

		} catch (Exception e) {
				
		} 

	}
}
