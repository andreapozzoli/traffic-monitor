package prog;

public class CentSist {
	private String tipoStrada;
	private int idCentralinaStradale;

	public CentSist (int id, String tipo) {
		this.idCentralinaStradale=id;
		this.tipoStrada=tipo;
	}
	
	public void setId (int id) {
		this.idCentralinaStradale=id;
	}
	
	public void setTipo (String tipo) {
		this.tipoStrada=tipo;
	}
	
	public int getId() {
		return this.idCentralinaStradale;
	}
	
	public String getTipo() {
		return this.tipoStrada;
	}
}
