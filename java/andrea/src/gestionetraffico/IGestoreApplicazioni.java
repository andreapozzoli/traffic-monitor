package gestionetraffico;
import java.rmi.*;

public interface IGestoreApplicazioni extends Remote{
	public void aggiungiApplicazione(ApplicazioneMobile applicazione) throws RemoteException;
	public void rimuoviApplicazione(int identificativo) throws RemoteException;
	public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException;

}
