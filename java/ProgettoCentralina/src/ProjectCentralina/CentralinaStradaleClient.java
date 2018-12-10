package ProjCentralina;
import java.rmi.*;

import ProjSistemaCentrale.IApplicazioneMobile;

public class CentralinaStradaleClient implements Runnable{

	public void run() {
		
		System.setSecurityManager(new RMISecurityManager());
		try {
			
			System.out.println("Security Manager loaded");
			String url="rmi://localhost/GestoreCentraline";
			IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup(url);
			System.out.println("Got remote object");
			
			
		}
		catch (RemoteException exc) {
			System.out.println("Error in lookup: "+exc.toString());
		}
		catch (java.net.MalformedURLException exc) {
			System.out.println("malformed URL: "+exc.toString());
		}
		catch (NotBoundException exc) {
			System.out.println("NotBound: "+exc.toString());
		}
		

	}

}
