package prog;
import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class GestoreCentraline /*extends UnicastRemoteObject*/ /*IGestoreCentraline,*/ {
	private ArrayList<CentralinaStradale> listaCentralineStradali;
	private static GestoreCentraline instance=null;
	
    private GestoreCentraline() throws RemoteException{
    	this.listaCentralineStradali=new ArrayList<CentralinaStradale>();
    }
    
    public static GestoreCentraline getInstance() throws RemoteException{  		
    		
            if(instance==null)
                    instance = new GestoreCentraline();
            return instance;
    }
    
    
    public void aggiungiCentralinaStradale(CentralinaStradale centralina) {
    	//si puo verificare che non esista gia in quella posizione
    	this.listaCentralineStradali.add(centralina);
    }
    
   
    
    public void rimuoviCentralinaStradale(int id) {
    	for (CentralinaStradale var : this.listaCentralineStradali) {
    		if (var.getIdCentralinaStradale()==id) {
    			this.listaCentralineStradali.remove(var);
    			break;
    		}
    	}
    }
    
    public synchronized void segnalaDatabaseS(DatoTraffico dato) throws NotBoundException {
    	GestoreDatabase.getInstance().aggiungiDatoTraffico(dato);
    }
    
    
  
    
    public ArrayList<CentralinaStradale> getListaCentralineStradali(){
    	return this.listaCentralineStradali;
    }
    

}
