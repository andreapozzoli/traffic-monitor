package prog;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main implements Runnable{

	/**
	 * @param args
	 */
	public void run() {
		
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(12345);
			
			registry.rebind("chatServer", ChatServer.getInstance());
			System.out.println("Server online");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
