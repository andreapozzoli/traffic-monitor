package gestionetraffico;
import java.rmi.*;

public class ApplicazioneServer implements Runnable {
	
private ApplicazioneMobile applicazione;
	
	public ApplicazioneServer(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		//System.setSecurityManager(new RMISecurityManager());
		//Naming.rebind("ApplicazioneMobile", this.applicazione);
		

	}

}
