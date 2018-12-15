package prog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import org.openstreetmap.gui.jmapviewer.*;

import jxl.read.biff.BiffException;


public class FunzionamentoSistemaCentrale {

	private static MappaGrafica mappa;
	
	public static MappaGrafica visualizzazioneMappaBase() {
		// Visualizza la mappa, inizialmente senza marcatori
		MappaGrafica mappa = new MappaGrafica(); // creazione oggetto mappa (vedere costruttore della classe MappaGrafica)
		mappa.setVisible(true); // visualizza la mappa

		return mappa;
	}


	public static boolean loginGrafico() {

		// basato su http://www.zentut.com/java-swing/simple-login-dialog/

		// Dichiarazione degli elementi che costituiscono l'interfaccia grafica per la scelta tra login e registrazione
		final JFrame frame = new JFrame("Accesso al sistema centrale");
		final JButton btnLogin = new JButton("Login");
		final JButton btnRegistrazione = new JButton("Registrazione");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100); // dimensione della finestra di login (larghezza, altezza)
		frame.setLayout(new FlowLayout()); // impostazione della tipologia di layout
		
		// Aggiunta dei bottoni al frame
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnRegistrazione);
		
		// Visualizzazione del frame
		frame.setVisible(true);

		btnLogin.addActionListener( // azioni da eseguire quando viene premuto il pulsante di login
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoginDialog loginDlg = new LoginDialog(frame, "A"); // "A" si riferisce al fatto che si stia considerando un amministratore
						loginDlg.setVisible(true); // mostra la schermata di login con la richiesta di nome utente e password

						if(loginDlg.isSucceeded()){ // se il nome utente e la password inseriti sono corretti
							loginDlg.setVisible(false); // nascondere schermata di login
							frame.setVisible(false); // nascondere schermata di scelta tra login e registrazione

							mappa=visualizzazioneMappaBase(); // visualizzazione della mappa senza marcatori

						}
					}
				});



		btnRegistrazione.addActionListener( // azioni da eseguire quando viene premuto il pulsante di registrazione
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						RegistrazioneDlg registrazioneDlg = new RegistrazioneDlg(frame, "A");
						registrazioneDlg.setVisible(true); // schermata di registrazione

						if(registrazioneDlg.isSucceeded()){
							registrazioneDlg.setVisible(false);
							frame.setVisible(false);
							Amministratore admin=new Amministratore(registrazioneDlg.getUsername(),registrazioneDlg.getPassword()); // creazione di un nuovo amminsitratore con le credenziali inserite (se accettate)
							GestoreAmministratori.getInstance().aggiungiAmministratore(admin); // aggiunta di un amministratore all'elenco degli amministratori

							mappa=visualizzazioneMappaBase();

						}
					}
				});
		return !(frame.isVisible()); // ritorna true soltanto se la finestra risulta chiusa

	}

	public static void logout() {
		mappa.setVisible(false); // nasconde la mappa
		loginGrafico(); // effettua nuovamente il login o registrazione
	} 

	public static MappaGrafica getMappa() {
		return mappa;
	}

	public static void main(String[] args) throws BiffException, IOException {

		GestoreApplicazioni.getInstance();
		GestoreCentraline.getInstance();
		GestoreUtenti.getInstance();
		GestoreAmministratori.getInstance();


		Thread t9= new Thread(new GestoreApplicazioniServer());
		t9.start();

		Thread t12= new Thread(new GestoreCentralineServer());
		t12.start();

		loginGrafico();

	}

}


