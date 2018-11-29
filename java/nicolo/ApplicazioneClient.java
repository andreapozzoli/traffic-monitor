import java.rmi.*;

public class ApplicazioneClient {

	public static void main(String[] args) throws Exception{
		System.setSecurityManager(new RMISecurityManager());
		IGestoreApplicazioni iGestoreApplicazioni=(IGestoreApplicazioni) Naming.lookup("GestoreApplicazioni");
		

	}

}
