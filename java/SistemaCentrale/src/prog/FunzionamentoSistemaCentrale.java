package prog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
						//		posizionaPuntiCasuali();
						
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
						//	try {
								mappa=visualizzazioneMappaBase();
							/*	posizionaPuntiCasuali();
							} catch (BiffException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NotBoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}*/
						}
					}
				});
		return !(frame.isVisible());

	}

	/*public static void posizionaPuntiTest() {

		Posizione p1 = new Posizione("",55,55);
		Posizione p2 = new Posizione("",33,44);
		Posizione p3 = new Posizione("",50,69);
		Posizione p4 = new Posizione("",20,43);
		Posizione p5 = new Posizione("",11, 79);

		DatoGenerico d1 = new DatoGenerico(p1, "M100 Coda", "11/12/2009", "11:32");
		DatoGenerico d2 = new DatoGenerico(p2, "S200 Traffico elevato", "10/08/2004", "12:30");
		DatoGenerico d3 = new DatoGenerico(p3, "S120 Coda", "19/03/2098", "07:32");
		DatoGenerico d4 = new DatoGenerico(p4, "S30 Velocità lenta", "11/02/1969", "17:13");
		DatoGenerico d5 = new DatoGenerico(p5, "S90 Qualcosa di indefinito", "07/07/1003", "00:02");

		ArrayList<DatoGenerico> listaTest = new ArrayList<DatoGenerico>();
		listaTest.add(d1);
		listaTest.add(d2);
		listaTest.add(d3);
		listaTest.add(d4);
		listaTest.add(d5);

		mappa.aggiornaMappa(listaTest);
	}*/

/*	public static void posizionaPuntiCasuali() throws BiffException, IOException, InterruptedException, NotBoundException {
		GestoreDatabase dataProva = GestoreDatabase.getInstance();
	//	SensoreGPSAuto sensoreCasuale = new SensoreGPSAuto();
		Posizione casuale = new Posizione();
		String tipoCasuale = new String();
		String dataCasuale = new String();
		String oraCasuale = new String();

		for (int i=0; i<5; ++i)
		{
			oraCasuale="";
			tipoCasuale = "";
	//		casuale = sensoreCasuale.rilevaPosizione();
			Random r = new Random();
			dataCasuale = String.valueOf((r.nextInt(27)+1))+"/"+String.valueOf((r.nextInt(11)+1))+"/"+String.valueOf((r.nextInt(2017)+1));
			oraCasuale = String.valueOf(r.nextInt(24))+":"+String.valueOf(r.nextInt(60));
			char c = r.nextBoolean() ? 'M' : 'S';
			tipoCasuale += c;
			int n = r.nextInt(100) + 1;
			tipoCasuale += String.valueOf(n);
			tipoCasuale += " ";

			int evento = r.nextInt(4);
			if(evento == 0)
			{
				tipoCasuale += "Coda";
			}
			else if(evento == 1)
			{
				tipoCasuale += "Traffico elevato";
			}
			else if(evento == 2)
			{
				tipoCasuale += "Velocità lenta";
			}
			else
			{
				tipoCasuale += "Qualcosa di incomprensibile";
			}

			dataProva.aggiornaTabellaTraffico("centralina", casuale, tipoCasuale, dataCasuale, oraCasuale);

		}


	}*/
	
    public static void logout() {
    	mappa.setVisible(false);
    	loginGrafico();
    } 

	public static MappaGrafica getMappa() {
		return mappa;
	}

	public static void main(String[] args) throws BiffException, IOException {

		GestoreApplicazioni GApp=GestoreApplicazioni.getInstance();
		GestoreCentraline GCent=GestoreCentraline.getInstance();
		GestoreUtenti GUt=GestoreUtenti.getInstance();
		GestoreAmministratori GAmm=GestoreAmministratori.getInstance();
	

		//mappa = visualizzazioneMappaBase();

		Thread t9= new Thread(new GestoreApplicazioniServer());
		t9.start();
		
		Thread t12= new Thread(new GestoreCentralineServer());
		t12.start();
	
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


