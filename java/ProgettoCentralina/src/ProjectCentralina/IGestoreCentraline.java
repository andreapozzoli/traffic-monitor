package ProjectCentralina;
import java.rmi.*;

public interface IGestoreCentraline extends Remote{
  //  public void aggiungiCentralinaAuto(CentralinaAutomobilistica centralina); 
    public void aggiungiCentralinaStradale(CentralinaStradale centralina) throws RemoteException;
   // public void rimuoviCentralinaAuto(int id) throws RemoteException;
    public void rimuoviCentralinaStradale(int id) throws RemoteException; 
    public void segnalaDatabaseS(DatoTraffico dato) throws RemoteException;
    //public void segnalaDatabaseA(StatoVeicolo dato) throws RemoteException;
}
