package ProjSistemaCentrale;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.Demo;

public class GestoreAmministratori implements Runnable {
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
    
    public void run() {
    	while(true) {
    		while(true) {
    			System.out.println("Digita 'l' se sei già registrato, 'r' se sei un nuovo amministratore");
    			Scanner sc= new Scanner(System.in);
    			String login=sc.nextLine();
    			if (login.equals("r")) {
    				this.registraAmministratore();
    				break;
    			}
    			else if (login.equals("l")) {
    				if(this.login()) {
    				break;
    				}
    				else {
    					System.out.println("Username o password non corretti");
    				}
    			}
    			}
    		while(true) {
    		System.out.println("Premi 'm' se vuoi visualizzare la mappa,'o' se vuoi fare il logout");
    		Scanner sc= new Scanner(System.in);
    		String comando=sc.nextLine();
    		if (comando.equals("m")) {
    			//visualizzazione mappa
    			 new Demo().setVisible(true);
    		}
    		/*else if (comando.equals("d")) {
    			//visualizzazione diagramma
    		}*/
    		else if (comando.equals("o"))
    		{
    			break;
    		}
    		/*else if(comando.equals("c")) {
    			//comandi per aggiungere una nuova centralina
    		}*/
    		}
    		}
    }
    

}
