package prog;
import java.rmi.*;

public class CentralinaStradaleClient {

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager()); 
		IGestoreCentraline iGestoreCentraline = (IGestoreCentraline)Naming.lookup("GestoreCentraline");
		

	}

}
