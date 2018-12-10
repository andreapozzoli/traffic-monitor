package ProjectSistemaCentrale;
import java.rmi.*;


public class GestoreApplicazioniServer implements Runnable{

	public void run(){
		
		try {
			System.setSecurityManager(new RMISecurityManager());
			GestoreApplicazioni gestoreApplicazioni= GestoreApplicazioni.getInstance();
			Naming.rebind("GestoreApplicazioni", gestoreApplicazioni);
			System.out.println("Server waiting.....");
			}
			catch (java.net.MalformedURLException me){
				System.out.println("Malformed URL: "+me.toString());
			}
			catch (RemoteException re) {
				System.out.println("Remote exception: "+re.toString());
			}

	}

}
