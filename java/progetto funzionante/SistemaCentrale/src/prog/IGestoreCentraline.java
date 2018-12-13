package prog;
import java.rmi.*;

public interface IGestoreCentraline extends Remote{
    public void aggiungiCentralinaStradale(CentralinaStradale centralina);
    public void rimuoviCentralinaStradale(int id); 
    public void segnalaDatabaseS(DatoTraffico dato);
}
