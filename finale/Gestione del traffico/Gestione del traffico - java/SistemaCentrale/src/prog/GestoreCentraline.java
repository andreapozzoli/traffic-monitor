package prog;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;

public class GestoreCentraline extends UnicastRemoteObject implements IGestoreCentraline {
	
	private static final long serialVersionUID = -8583760378828514273L;
	private ArrayList<Cent> listaCentralineStradali;
	private ArrayList<Auto> listaCentralineAuto;
	private static GestoreCentraline instance=null;
	
    private GestoreCentraline() throws RemoteException{
    	this.listaCentralineStradali=new ArrayList<Cent>();
    	this.listaCentralineAuto=new ArrayList<Auto>();
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
    
    public void aggiungiCentralinaAuto(int id) throws RemoteException {
    	// metodo per aggiungere una nuova centralina
    	Auto centralina=new Auto(id);
    	this.listaCentralineAuto.add(centralina);
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
    
    public void rimuoviCentralinaAuto(int id) throws RemoteException{
    	// metodo per rimuovere una centralina
    	for (Auto var : this.listaCentralineAuto) {
    		if (var.getId()==id) {
    			this.listaCentralineAuto.remove(var);
    			break;
    		}
    	}
    }
    
    public synchronized void segnalaDatabaseS(DatoTraffico dato) throws NotBoundException, RemoteException{
    	// metodo per segnalare il sistema centrale e inviargli quindi una notifica nel momento in cui
    	// scade l'intervallo di tempo della centralina
    	GestoreDatabase.getInstance().aggiungiDatoTraffico(dato);
    }
    
    public synchronized void segnalaDatabaseA(StatoVeicolo stato) throws NotBoundException, RemoteException{
    	// metodo per segnalare il sistema centrale e inviargli quindi una notifica nel momento in cui
    	// scade l'intervallo di tempo della centralina
    	try {
			GestoreDatabase.getInstance().aggiungiStatoVeicolo(stato);
		} catch (BiffException | IOException e) {
			
		}
    }
  
    
    public ArrayList<Cent> getListaCentralineStradali(){
    	return this.listaCentralineStradali;
    }

    public ArrayList<Auto> getListaCentralineAuto(){
    	return this.listaCentralineAuto;
    }
	

}
