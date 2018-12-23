package prog;

public class App { // classe necessaria a salvare i dati dell'applicazione nel sistema centrale
	private int identificativo;
	private Utente utente;

	public App(int id, Utente utente) {
		this.identificativo=id;
		this.utente=utente;
	}
	
	public void setId(int id){
		this.identificativo=id;
	}
	public void setUtente(Utente utente) {
		this.utente=utente;
	}
	public int getId() {
		return this.identificativo;
	}
	public Utente getUtente() {
		return this.utente;
	}
	
}
