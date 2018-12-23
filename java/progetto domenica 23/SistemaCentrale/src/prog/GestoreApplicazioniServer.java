package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;


public class GestoreApplicazioniServer implements Runnable{

	public void run(){

		Registry registry = null;
		try {
			// viene caricata l'interfaccia del GestoreApplicazioni necessaria per la comunicazione rmi con l'applicazione e configurato il lato server
			registry = LocateRegistry.createRegistry(12345);

			registry.rebind("gestApp", GestoreApplicazioni.getInstance());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Errore di connessione.\n"
							+ "\nIl sistema e' gia' in uso.",
							"Errore di connessione",
							JOptionPane.ERROR_MESSAGE);

		}

	}
}
