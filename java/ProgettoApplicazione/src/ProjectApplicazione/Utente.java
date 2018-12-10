package ProjectApplicazione;

public class Utente {
	private String username;
	private String password;
	public Utente(String username, String password) {
		this.username=username;
		this.password=password;
	}
	public String getUsername () {
		return this.username;
	}
	public void setUsername(String username) {
		System.out.println("ok");
		this.username=username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	

}
