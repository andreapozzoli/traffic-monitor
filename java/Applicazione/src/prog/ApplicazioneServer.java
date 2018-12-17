package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ApplicazioneServer implements Runnable {
	
private ApplicazioneMobile applicazione;
	
	public ApplicazioneServer(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		//lato server dell'applicazione, configura la connessione rmi con GestoreApplicazioni
		Registry registry = null;
		try {
			//il numero di porta è calcolato in base all'id dell'applicazione
			registry = LocateRegistry.createRegistry(12346+this.applicazione.getIdentificativo());
			registry.rebind("appServer", this.applicazione);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
