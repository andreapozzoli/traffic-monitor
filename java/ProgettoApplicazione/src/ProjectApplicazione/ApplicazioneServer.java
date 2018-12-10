package ProjectApplicazione;
import java.rmi.*;

public class ApplicazioneServer implements Runnable {
	
private ApplicazioneMobile applicazione;
	
	public ApplicazioneServer(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		try {
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
		

	}

}