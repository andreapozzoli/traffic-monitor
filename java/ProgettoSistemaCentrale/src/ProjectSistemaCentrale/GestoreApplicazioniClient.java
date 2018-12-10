package ProjSistemaCentrale;
import java.rmi.*;

public class GestoreApplicazioniClient implements Runnable {

	public  void run(){
		System.setSecurityManager(new RMISecurityManager());
		try {
			System.out.println("Security Manager loaded");
			String url="rmi://localhost/ApplicazioneMobile";
			IApplicazioneMobile iApplicazioneMobile = (IApplicazioneMobile) Naming.lookup(url);
			System.out.println("Got remote object");
			System.out.println(iApplicazioneMobile.getIdentificativo());
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
