package gestionetraffico;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.lang.Math;


public class GestoreApplicazioni /*extends UnicastRemoteObject implements IGestoreApplicazioni*/ {
	private static GestoreApplicazioni instance=null;
	private int raggio;
	private ArrayList<ApplicazioneMobile> listaApplicazioni;
	private GestoreApplicazioni() /*throws RemoteException*/ {
		super();
		this.listaApplicazioni=new ArrayList<ApplicazioneMobile>();
		this.raggio=5000;
	}
	public static GestoreApplicazioni getInstance(){
		//try {    		
		if(instance==null)
			instance = new GestoreApplicazioni();
		return instance;
		//}
		/*catch (RemoteException a) {
    		System.err.println(a.getMessage());
    	}
    	finally {*/
		//System.out.println("Errore nella connessione RMI");
		//return instance;
		//}
	}
	public int getRaggio() {
		return this.raggio;
	}
	public void setRaggio(int raggio) {
		this.raggio=raggio;
	}
	public void aggiungiApplicazione(ApplicazioneMobile app) /*throws RemoteException*/{
		this.listaApplicazioni.add(app);
	}
	public void rimuoviApplicazione(int id) throws RemoteException{
		for (ApplicazioneMobile var: this.listaApplicazioni) {
			if (var.getIdentificativo()==id) {
				this.listaApplicazioni.remove(var);
				break;
			}
		}
	}

	public ArrayList<ApplicazioneMobile> getListaApplicazioni() {
		return this.listaApplicazioni;
	}

	public void segnalaDatabase(NotificaApplicazione notifica)/* throws RemoteException*/{
		GestoreDatabase.getInstance().aggiungiNotificaApplicazione(notifica);
	}
	public void calcolaApplicazioniDaNotificare(String mittente,Posizione posizione, String tipo) throws BiffException, IOException {
		if(!(mittente.equals("centralina"))) {
			System.out.println("1");
			if (!(this.listaApplicazioni.isEmpty())) {
				System.out.println("2");
				for (ApplicazioneMobile var: this.listaApplicazioni) {
					if ((var.getUsernameUtente()!=null)&&(!(var.getUsernameUtente().equals(mittente)))) {
						//conversione distanza coordinate in metri
						double R = 6378.137; // Radius of earth in KM 
						double lat1=posizione.getLatitudine();
						double lat2=var.getPosizione().getLatitudine();
						double lon1=posizione.getLongitudine();
						double lon2=var.getPosizione().getLongitudine();
						double dLat = lat1 * Math.PI/180 - lat2 * Math.PI/180; 
						double dLon = lon1 * Math.PI/180 - lon2 * Math.PI/180; 
						double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1 * Math.PI/180) * Math.cos(lat2 * Math.PI/180) * Math.sin(dLon/2) * Math.sin(dLon/2); 
						double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
						double d = R * c; 
						//if (Math.sqrt(Math.pow(var.getPosizione().getLatitudine()-posizione.getLatitudine(),2)+Math.pow(var.getPosizione().getLongitudine()-posizione.getLongitudine(),2))<this.raggio){
						if (d*1000<this.raggio) {
							NotificaApplicazione notifica;
							notifica=var.creaNotificaApplicazione(posizione, tipo);
							var.aggiungiNotificaInCoda(notifica);    
						}
					}
				}
			}
		}

	}

	public boolean verificaAccesso(String username, String password) {
		return GestoreUtenti.getInstance().riconosciUtente(username, password);
	}

	public boolean verificaAccesso(String username) {
		return GestoreUtenti.getInstance().riconosciUtente(username);
	}

	public Utente passaggioUtente(String username) {
		return GestoreUtenti.getInstance().getUtente(username);
	}

	public void registraUtente(Utente utente) {
		GestoreUtenti.getInstance().aggiungiUtente(utente);
	}

}


