package ProjSistemaCentrale;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.lang.Math;


public class GestoreApplicazioni extends UnicastRemoteObject implements IGestoreApplicazioni {
	private static GestoreApplicazioni instance=null;
	private int raggio;
	private ArrayList<ApplicazioneMobile> listaApplicazioni;
    private GestoreApplicazioni() throws RemoteException {
    	super();
    	this.listaApplicazioni=new ArrayList<ApplicazioneMobile>();
    	this.raggio=500;
    }
    public static GestoreApplicazioni getInstance(){
    	    if(instance==null)
                    instance = new GestoreApplicazioni();
            return instance;
    	
    }
    public int getRaggio() {
    	return this.raggio;
    }
    public void setRaggio(int raggio) {
    	this.raggio=raggio;
    }
    public void aggiungiApplicazione(ApplicazioneMobile app) throws RemoteException{
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
   
    public void segnalaDatabase(NotificaApplicazione notifica) throws RemoteException{
    	GestoreDatabase.getInstance().aggiungiNotificaApplicazione(notifica);
    }
    public void calcolaApplicazioniDaNotificare(String mittente,Posizione posizione, String tipo) throws BiffException, IOException {
    	for (ApplicazioneMobile var: this.listaApplicazioni) {
    		if (!(var.getUsenameUtente().equals(mittente))) {
    		if (Math.sqrt(Math.pow(var.getPosizione().getLatitudine()-posizione.getLatitudine(),2)+Math.pow(var.getPosizione().getLongitudine()-posizione.getLongitudine(),2))<this.raggio){
    			NotificaApplicazione notifica;
    			notifica=var.creaNotificaApplicazione(posizione, tipo);
    			var.aggiungiNotificaInCoda(notifica);    
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


