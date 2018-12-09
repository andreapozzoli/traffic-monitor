package gestionetraffico;
import java.io.IOException;
import java.rmi.*;
import java.util.Scanner;

import jxl.read.biff.BiffException;

public class ApplicazioneClient implements Runnable{
	
	private ApplicazioneMobile applicazione;
	
	public ApplicazioneClient(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
		
	}

	public void run(){
		/*
		//System.setSecurityManager(new RMISecurityManager());
		//IGestoreApplicazioni iGestoreApplicazioni=(IGestoreApplicazioni) Naming.lookup("GestoreApplicazioni");
		
		//while per gestire la possibilit� di riloggarsi nel momento in cui si fa il log-out
				while(true) {
				//L'utente deve scegliere se fare il login (� gia registrato) o registrarsi (� un nuovo utente)
				while(true) {
				System.out.println("Digita 'l' se sei gi� registrato, 'r' se sei un nuovo utente");
				Scanner sc= new Scanner(System.in);
				String login=sc.nextLine();
				if (login.equals("r")) {
					this.applicazione.registraUtente();
					break;
				}
				else if (login.equals("l")) {
					if(this.applicazione.login()) {
					break;
					}
					else {
						System.out.println("Username o password non corretti");
					}
				}
				}
				//segnalazione coda e possibilit� di logout
				while(true) {
				System.out.println("Digita 's' se vuoi segnalare la presenza di coda, 'o' se vuoi fare il log-out");
				Scanner sc= new Scanner(System.in);
				String segnalazione=sc.nextLine();
				if (segnalazione.equals("s")) {
				try {
					this.applicazione.segnalaCoda();
					System.out.println("posizione "+ this.applicazione.getPosizione().getVia());
				} catch (BiffException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else if (segnalazione.equals("o"))
				{
					this.applicazione.logout();
					break;
				}
				}
				}
				
		*/

	}

}
