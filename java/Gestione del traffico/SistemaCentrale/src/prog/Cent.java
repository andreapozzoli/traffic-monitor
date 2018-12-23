package prog;

public class Cent { // classe necessaria a salvare i dati delle centraline nel sistema centrale
	
	private int idCentralinaStradale;
	
	
	public Cent(int id) {
		this.idCentralinaStradale=id;
	}
	
	public int getId() {
		return this.idCentralinaStradale;
	}
	
}
