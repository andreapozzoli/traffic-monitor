package ProjectSistemaCentrale;
public class Amministratore {
	private String nomeUtente;
	private String password;
	public Amministratore(String nomeUtente, String password) {
		this.nomeUtente=nomeUtente;
		this.password=password;
	}
	public String getNomeUtente () {
		return this.nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente=nomeUtente;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password=password;
	}

}
