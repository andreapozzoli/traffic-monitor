package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class GestoreCentralineServer implements Runnable {

	public void run()  {
		Registry registry = null;
		try {
			// viene caricata l'interfaccia server del GestoreCentraline
			registry = LocateRegistry.createRegistry(12344);

			registry.rebind("gestCent", GestoreCentraline.getInstance());
			System.out.println("Server online");
			FunzionamentoSistemaCentrale.loginGrafico();

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Il sistema per la gestione delle centraline e' gia'  in uso");
		} 

	}
}
