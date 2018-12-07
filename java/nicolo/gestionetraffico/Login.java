package gestionetraffico;

public class Login {
	 
    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("user") && password.equals("password")) {
            return true;
        }
        return false;
    }
}
