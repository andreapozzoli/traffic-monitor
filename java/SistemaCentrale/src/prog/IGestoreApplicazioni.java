package prog;
import java.rmi.*;

import prog.Utente;

public interface IGestoreApplicazioni extends Remote{
	public void aggiungiApplicazione(int id, Utente utente) throws RemoteException;
	public void rimuoviApplicazione(int identificativo) throws RemoteException;
	public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException;
	public void registraUtente(Utente utente) throws RemoteException;
	public Utente passaggioUtente(String username) throws RemoteException;
	public boolean verificaAccesso(String username, String password) throws RemoteException;
	public boolean verificaAccesso(String username) throws RemoteException;
	public int getIdApp() throws RemoteException;

}
