
public class GestoreAmministratori {
	private static GestoreAmministratori istance=null;
	private Amministratore[] listaAmministratori;
    private GestoreAmministratori() {
    	this.listaAmministratori=new Amministratore[50];
    }
    public static GestoreAmministratori getIstance() {
            if(istance==null)
                    istance = new GestoreAmministratori();
            return istance;
    }
    public void aggiungiAmministratore(Amministratore admin) {
    	int i=0;
    	for (i=0; i<50; i++) {
    		if (this.listaAmministratori[i]==null) {
    			this.listaAmministratori[i]=admin;
    			break;
    		}
    	}
    	if (i==50) {
    		System.out.println("immpossibile aggiungere amministratore");
    	}
    }
    public void rimuoviAmministratore(String nome) {
    	int i=0;
    	for (i=0; i<50; i++) {
    		if (this.listaAmministratori[i].getNomeUtente()==nome) {
    			this.listaAmministratori[i]=null;
    			break;
    		}
    	}
    	if (i==50) {
    		System.out.println("non esiste amministratore con questo nome");
    	}
    }
    public Amministratore[] getListaAmministratori () {
    	return this.listaAmministratori;
    }
    
    
    

}
