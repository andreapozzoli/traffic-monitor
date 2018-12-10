package ProjectApplicazione;

import java.rmi.RemoteException;

// basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class Login {

	public boolean authenticate(String username, String password, String tipoUtente, IGestoreApplicazioni iGestoreApplicazioni) throws RemoteException {
		if(tipoUtente.equals("U"))
		{
			if(iGestoreApplicazioni.verificaAccesso(username, password)) {

				return true;
			}

			return false;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean authenticate(String username,String tipoUtente, IGestoreApplicazioni iGestoreApplicazioni) throws RemoteException {
		if(tipoUtente.equals("U"))
		{
			if(iGestoreApplicazioni.verificaAccesso(username)) {

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



