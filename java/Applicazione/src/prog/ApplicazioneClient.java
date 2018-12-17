package prog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jxl.read.biff.BiffException;
import java.rmi.registry.*;


public class ApplicazioneClient implements Runnable {

	private ApplicazioneMobile applicazione;
	private IGestoreApplicazioni server;


	public ApplicazioneClient(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
	}

	private void segnalaCoda() throws BiffException, IOException  {

		if (!applicazione.getFissa()) { // in questo modo se l'utente vuole tenere fissa l'applicazione gli basta spuntare la casella dedicata 
			this.applicazione.setPosizione(this.applicazione.getSensore().rilevaPosizione()); // e non viene perci� aggiornata la posizione dell'applicazione
		}
		NotificaApplicazione notifica=new NotificaApplicazione(this.applicazione.getUsernameUtente(), this.applicazione.getPosizione(), "M10 Coda"); // viene creata la notifica
		server.segnalaDatabase(notifica); // viene inviata la notifica al sistema centrale

	}

	public boolean loginGrafico(int id) throws RemoteException {


		final JFrame frame = new JFrame("Accesso all'applicazione mobile");
		final JButton btnLogin = new JButton("Login");
		final JButton btnRegistrazione = new JButton("Registrazione");


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(360, 100);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnRegistrazione);
		frame.setVisible(true);


		btnLogin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoginDialog loginDlg = new LoginDialog(frame, "U", server);
						loginDlg.setVisible(true);

						if(loginDlg.loginRiuscito()){
							loginDlg.setVisible(false);
							frame.setVisible(false);

							try {
								applicazione.setUtente(server.passaggioUtente(loginDlg.getUsername()));
								try {
									server.aggiungiApplicazione(applicazione.getIdentificativo(), applicazione.getUtente());
								} catch (RemoteException e3) {
									e3.printStackTrace();
								}
							} catch (RemoteException e2) {
								e2.printStackTrace();
							}								

							try {
								mostraGUI(id);
							} catch (BiffException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						}
					}
				});



		btnRegistrazione.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						RegistrazioneDlg registrazioneDlg = new RegistrazioneDlg(frame, "U", server);
						registrazioneDlg.setVisible(true);


						System.out.println(registrazioneDlg.getUsername());
						if(registrazioneDlg.registrazioneRiuscita()){
							registrazioneDlg.setVisible(false);
							frame.setVisible(false);

							applicazione.setUtente ( new Utente(registrazioneDlg.getUsername(), registrazioneDlg.getPassword()));
							try {
								server.aggiungiApplicazione(applicazione.getIdentificativo(), applicazione.getUtente());
							} catch (RemoteException e3) {
								e3.printStackTrace();
							}
							try {
								server.registraUtente(applicazione.getUtente());
							} catch (RemoteException e2) {
								e2.printStackTrace();
							}

							try {
								mostraGUI(id);
							} catch (BiffException | IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				});


		return !(frame.isVisible());

	}

	public void logout() throws RemoteException { // se l'utente decide di fare il logout dall'applicazione, viene settato l'utente a null
		this.applicazione.setUtente(null);        // e viene rimossa l'applicazione dall'elenco delle applicazioni segnalabili dal sistema centrale
		server.rimuoviApplicazione(this.applicazione.getIdentificativo());
		this.applicazione.getFrame().setVisible(false);  // viene chiusa la finestra di segnalazione
		this.loginGrafico(this.applicazione.getIdentificativo()); // viene fatto ripartire il login
	}

	public void mostraGUI(int id) throws BiffException, IOException {
		JButton segnalaCodaBtn = new JButton("Segnala coda");
		JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");
		JButton logoutBtn = new JButton("Logout");
		JCheckBox fissaPosizione = new JCheckBox("Selezionare per fissare la posizione");

		this.applicazione.setFrame(new JFrame());
		this.applicazione.getFrame().setTitle("Applicazione mobile"); // titolo della finestra

		this.applicazione.getPaneNotifiche().setPreferredSize(new Dimension(175,150));

		// Pannello contenitore
		JPanel principale = new JPanel(new BorderLayout());
		
		// Pannello in alto
		JPanel alto = new JPanel();
		principale.add(alto,BorderLayout.NORTH);
		alto.add(fissaPosizione); // spunta per fissare la posizione dell'applicazione

		// Pannello in basso
		JPanel basso = new JPanel();
		principale.add(basso,BorderLayout.SOUTH);
		basso.add(segnalaCodaBtn); // bottone per la segnalazione della coda
		basso.add(logoutBtn); // bottone per il logout
		basso.add(svuotaNotificheBtn); // bottone per svuotare le notifiche

		// Pannello a sinistra
		JPanel sinistra = new JPanel();
		principale.add(sinistra,BorderLayout.WEST);

		// Pannello a destra
		JPanel destra = new JPanel();
		principale.add(destra,BorderLayout.EAST);
		
		// Area centrale
		principale.add(this.applicazione.getPaneNotifiche(),BorderLayout.CENTER);
		this.applicazione.getFrame().getContentPane().add(principale);

		// Azioni dei bottoni
		segnalaCodaBtn.addActionListener(e -> {
			try {
				segnalaCoda();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		logoutBtn.addActionListener(e -> {
			try {
				logout();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());

		// Azioni della spunta
		fissaPosizione.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						applicazione.setFissa(fissaPosizione.isSelected());
					}
				});


		// Alla chiusura della finestra deve fare il logout
		this.applicazione.getFrame().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					logout();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.applicazione.getFrame().pack();
		this.applicazione.getFrame().setVisible(true);

	}

	private void pulisciNotifiche() {
		this.applicazione.getAreaNotifiche().setText("");
	}

	public void run(){

		try {

			customSecurityManager cSM = new customSecurityManager(System.getSecurityManager());
			System.setSecurityManager(cSM);

			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);

			this.server = (IGestoreApplicazioni) registry.lookup("gestApp");

			this.applicazione.setIdentificativo(this.server.getIdApp()); // viene calcolato l'identificativo dell'applicazione appena creata

			Thread t2=new Thread(new ApplicazioneServer(this.applicazione)); // viene fatto partire il lato server dell'applicazione
			t2.start();

			loginGrafico(this.applicazione.getIdentificativo()); // si apre la finestra di login

		} catch (Exception e) { // se non e' ancora stato attivato il sistema centrale non e' possibile inviare segnalazioni
			  JOptionPane.showMessageDialog(null,
				        "Il sistema centrale non e' disponibile.\nI dati possono essere trasmessi solo in presenza\ndi una connessione con il sistema centrale."
					  +"\nL'applicazione verra' chiusa.",
				        "Errore di connessione",
				        JOptionPane.ERROR_MESSAGE);
			  System.exit(1);
		}

	}

}
