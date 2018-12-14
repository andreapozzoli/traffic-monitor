package prog;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ApplicazioneServer implements Runnable {
	
private ApplicazioneMobile applicazione;
	
	public ApplicazioneServer(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		//System.setSecurityManager(new RMISecurityManager());
		//Naming.rebind("ApplicazioneMobile", this.applicazione);
		
		
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(12346+this.applicazione.getIdentificativo());
			registry.rebind("appServer", this.applicazione);
			System.out.println("Server online");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		

	}

}
