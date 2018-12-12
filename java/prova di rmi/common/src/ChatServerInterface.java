

import java.rmi.Remote;
import java.rmi.RemoteException;




public interface ChatServerInterface extends Remote{


    public void aggiungiCentralinaStradale(int id, String tipo) throws RemoteException;
    
 
    
    public void rimuoviCentralinaStradale(int id) throws RemoteException;
    
    public void segnalaDatabaseS(DatoTraffico dato) throws RemoteException;
}
