import java.rmi.*;

public interface IGestoreCentraline extends Remote{
    public void aggiungiCentralinaAuto(CentralinaAutomobilistica centralina) 
    public void aggiungiCentralinaStradale(CentralinaStradale centralina) 
    public void rimuoviCentralinaAuto(int id)
    public void rimuoviCentralinaStradale(int id) 
    public void segnalaDatabaseS(DatoTraffico dato)
    public void segnalaDatabaseA(StatoVeicolo dato)
    
}
