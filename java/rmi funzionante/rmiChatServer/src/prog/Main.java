package prog;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main implements Runnable{

	/**
	 * @param args
	 */
	public void run() {
		
		try {
			   ChatServer Hello = ChatServer.getInstance();			   		   
			   Naming.rebind("rmi://localhost/ABC", Hello);

			   System.out.println("Addition Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Addition Server failed: " + e);
				}
		
		
		
		
		
		/*	Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(12345);
			
			registry.rebind("chatServer", ChatServer.getInstance());
			System.out.println("Server online");
			
		} catch (Exception e) {
			e.printStackTrace();
		} */
	}

}
