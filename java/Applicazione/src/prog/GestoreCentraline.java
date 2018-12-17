package prog;
import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.*;

public class GestoreCentraline extends UnicastRemoteObject implements IGestoreCentraline {
	
	private static final long serialVersionUID = -8583760378828514273L;
	private ArrayList<Cent> listaCentralineStradali;
	private static GestoreCentraline instance=null;
	
    private GestoreCentraline() throws RemoteException{
    	this.listaCentralineStradali=new ArrayList<Cent>();
    }
    
    public static GestoreCentraline getInstance() throws RemoteException{  		
    		// metodo per mantenere la classe singleton
            if(instance==null)
                    instance = new GestoreCentraline();
            return instance;
    }
    
    
    public void aggiungiCentralinaStradale(int id) throws RemoteException {
    	// metodo per aggiungere una nuova centralina
    	Cent centralina=new Cent(id);
    	this.listaCentralineStradali.add(centralina);
    }
    
   
    
    public void rimuoviCentralinaStradale(int id) throws RemoteException{
    	// metodo per rimuovere una centralina
    	for (Cent var : this.listaCentralineStradali) {
    		if (var.getId()==id) {
    			this.listaCentralineStradali.remove(var);
    			break;
    		}
    	}
    }
    
    public synchronized void segnalaDatabaseS(DatoTraffico dato) throws NotBoundException, RemoteException{
    	// metodo per segnalare il sistema centrale e inviargli quindi una notifica nel momento in cui
    	// scade l'intervallo di tempo della centralina
    	GestoreDatabase.getInstance().aggiungiDatoTraffico(dato);
    }
    
    
  
    
    public ArrayList<Cent> getListaCentralineStradali(){
    	return this.listaCentralineStradali;
    }

	

}
