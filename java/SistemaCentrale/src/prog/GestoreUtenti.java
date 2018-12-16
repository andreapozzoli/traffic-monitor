package prog;
import java.util.ArrayList;

public class GestoreUtenti {
	private ArrayList<Utente> listaUtenti;
	private static GestoreUtenti instance=null;
    
    private GestoreUtenti() {
    	this.listaUtenti=new ArrayList<Utente>();
    }

    public static GestoreUtenti getInstance() {
            if(instance==null)
                    instance = new GestoreUtenti();
            return instance;
    }
    public void aggiungiUtente(Utente utente) {
    	
    	this.listaUtenti.add(utente);
    }
    
    public ArrayList<Utente> getListaUtenti(){
    	return this.listaUtenti;
    }
    
    public void rimuoviUtente(String username) {
    	
	for(Utente var : this.listaUtenti) {
		if (var.getUsername().equals(username)) {
			this.listaUtenti.remove(var);
			break;
		}
	}

    }

    public Utente getUtente(String username) {
    	Utente utente=null;
    	for(Utente var : this.listaUtenti) {
    		if (var.getUsername().equals(username)) {
    		utente=var;
    		break;
    		}
    	}
    	return utente;
    	
    }

    public boolean riconosciUtente(String username, String password) {
		for (Utente utente: this.listaUtenti) {
			if (utente.getUsername().equals(username) && utente.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
    	
	}
    
    public boolean riconosciUtente(String username) {
		for (Utente utente: this.listaUtenti) {
			if (utente.getUsername().equals(username) ) {
				return true;
			}
		}
		return false;
    	
	}
    
}
