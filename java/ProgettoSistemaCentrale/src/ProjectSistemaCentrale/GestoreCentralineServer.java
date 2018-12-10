package ProjectSistemaCentrale;
import java.rmi.*;

public class GestoreCentralineServer {

public void run() {
    	
		
		try {
			System.setSecurityManager(new RMISecurityManager());
			GestoreCentraline gestoreCentraline = GestoreCentraline.getInstance();
			Naming.rebind("GestoreCentraline", gestoreCentraline);
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
