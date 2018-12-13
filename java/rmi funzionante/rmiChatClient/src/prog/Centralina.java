package prog;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public abstract class Centralina extends UnicastRemoteObject implements Runnable {
	protected Centralina() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String tipo;
	protected Posizione posizione;
	protected int velocita;
	protected RilevatoreVelocita rilevatoreVelocita;
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public Posizione getPosizione() {
		return this.posizione;
	}
	public void setPosizione(Posizione posizione) {
		this.posizione=posizione;
	}
	public int getVelocita() {
		return this.velocita;
	}
	public void setVelocita(int velocita) {
		this.velocita=velocita;
	}

}
