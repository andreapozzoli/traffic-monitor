package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class GestoreCentralineServer implements Runnable {

	public void run()  {
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(12344);

			registry.rebind("gestCent", GestoreCentraline.getInstance());
			System.out.println("Server online");

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Il sistema per la gestione delle centraline è già in uso");
		} 

	}
}
