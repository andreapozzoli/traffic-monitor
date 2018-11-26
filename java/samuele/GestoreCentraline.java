import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class GestoreCentraline extends UnicastRemoteObject implements IGestoreCentraline {
	private ArrayList<CentralinaAutomobilistica> listaCentralineAuto;
	private ArrayList<CentralinaStradale> listaCentralineStradali;
	private static GestoreCentraline istance=null;
	
    private GestoreCentraline() {
    	this.listaCentralineAuto=new ArrayList<CentralinaAutomobilistica>();
    	this.listaCentralineStradali=new ArrayList<CentralinaStradale>();
    }
    
    public static GestoreCentraline getIstance() {
            if(istance==null)
                    istance = new GestoreCentraline();
            return istance;
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
    
    
    

}
