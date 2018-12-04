package gestionetraffico;
import java.util.ArrayList;
import java.util.Scanner;

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
    
    public boolean login() {
		//registrato inserisce dati e li verifica
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username=sc.nextLine();
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		if(this.riconosciAmministratore(username, password)) {
			return true;
		}
		else
			return false;
		
	}
    
    public boolean registraAmministratore() {
    	//registrare nuovo utente
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username;
		while(true) {
		username=sc.nextLine();
		if(this.riconosciAmministratore(username)) {
			System.out.println("Username già in uso");
		}	
		else {
			break;
		}
		}
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		this.aggiungiAmministratore(new Amministratore(username,password));
		return true;
    }
    
    public boolean riconosciAmministratore(String username, String password) {
		for (Amministratore amministratore: this.listaAmministratori) {
			if (amministratore.getNomeUtente().equals(username) && amministratore.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
    	
	}
    
    public boolean riconosciAmministratore(String username) {
    	for (Amministratore amministratore: this.listaAmministratori) {
			if (amministratore.getNomeUtente().equals(username)) {
				return true;
			}
		}
		return false;
    	
	}
    

}
