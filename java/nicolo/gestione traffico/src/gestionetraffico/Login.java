package gestionetraffico;

// basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class Login {

	public static boolean authenticate(String username, String password, String tipoUtente) {
		if(tipoUtente.equals("U"))
		{
			if(GestoreApplicazioni.getInstance().verificaAccesso(username, password)) {

				return true;
			}

			return false;
		}
		else if(tipoUtente.equals("A"))
		{
			if (username.equals("user") && password.equals("password")) {

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



