package prog;

// basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class Login {

	public static boolean authenticate(String username, String password, String tipoUtente) {
		if(tipoUtente.equals("A"))
		{

				if(GestoreAmministratori.getInstance().riconosciAmministratore(username, password)) {

					return true;
				}

			return false;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean authenticate(String username,String tipoUtente) {
		if(tipoUtente.equals("A"))
		{

				if(GestoreAmministratori.getInstance().riconosciAmministratore(username)) {

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



