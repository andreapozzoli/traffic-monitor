package prog;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
	private ArrayList<CentSist> listaCentralineStradali;
	private static ChatServer instance=null;
	

	private static final long serialVersionUID = 7787401541047824619L;
	

	protected ChatServer() throws RemoteException{
    	//this.listaCentralineAuto=new ArrayList<CentralinaAutomobilistica>();
    	super();
    	this.listaCentralineStradali=new ArrayList<CentSist>();
    }
    
    public static ChatServer getInstance() throws RemoteException{  		
    	 		
            if(instance==null)
                    instance = new ChatServer();
            return instance;
            }
    
   
    public int sommma(int a, int b) throws RemoteException{
    	System.out.println(a+b);
    	return(a+b);
    }
    
    public void aggiungiCentralinaStradale(int id, String tipo) throws RemoteException{
    	CentSist centralina=new CentSist(id, tipo);
    	this.listaCentralineStradali.add(centralina);
    }
   
    public void rimuoviCentralinaStradale(int id) throws RemoteException{
    	for (CentSist var : this.listaCentralineStradali) {
    		if (var.getId()==id) {
    			this.listaCentralineStradali.remove(var);
    			break;
    		}
    	}
    }
    
    public synchronized void segnalaDatabaseS(DatoTraffico dato) throws RemoteException {
    	GestoreDatabase.getInstance().aggiungiDatoTraffico(dato);
    }
 
    
    public ArrayList<CentSist> getListaCentralineStradali(){
    	return this.listaCentralineStradali;
    }

	
	


}
