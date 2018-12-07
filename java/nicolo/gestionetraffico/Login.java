package gestionetraffico;

// basato su http://www.zentut.com/java-swing/simple-login-dialog/

public class Login {
	 
    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("user") && password.equals("password")) {
            return true;
        }
        return false;
    }
}
