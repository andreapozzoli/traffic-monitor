package gestionetraffico;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

public class FunzionamentoApplicazione {

	public static void main(String[] args) throws IOException  {

		ApplicazioneMobile app1=new ApplicazioneMobile(123);
		//while per gestire la possibilità di riloggarsi nel momento in cui si fa il log-out
		while(true) {
		//L'utente deve scegliere se fare il login (è gia registrato) o registrarsi (è un nuovo utente)
		while(true) {
		System.out.println("Digita 'l' se sei già registrato, 'r' se sei un nuovo utente");
		Scanner sc= new Scanner(System.in);
		String login=sc.nextLine();
		if (login.equals("r")) {
			app1.registraUtente();
			break;
		}
		else if (login.equals("l")) {
			if(app1.login()) {
			break;
			}
			else {
				System.out.println("Username o password non corretti");
			}
		}
		}
		//segnalazione coda e possibilità di logout
		while(true) {
		System.out.println("Digita 's' se vuoi segnalare la presenza di coda, 'o' se vuoi fare il log-out");
		Scanner sc= new Scanner(System.in);
		String segnalazione=sc.nextLine();
		if (segnalazione.equals("s")) {
		app1.segnalaCoda();
		}
		else if (segnalazione.equals("o"))
		{
			app1.logout();
			break;
		}
		}
		}
		
		/*SERVER APPLICAZIONE
		 * System.setSecurityManager(new RMISecurityManager());
		   ApplicazioneMobile applicazioneMobile= new ApplicazioneMobile();
		   Naming.rebind("ApplicazioneMobile", applicazioneMobile);*/
		
		/*CLIENT APPLICAZIONE
		 * System.setSecurityManager(new RMISecurityManager());
		   IGestoreApplicazioni iGestoreApplicazioni=(IGestoreApplicazioni) Naming.lookup("GestoreApplicazioni");*/
	
	}

}
