import java.rmi.*;

public class CentralinaAutomobilisticaClient {

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager()); 
		IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup("GestoreCentraline");
		
		
	}

}
