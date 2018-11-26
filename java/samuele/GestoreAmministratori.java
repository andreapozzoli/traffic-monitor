import java.util.ArrayList;

public class GestoreAmministratori {
	private static GestoreAmministratori instance=null;
	private ArrayList <Amministratore> listaAmministratori;
    private GestoreAmministratori() {
    	this.listaAmministratori=new ArrayList<Amministratore>();
    }
    public static GestoreAmministratori getInstance() {
            if(instance==null)
                    instance = new GestoreAmministratori();
            return instance;
    }
    public void aggiungiAmministratore(Amministratore admin) {
    	this.listaAmministratori.add(admin);
    }
    public void rimuoviAmministratore(String nome) {
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
    
    
    

}
