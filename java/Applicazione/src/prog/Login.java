package prog;

import java.rmi.RemoteException;

// basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class Login {

	public static boolean authenticate(String username, String password, String tipoUtente, IGestoreApplicazioni server) throws RemoteException {
		// metodo per verificare che l'utente che sta facendo il login sia registrato
		if(tipoUtente.equals("U"))
		{
			if(server.verificaAccesso(username, password)) {

				return true;
			}

			return false;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean authenticate(String username,String tipoUtente, IGestoreApplicazioni server) throws RemoteException {
		// metodo per verificare che l'username immesso dall'utente che si sta registrando non sia già in uso
		if(tipoUtente.equals("U"))
		{
			if(server.verificaAccesso(username)) {

				return true;
			}

			return false;
		}
		else
		{
			return false;
		}
	}
}



