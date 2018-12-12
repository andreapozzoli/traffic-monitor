package prog;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.Random;

import org.openstreetmap.gui.jmapviewer.*;

import jxl.read.biff.BiffException;


public class FunzionamentoSistemaCentrale {

	private static MappaGrafica mappa;
	public static MappaGrafica visualizzazioneMappaBase() {
		MappaGrafica mappa = new MappaGrafica();
		mappa.setVisible(true);

		return mappa;
	}


	public static boolean loginGrafico() {

		// basato su http://www.zentut.com/java-swing/simple-login-dialog/

		final JFrame frame = new JFrame("Accesso al sistema centrale");
		final JButton btnLogin = new JButton("Login");
		final JButton btnRegistrazione = new JButton("Registrazione");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnRegistrazione);
		frame.setVisible(true);

		btnLogin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoginDialog loginDlg = new LoginDialog(frame, "A");
						loginDlg.setVisible(true);

						if(loginDlg.isSucceeded()){
							loginDlg.setVisible(false);
							frame.setVisible(false);
							mappa=visualizzazioneMappaBase();
						}
					}
				});
		
		

		btnRegistrazione.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						RegistrazioneDlg registrazioneDlg = new RegistrazioneDlg(frame, "A");
						registrazioneDlg.setVisible(true);

						if(registrazioneDlg.isSucceeded()){
							registrazioneDlg.setVisible(false);
							frame.setVisible(false);
							Amministratore admin=new Amministratore(registrazioneDlg.getUsername(),registrazioneDlg.getPassword());
							GestoreAmministratori.getInstance().aggiungiAmministratore(admin);
							
								mappa=visualizzazioneMappaBase();
								
							
						}
					}
				});
		return !(frame.isVisible());

	}

	
		
    public static void logout() {
    	mappa.setVisible(false);
    	loginGrafico();
    } 

	public static MappaGrafica getMappa() {
		return mappa;
	}

	public static void main(String[] args) throws BiffException, IOException {

		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		ChatServer GCent=ChatServer.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
	

		//mappa = visualizzazioneMappaBase();

		Thread t90=new Thread(new Main());
		t90.start();
		
		loginGrafico();
		






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


