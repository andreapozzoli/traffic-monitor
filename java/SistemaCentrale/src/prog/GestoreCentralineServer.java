package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class GestoreCentralineServer implements Runnable {

	public void run()  {
		Registry registry = null;
		try {
			// viene caricata l'interfaccia server del GestoreCentraline e configurato il lato server della comunicazione rmi con le centraline
			registry = LocateRegistry.createRegistry(12344);

			registry.rebind("gestCent", GestoreCentraline.getInstance());
			FunzionamentoSistemaCentrale.loginGrafico();

		} catch (Exception e) {
			
		} 

	}
}
