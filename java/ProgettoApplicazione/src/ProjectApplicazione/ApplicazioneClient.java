package ProjectApplicazione;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jxl.read.biff.BiffException;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class ApplicazioneClient implements Runnable{

	private ApplicazioneMobile applicazione;
	private IGestoreApplicazioni iGestoreApplicazioni;
	private JFrame frame;
	private final long serialVersionUID = 1L;

	private JTextArea areaNotifiche = new JTextArea(10, 10);
	private JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);

	public ApplicazioneClient(ApplicazioneMobile applicazione) {
		this.applicazione=applicazione;
	}
	
	private void segnalaCoda() throws BiffException, IOException  {

		this.applicazione.setPosizione(this.applicazione.getSensore().rilevaPosizione());
		NotificaApplicazione notifica=new NotificaApplicazione(this.applicazione.getUsernameUtente(), this.applicazione.getPosizione(), "M10 Coda");
		iGestoreApplicazioni.segnalaDatabase(notifica);

	}
	
	public boolean loginGrafico(int id) {

		// basato su http://www.zentut.com/java-swing/simple-login-dialog/

		final JFrame frame = new JFrame("Accesso all'applicazione mobile");
		final JButton btnLogin = new JButton("Login");
		final JButton btnRegistrazione = new JButton("Registrazione");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnRegistrazione);
		frame.setVisible(true);

		/* FunzionamentoSistemaCentrale test = new FunzionamentoSistemaCentrale();
		try {
			test.main();
		} catch (BiffException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} */

		btnLogin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoginDialog loginDlg = new LoginDialog(frame, "U", iGestoreApplicazioni);
						loginDlg.setVisible(true);

						if(loginDlg.isSucceeded()){
							loginDlg.setVisible(false);
							frame.setVisible(false);

							try {
								applicazione.setUtente(iGestoreApplicazioni.passaggioUtente(loginDlg.getUsername()));
							} catch (RemoteException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
								return;
							}								

							try {
								mostraGUI(id);
							} catch (BiffException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});



		btnRegistrazione.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						RegistrazioneDlg registrazioneDlg = new RegistrazioneDlg(frame, "U", iGestoreApplicazioni);
						registrazioneDlg.setVisible(true);

						//utente=GestoreApplicazioni.getInstance().passaggioUtente(registrazioneDlg.getUsername());

							System.out.println(registrazioneDlg.getUsername());
							if(registrazioneDlg.isSucceeded()){
								registrazioneDlg.setVisible(false);
								frame.setVisible(false);

								applicazione.setUtente ( new Utente(registrazioneDlg.getUsername(), registrazioneDlg.getPassword()));
								try {
									iGestoreApplicazioni.registraUtente(applicazione.getUtente());
								} catch (RemoteException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
									return;
								}
								//System.out.println(registrazioneDlg.getUsername());

								//utente.setUsername(registrazioneDlg.getUsername());
								//utente.setPassword(registrazioneDlg.getPassword());

								try {
									mostraGUI(id);
								} catch (BiffException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
				});
		return !(frame.isVisible());

	}
	
	public void logout() throws RemoteException {
		this.applicazione.setUtente(null);
		frame.setVisible(false);
		this.loginGrafico(this.applicazione.getIdentificativo());
	}
	
	public void mostraGUI(int id) throws BiffException, IOException {
		JButton segnalaCodaBtn = new JButton("Segnala coda");
		JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");
		JButton logoutBtn = new JButton("Logout");

		frame = new JFrame();
		frame.setTitle("Applicazione mobile");

		// da https://coderanch.com/t/341045/java/expand-JTextArea-main-panel-resized
		// https://stackoverflow.com/questions/33100147/how-to-set-window-size-without-extending-jframe

		paneNotifiche.setPreferredSize(new Dimension(175,150));

		JPanel jp = new JPanel(new BorderLayout());
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();

		JPanel left = new JPanel();
		JPanel right = new JPanel();
		jp.add(top,BorderLayout.NORTH);
		jp.add(bottom,BorderLayout.SOUTH);
		jp.add(left,BorderLayout.WEST);
		jp.add(right,BorderLayout.EAST);
		jp.add(paneNotifiche,BorderLayout.CENTER);
		frame.getContentPane().add(jp);

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
		bottom.add(segnalaCodaBtn);

		logoutBtn.addActionListener(e -> {
			try {
				logout();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bottom.add(logoutBtn);

		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());

		bottom.add(svuotaNotificheBtn);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


	}
	
	private void pulisciNotifiche() {
		areaNotifiche.setText("");
	}

	public void run(){
		System.setSecurityManager(new RMISecurityManager());
		try {
			System.out.println("Security Manager loaded");
			String url="rmi://localhost/GestoreApplicazioni"; //mettere :1099 dopo localhost se richiesto
			this.iGestoreApplicazioni = (IGestoreApplicazioni) Naming.lookup(url);
			System.out.println("Got remote object");
			iGestoreApplicazioni.aggiungiApplicazione(this.applicazione);
			this.applicazione.setInterfaccia(iGestoreApplicazioni);
			

			loginGrafico(this.applicazione.getIdentificativo());

		}
		catch (RemoteException exc) {
			System.out.println("Error in lookup: "+exc.toString());
		}
		catch (java.net.MalformedURLException exc) {
			System.out.println("malformed URL: "+exc.toString());
		}
		catch (NotBoundException exc) {
			System.out.println("NotBound: "+exc.toString());
		}



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
