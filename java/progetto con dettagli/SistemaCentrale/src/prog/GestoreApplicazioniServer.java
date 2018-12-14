package prog;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GestoreApplicazioniServer implements Runnable{

	public void run(){
		//System.setSecurityManager(new RMISecurityManager());
		//GestoreApplicazioni gestoreApplicazioni= GestoreApplicazioni.getInstance();
		//Naming.rebind("GestoreApplicazioni", gestoreApplicazioni);
		
		
		Registry registry = null;
	try {
		registry = LocateRegistry.createRegistry(12345);
		
		registry.rebind("gestApp", GestoreApplicazioni.getInstance());
		System.out.println("Server online");
		
	} catch (Exception e) {
		e.printStackTrace();
	} 

	}

}
