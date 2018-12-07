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
	
	public static boolean loginGrafico(MappaGrafica mappa) {
		
		// basato su http://www.zentut.com/java-swing/simple-login-dialog/

		final JFrame frame = new JFrame("Accesso al sistema centrale");
        final JButton btnLogin = new JButton("Visualizzare la mappa");
 
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
                            posizionaPuntiTest(mappa);
                        }
                    }
                });
        return !(frame.isVisible());
        
	}
	
	public static void posizionaPuntiTest(MappaGrafica Mappa) {
		
		Posizione P1 = new Posizione((float)11.3, (float)11.5);
		Posizione P2 = new Posizione((float)6.3, (float)31.5);
		Posizione P3 = new Posizione((float)80.3, (float)51.5);


		CentralinaStradale C1 = new CentralinaStradale(10, P1, 3, "extraurbana");
		CentralinaStradale C2 = new CentralinaStradale(50, P2, 3, "urbana");
		CentralinaStradale C3 = new CentralinaStradale(50, P3, 3, "urbana");


		ArrayList<CentralinaStradale> ListaTest = new ArrayList<CentralinaStradale>();
		ListaTest.add(C1);
		ListaTest.add(C2);
		
		ArrayList<MapMarkerDot> ListaPunti = new ArrayList<MapMarkerDot>();
		ListaPunti = posizionaCentraline(ListaTest, Mappa);
		ListaTest.add(C3);
		
		MapMarkerDot Dinamica = Mappa.aggiungiApplicazioneMobile("App mobile dinamica", 11, 23);
		MapMarkerDot Dinamica2 = Mappa.aggiungiApplicazioneMobile("App mobile dinamica 2", 70, 23);
		Mappa.rimuoviMarcatore(Dinamica2);
		aggiornaCentraline(ListaPunti, ListaTest, Mappa);
	}
	
	
	
	public static void main(String[] args) {
	
		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		GestoreCentraline GCent=GestoreCentraline.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
	
		MappaGrafica Mappa = visualizzazioneMappaBase();
		
		loginGrafico(Mappa);
				
			
		
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


