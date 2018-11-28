import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class GestoreCentraline extends UnicastRemoteObject implements IGestoreCentraline {
	private ArrayList<CentralinaAutomobilistica> listaCentralineAuto;
	private ArrayList<CentralinaStradale> listaCentralineStradali;
	private static GestoreCentraline instance=null;
	
    private GestoreCentraline() throws RemoteException{
    	this.listaCentralineAuto=new ArrayList<CentralinaAutomobilistica>();
    	this.listaCentralineStradali=new ArrayList<CentralinaStradale>();
    }
    
    public static GestoreCentraline getInstance(){  		
    	try {    		
            if(instance==null)
                    instance = new GestoreCentraline();
            return instance;
    	}
    	catch (RemoteException a) {
    		System.err.println(a.getMessage());
    	}
    	finally {
    		System.out.println("Errore nella connessione RMI");
    		return instance;
    	}
    }
    public void aggiungiCentralinaAuto(CentralinaAutomobilistica centralina) {
    	this.listaCentralineAuto.add(centralina);
    }
    
    public void aggiungiCentralinaStradale(CentralinaStradale centralina) {
    	this.listaCentralineStradali.add(centralina);
    }
    
    public void rimuoviCentralinaAuto(int id) {
    	for (CentralinaAutomobilistica var : this.listaCentralineAuto) {
    		if (var.getIdVeicolo()==id) {
    			this.listaCentralineAuto.remove(var);
    			break;
    		}
    	}
    }
    
    public void rimuoviCentralinaStradale(int id) {
    	for (CentralinaStradale var : this.listaCentralineStradali) {
    		if (var.getIdCentralinaStradale()==id) {
    			this.listaCentralineStradali.remove(var);
    			break;
    		}
    	}
    }
    
    public void segnalaDatabaseS(DatoTraffico dato) {
    	GestoreDatabase.getInstance().aggiungiDatoTraffico(dato);
    }
    public void segnalaDatabaseA(StatoVeicolo dato) {
    	GestoreDatabase.getInstance().aggiungiStatoVeicolo(dato);
    }
    
    public ArrayList<CentralinaAutomobilistica> getListaCentralineAuto(){
    	return this.listaCentralineAuto;
    }
    
    public ArrayList<CentralinaStradale> getListaCentralineStradali(){
    	return this.listaCentralineStradali;
    }
    

}
