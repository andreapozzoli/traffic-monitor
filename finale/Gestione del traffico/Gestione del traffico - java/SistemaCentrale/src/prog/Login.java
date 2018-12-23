package prog;

public class Login {

	public static boolean autenticazione(String username, String password, String tipoUtente) {
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

	public static boolean autenticazione(String username,String tipoUtente) {
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



