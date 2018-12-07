package gestionetraffico;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.*;


public class FunzionamentoSistemaCentrale {

	public static MappaGrafica visualizzazioneMappaBase() {
		MappaGrafica mappa = new MappaGrafica();
		mappa.setVisible(true);
		
		return mappa;
	}
	
	public static ArrayList<MapMarkerDot> posizionaCentraline(ArrayList<CentralinaStradale> listaCS, MappaGrafica mappa) {
		ArrayList<MapMarkerDot> lista = new ArrayList<MapMarkerDot>();
		MapMarkerDot punto = new MapMarkerDot(0,0);
		for (Centralina c : listaCS) {
			punto = mappa.aggiungiCentralinaVuota("Centralina", c.getPosizione().getLatitudine(), c.getPosizione().getLongitudine());
			lista.add(punto);
		}
		return lista;
			
	}
	
	public static void aggiornaCentraline(ArrayList<MapMarkerDot> listaPuntiCS, ArrayList<CentralinaStradale> nuoveCS, MappaGrafica mappa) {
		for (MapMarkerDot c : listaPuntiCS) {
			mappa.rimuoviMarcatore(c);
		}
		posizionaCentraline(nuoveCS, mappa);
	}
	
	public static void loginGrafico() {
		final JFrame frame = new JFrame("Accesso al sistema centrale");
        final JButton btnLogin = new JButton("Accedere");
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.setVisible(true);
        
        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);

                        if(loginDlg.isSucceeded()){
                            loginDlg.setVisible(false);
                            frame.setVisible(false);
                        }
                    }
                });
 
        
	}
	
	
	
	public static void main(String[] args) {
	
		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		GestoreCentraline GCent=GestoreCentraline.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
	
		loginGrafico();
		
		while(true) {
			
			MappaGrafica mappa = visualizzazioneMappaBase();
			
				
			Posizione p1 = new Posizione((float)11.3, (float)11.5);
			Posizione p2 = new Posizione((float)6.3, (float)31.5);
			Posizione p3 = new Posizione((float)80.3, (float)51.5);
	
	
			CentralinaStradale c1 = new CentralinaStradale(10, p1, 3, "extraurbana");
			CentralinaStradale c2 = new CentralinaStradale(50, p2, 3, "urbana");
			CentralinaStradale c3 = new CentralinaStradale(50, p3, 3, "urbana");
	
	
			ArrayList<CentralinaStradale> listaTest = new ArrayList<CentralinaStradale>();
			listaTest.add(c1);
			listaTest.add(c2);
			
			ArrayList<MapMarkerDot> listaPunti = new ArrayList<MapMarkerDot>();
			listaPunti = posizionaCentraline(listaTest, mappa);
			listaTest.add(c3);
			
			while(true) {
				System.out.println("Premi 'm' se vuoi visualizzare la mappa, 'd' se vuoi visualizzare il diagramma,'o' se vuoi fare il logout");
				Scanner sc= new Scanner(System.in);
				String comando=sc.nextLine();
				if (comando.equals("m")) {
					MapMarkerDot dinamica = mappa.aggiungiApplicazioneMobile("App mobile dinamica", 11, 23);
					MapMarkerDot dinamica2 = mappa.aggiungiApplicazioneMobile("App mobile dinamica 2", 70, 23);
					mappa.rimuoviMarcatore(dinamica2);
					aggiornaCentraline(listaPunti, listaTest, mappa);
				}
				else if (comando.equals("d")) {
					//visualizzazione diagramma
				}
				else if (comando.equals("o"));{
					break;
				}
			}
		}
		//fine gestore amministratori
		/*SERVER GESTORE APPLICAZIONI
		 * System.setSecurityManager(new RMISecurityManager());
		   GestoreApplicazioni gestoreApplicazioni= GestoreApplicazioni.getInstance();
		   Naming.rebind("GestoreApplicazioni", gestoreApplicazioni);*/
		
		/*CLIENT GESTORE APPLICAZIONI
		 *System.setSecurityManager(new RMISecurityManager());
		  IApplicazioneMobile iApplicazioneMobile = (IApplicazioneMobile) Naming.lookup("ApplicazioneMobile");*/
		
		/*SERVER GESTORE CENTRALINE
		 * System.setSecurityManager(new RMISecurityManager()); 
		   GestoreCentraline gestoreCentraline = GestoreCentraline.getInstance();
		   Naming.rebind("GestoreCentraline", gestoreCentraline);*/
			
}
		

}


