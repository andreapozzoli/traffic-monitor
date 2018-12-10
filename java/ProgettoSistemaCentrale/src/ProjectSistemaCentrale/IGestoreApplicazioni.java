package gestionetraffico;
import java.rmi.*;

public interface IGestoreApplicazioni extends Remote{
	public void aggiungiApplicazione(ApplicazioneMobile applicazione) throws RemoteException;
	public void rimuoviApplicazione(int identificativo) throws RemoteException;
	public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException;
	public boolean verificaAccesso(String username, String password) throws RemoteException;
	public boolean verificaAccesso(String username) throws RemoteException;
	public Utente passaggioUtente(String username) throws RemoteException;
	public void registraUtente(Utente utente) throws RemoteException;

}
