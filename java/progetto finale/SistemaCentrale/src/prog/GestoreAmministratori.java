package prog;
import java.util.ArrayList;

public class GestoreAmministratori {
	private static GestoreAmministratori instance=null;
	private ArrayList <Amministratore> listaAmministratori;
    private GestoreAmministratori() {
    	this.listaAmministratori=new ArrayList<Amministratore>();
    }
    
    public static GestoreAmministratori getInstance() {
    	// metodo per mantenere la classe singleton
            if(instance==null)
                    instance = new GestoreAmministratori();
            return instance;
    }
    public void aggiungiAmministratore(Amministratore admin) {
    	// metodo per aggiungere un amministratore
    	this.listaAmministratori.add(admin);
    }
    public void rimuoviAmministratore(String nome) {
    	// metodo per rimuovere i dati di un amministratore, se esso e' nella lista
    	for (Amministratore var: this.listaAmministratori) {
    		if (var.getNomeUtente().equals(nome)) {
    			this.listaAmministratori.remove(var);
    			break;
    		}
    	}
    }
    public ArrayList<Amministratore> getListaAmministratori () {
    	return this.listaAmministratori;
    }
    
    
   
    public boolean riconosciAmministratore(String username, String password) {
    	// metodo per riconoscere un amministratore nel momento in cui sta facendo il login se egli e' gia' registrato
		for (Amministratore amministratore: this.listaAmministratori) {
			if (amministratore.getNomeUtente().equals(username) && amministratore.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
    	
	}
    
    public boolean riconosciAmministratore(String username) {
    	// metodo per controllare che un amministratore che si sta registrando non utilizzi un username gia' in uso
    	for (Amministratore amministratore: this.listaAmministratori) {
			if (amministratore.getNomeUtente().equals(username)) {
				return true;
			}
		}
		return false;
    	
	}
    

}
