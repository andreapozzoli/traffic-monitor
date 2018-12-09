package gestionetraffico;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import jxl.read.biff.BiffException;


public class ApplicazioneMobile /*extends UnicastRemoteObject implements IApplicazioneMobile*/ {
	private int identificativo;
	private ArrayList<NotificaApplicazione> listaNotificheRicevute;
	private SensoreGPS sensore;
	private Posizione posizione;
	private Utente utente;

	private JFrame frame;
	private final long serialVersionUID = 1L;

	private JTextArea areaNotifiche = new JTextArea(10, 10);
	private JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);


	public ApplicazioneMobile() throws BiffException, IOException /*throws RemoteException*/{
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);
	}

	public ApplicazioneMobile(int id) throws BiffException, IOException /*throws RemoteException*/ {

		super ();

		loginGrafico(id);
	}
	
	public void mostraGUI(int id) throws BiffException, IOException {
		this.identificativo=id;
		this.sensore = new SensoreGPSTelefono();
		this.listaNotificheRicevute=new ArrayList<NotificaApplicazione>();
		this.posizione=this.sensore.rilevaPosizione();
		GestoreApplicazioni.getInstance().aggiungiApplicazione(this);

		JButton segnalaCodaBtn = new JButton("Segnala coda");
		JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");

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

		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());

		bottom.add(svuotaNotificheBtn);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


	}

	public void segnalaCoda() throws BiffException, IOException  {

		this.posizione=this.sensore.rilevaPosizione();
		//NotificaApplicazione notifica=new NotificaApplicazione("user", this.posizione,"coda");
		NotificaApplicazione notifica=new NotificaApplicazione(this.utente.getUsername(),this.posizione, "coda");
		//nuovo
		GestoreApplicazioni.getInstance().segnalaDatabase(notifica);



	}

	private void pulisciNotifiche() {
		areaNotifiche.setText("");
	}


	public int getIdentificativo() {
		return this.identificativo;
	}

	public void setIdentificativo(int id) {
		this.identificativo=id;
	}

	public void aggiornaPosizione() throws BiffException, IOException {
		this.posizione=this.sensore.rilevaPosizione();
	}

	public Posizione getPosizione() throws BiffException, IOException {
		aggiornaPosizione();
		return this.posizione;
	}

	public String getUsenameUtente() {
		return this.utente.getUsername();
	}

	//modifica
	public void segnalaUtente(NotificaApplicazione notifica ) {
		//metodo per segnalare all'utente la ricezione di una notifica
		notifica.stampaNotifica();
	}

	public NotificaApplicazione creaNotificaApplicazione(Posizione pos, String tipo) {
		return new NotificaApplicazione("SistemaCentrale",pos,tipo);
	}

	public void aggiungiNotificaInCoda(NotificaApplicazione notifica) {
		this.listaNotificheRicevute.add(notifica);
		this.segnalaUtente(notifica);
	}

	public void svuotaLista() {
		this.listaNotificheRicevute.clear();
	}

	//modificato

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

		btnLogin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoginDialog loginDlg = new LoginDialog(frame, "U");
						loginDlg.setVisible(true);

						if(loginDlg.isSucceeded()){
							loginDlg.setVisible(false);
							frame.setVisible(false);
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
						RegistrazioneDlg registrazioneDlg = new RegistrazioneDlg(frame, "U");
						registrazioneDlg.setVisible(true);

						if(registrazioneDlg.isSucceeded()){
							registrazioneDlg.setVisible(false);
							frame.setVisible(false);
							System.out.println("prova2");
						}
					}
				});
		return !(frame.isVisible());

	}


	public boolean login() {
		//registrato inserisce dati e li verifica
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username=sc.nextLine();
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		if(GestoreApplicazioni.getInstance().verificaAccesso(username, password)) {
			this.utente=GestoreApplicazioni.getInstance().passaggioUtente(username);
			return true;
		}
		else
			return false;

	}

	//modificato
	public boolean registraUtente() {
		//registrare nuovo utente
		System.out.println("Inserisci username: ");
		Scanner sc= new Scanner(System.in);
		String username;
		while(true) {
			username=sc.nextLine();
			if(GestoreApplicazioni.getInstance().verificaAccesso(username)) {
				System.out.println("Username gi� in uso");
			}	
			else {
				break;
			}
		}
		System.out.println("Inserisci password: ");
		Scanner sc1= new Scanner(System.in);
		String password=sc.nextLine();
		this.utente=new Utente(username,password);
		GestoreApplicazioni.getInstance().registraUtente(utente);
		return true;
	}	

	//nuova
	public void logout() {
		this.utente=null;
	}

	/*	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//creaGUI();
				try {
					new ApplicazioneMobile();
				} catch (BiffException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}*/

}
