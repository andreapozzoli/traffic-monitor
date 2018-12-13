package gestionetraffico;
import java.rmi.*;

import gestionetraffico.Utente;
import prog.IGestoreApplicazioni;

public interface IGestoreApplicazioni extends Remote{
	public void aggiungiApplicazione(ApplicazioneMobile applicazione) throws RemoteException;
	public void rimuoviApplicazione(int identificativo) throws RemoteException;
	public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException;
	public void registraUtente(Utente utente) throws RemoteException;
	public Utente passaggioUtente(String username) throws RemoteException;
	public boolean verificaAccesso(String username, String password, IGestoreApplicazioni server) throws RemoteException;
	public boolean verificaAccesso(String username, IGestoreApplicazioni server) throws RemoteException;

}
