package prog;

import java.rmi.RemoteException;

public class Login {

	public static boolean autenticazione(String username, String password, String tipoUtente, IGestoreApplicazioni server) throws RemoteException {
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
	
	public static boolean autenticazione(String username,String tipoUtente, IGestoreApplicazioni server) throws RemoteException {
		// metodo per verificare che l'username immesso dall'utente che si sta registrando non sia gia' in uso
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



