import java.util.ArrayList;

public class GestoreUtenti {
	private ArrayList<Utente> listaUtenti;
	private static GestoreUtenti istance=null;
    
private GestoreUtenti() {
    	this.listaUtenti=new ArrayList<Utente>();
    }

    public static GestoreUtenti getIstance() {
            if(istance==null)
                    istance = new GestoreUtenti();
            return istance;
    }
    public void aggiungiUtente(Utente utente) {
    	
    	listaUtente.add(utente)
    }
    
    public void rimuoviUtente(String username) {
    	
	for(Utente var : this.listaUtenti) {
		if (var.getUsername().equals(username)) {
			this.listaUtenti.remove(var);
			break;
		}
	}

    }

    public void getUtenti() {
    	return listaUtenti;
    }

    public bool riconosciUtente(String username, String password) {
		for(i=0, i<listaUtente.size();i++){
		if (listaUtente[i].username==username && listaUtente[i].password==password) {
			return 1;
		}
	return 0;
	}
    
    
    

}
