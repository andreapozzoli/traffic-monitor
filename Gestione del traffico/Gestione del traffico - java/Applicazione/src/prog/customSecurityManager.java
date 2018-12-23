package prog;
import java.security.*;

public class customSecurityManager extends SecurityManager {

	//questa classe serve per ovviare ai problemi di security manager nelle connessioni rmi

	SecurityManager original;

	customSecurityManager(SecurityManager original) {
		this.original = original;
	}


	public void checkExit(int status) {
		//throw(new SecurityException("Not allowed"));
	}

	public void checkPermission(Permission perm) {
	}

	public SecurityManager getOriginalSecurityManager() {
		return original;
	}
}

