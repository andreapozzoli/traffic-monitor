package ProjSistemaCentrale;
import java.rmi.*;

public class GestoreCentralineServer {

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager()); 
		GestoreCentraline gestoreCentraline = GestoreCentraline.getInstance();
		//Naming.rebind("GestoreCentraline", gestoreCentraline);

	}

}
