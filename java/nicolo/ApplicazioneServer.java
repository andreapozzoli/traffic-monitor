import java.rmi.*;

public class ApplicazioneServer {

	public static void main(String[] args) throws Exception{
		System.setSecurityManager(new RMISecurityManager());
		ApplicazioneMobile applicazioneMobile= new ApplicazioneMobile();
		Naming.rebind("ApplicazioneMobile", applicazioneMobile);
		

	}

}
