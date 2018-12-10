package ProjectApplicazione;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class ApplicazioneServer implements Runnable {
	
private ApplicazioneMobile applicazione;
	
	public ApplicazioneServer(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		/*try {
		System.setSecurityManager(new RMISecurityManager());
		ApplicazioneMobile Server=this.applicazione;
		Naming.rebind("ApplicazioneMobile", Server);
		System.out.println("Server waiting.....");
		}
		catch (java.net.MalformedURLException me){
			System.out.println("Malformed URL: "+me.toString());
		}
		catch (RemoteException re) {
			System.out.println("Remote exception: "+re.toString());
		}
		*/
		
		
		/*System.setProperty("java.security.policy", "grantFile.policy");
		Registry serverRegistry=null;
		
			while(serverRegistry==null) {
		
					try {
						serverRegistry=LocateRegistry.createRegistry(23456);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		
			}*/

	}

}