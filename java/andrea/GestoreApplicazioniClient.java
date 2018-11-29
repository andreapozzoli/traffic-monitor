import java.rmi.*;

public class GestoreApplicazioniClient {

	public static void main(String[] args) throws Exception{
		System.setSecurityManager(new RMISecurityManager());
		IApplicazioneMobile iApplicazioneMobile = (IApplicazioneMobile) Naming.lookup("ApplicazioneMobile");


	}

}
