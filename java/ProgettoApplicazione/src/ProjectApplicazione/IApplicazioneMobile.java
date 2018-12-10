package ProjectApplicazione;
import java.io.IOException;
import java.rmi.*;

import jxl.read.biff.BiffException;

public interface IApplicazioneMobile extends Remote {
	public int getIdentificativo () throws RemoteException;
	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) throws RemoteException;
	public Posizione getPosizione() throws BiffException, IOException, RemoteException;
	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) throws RemoteException;
	public void setIdentificativo(int id) throws RemoteException;
	public String getUsernameUtente() throws RemoteException;

}
