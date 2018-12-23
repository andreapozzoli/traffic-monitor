package prog;
import java.rmi.*;

public interface IApplicazioneMobile extends Remote {
	public int getIdentificativo() throws RemoteException;
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) throws RemoteException;
	public Posizione getPosizione() throws RemoteException;
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) throws RemoteException;
	public void setIdentificativo(int id) throws RemoteException;
	public String getUsernameUtente() throws RemoteException;

}
