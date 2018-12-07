package gestionetraffico;
import java.rmi.*;

public class GestoreApplicazioniServer {

	public static void main(String[] args) throws Exception{
		System.setSecurityManager(new RMISecurityManager());
		GestoreApplicazioni gestoreApplicazioni= GestoreApplicazioni.getInstance();
		//Naming.rebind("GestoreApplicazioni", gestoreApplicazioni);
		
		

	}

}
