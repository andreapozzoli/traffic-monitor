package prog;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FunzionamentoCentralinaS {

	public static void configurazioneGrafica(){
		try {

			String percorsoCorrente = System.getProperty("user.dir"); // percorso corrente
			Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls")); // nome del file con vie e relative posizioni
			Sheet sheet = wb.getSheet(0); // primo foglio del foglio di calcolo

			JFrame frame = new JFrame("Centralina stradale"); // titolo del frame

			JPanel panel = new JPanel(new GridBagLayout()); // impostazione del layout
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.BOTH;

			final JLabel labelVia = new JLabel("Via:"); // Etichetta
			final JTextField fieldVia = new JTextField(20); // Campo per inserire il nome della via o della piazza (es. via valleggio)

			final JLabel labelTipoStrada = new JLabel("Tipo di strada:"); // etichetta

			String[] scelteTipoStrada = {"urbana", "extraurbana", "superstrada"}; // possibili scelte per il tipo di strada

			final JComboBox listaTipoStrada = new JComboBox(scelteTipoStrada); // impostazione della lista di scelta

			final JLabel labelIntervallo = new JLabel("Intervallo di tempo iniziale [s]:"); // etichetta
			
			/* modello per lo spinner per selezionare l'intervallo di aggiornamento iniziale
			 * (minimo: 10, valore mostrato 
			 * inizialmente: 10, massimo: 360 000, step: 1) */
			
			final SpinnerNumberModel sceltaIntervallo = new SpinnerNumberModel(10, 10, 90, 1); 
			final JSpinner spinner = new JSpinner(sceltaIntervallo); // spinner

			// Impostazione dei margini per una migliore disposizioen grafica
			labelVia.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			labelTipoStrada.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
			labelIntervallo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

			listaTipoStrada.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			spinner.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

			// Disposizione degli elementi secondo una griglia
			cs.gridx = 0; // colonna 0
			cs.gridy = 0; // riga 0
			cs.gridwidth = 1; // ampiezza in colonne 1
			panel.add(labelVia, cs); // Aggiunta alla griglia come definito dalle tre linee precedenti

			cs.gridx = 1;
			cs.gridy = 0;
			cs.gridwidth = 2;
			panel.add(fieldVia, cs);

			cs.gridx = 0;
			cs.gridy = 1;
			cs.gridwidth = 1;
			panel.add(labelTipoStrada, cs);

			cs.gridx = 1;
			cs.gridy = 1;
			cs.gridwidth = 2;
			panel.add(listaTipoStrada, cs);

			cs.gridx = 0;
			cs.gridy = 2;
			cs.gridwidth = 1;
			panel.add(labelIntervallo, cs);

			cs.gridx = 1;
			cs.gridy = 2;
			cs.gridwidth = 1;
			panel.add(spinner, cs);

			panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // creazione di un margine anche per il pannello

			JPanel velocitaPanel = new JPanel(new GridBagLayout());
			GridBagConstraints velGrid = new GridBagConstraints();

			velGrid.fill = GridBagConstraints.BOTH;

			final JLabel labelVelocita = new JLabel("Velocit� [km/h]:");
			final SpinnerNumberModel modelloVelocita = new SpinnerNumberModel(20, 1, 110, 1);
			final JSpinner spinnerVelocita = new JSpinner(modelloVelocita);

			JCheckBox domandaVelocita = new JCheckBox("Selezionare per impostare una velocit� iniziale casuale");

			labelVelocita.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			spinnerVelocita.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			domandaVelocita.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));


			velGrid.gridx = 0;
			velGrid.gridy = 0;
			velGrid.gridwidth = 1;
			velocitaPanel.add(labelVelocita, velGrid);

			velGrid.gridx = 1;
			velGrid.gridy = 0;
			velGrid.gridwidth = 2;
			velocitaPanel.add(spinnerVelocita, velGrid);

			spinnerVelocita.setEnabled(true);

			velGrid.gridx = 0;
			velGrid.gridy = 1;
			velGrid.gridwidth = 2;
			velocitaPanel.add(domandaVelocita, velGrid);

			velocitaPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));


			JPanel bottoniPanel = new JPanel(new GridBagLayout());
			GridBagConstraints bottGrid = new GridBagConstraints();

			bottGrid.fill = GridBagConstraints.BOTH;

			JButton btnOK = new JButton("OK"); // bottone
			JButton btnGeneraTraffico = new JButton("Imporre una velocit�"); // bottone
			btnGeneraTraffico.setEnabled(false);


			bottGrid.gridx = 0;
			bottGrid.gridy = 0;
			bottGrid.gridwidth = 1;
			bottoniPanel.add(btnOK, bottGrid);

			bottGrid.gridx = 1;
			bottGrid.gridy = 0;
			bottGrid.gridwidth = 1;


			bottoniPanel.add(btnGeneraTraffico, bottGrid);



			bottoniPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

			frame.add(panel, BorderLayout.PAGE_START);
			frame.add(velocitaPanel, BorderLayout.CENTER);
			frame.add(bottoniPanel, BorderLayout.PAGE_END);

			frame.pack();
			frame.setLocationRelativeTo(frame);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // azione predefinita quando si chiude usando il pulsante di chiusura della finestra
			frame.setSize(470, 250); // dimensione del frame (altezza * larghezza)

			frame.setVisible(true);


			

			
			domandaVelocita.addActionListener( // comportamento quando si vuole imporre una velocità casuale
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							spinnerVelocita.setEnabled(!spinnerVelocita.isEnabled()); // cambia lo stato di attivazione dello spinner di velocità
							btnGeneraTraffico.setEnabled(spinnerVelocita.isEnabled() && !btnOK.isEnabled()); 
							/* attiva il bottone per l'imposizione della velocità se lo spinner è abilitato e il bottone OK è disabilitato
							 * (quindi è possibile abilitare il bottone soltanto dopo l'avvio della centralina e solo se è stata selezionata
							 * una velocità da imporre).
							 */
						}
					});


			btnOK.addActionListener( // comportamento alla pressione del bottone OK
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {

							boolean trovato = false;
							int i = 0;
							Cell cella;

							// Ricerca della posizione in base al nome della via/piazza
							for(i=0;i<543;i++) {
								cella=sheet.getCell(0,i);
								if(cella.getContents().toLowerCase().equals(fieldVia.getText().toLowerCase())) {
									trovato=true;
									break;
								}
							}
							if(!trovato) { // se l'indirizzo non viene trovato
								JOptionPane.showMessageDialog(frame, "L'indirizzo inserito non � stato trovato.");
							}
							else
							{
								// genera la posizione in base alle informazioni trovate
								Posizione posizione=new Posizione(fieldVia.getText(),Double.valueOf(sheet.getCell(1,i).getContents()),Double.valueOf(sheet.getCell(2,i).getContents()));
								// imposta il tipo di strada in base alla selezione fatta
								String tipoStrada = (String) listaTipoStrada.getSelectedItem();
								// legge l'intervallo iniziale in base al valore dello spinner
								int intervallo = (int) spinner.getModel().getValue();

								CentralinaStradale centralina=new CentralinaStradale(intervallo,posizione,tipoStrada);
								
								if(spinnerVelocita.isEnabled())
								{
									centralina.getRilevatoreVeicoli().getRilevatoreVelocita().setVelocita((int) spinnerVelocita.getModel().getValue());
								}
								else {
									Random random = new Random();
									int a=0; // valore minimo di velocità
									int b=110; // valore massimo di velocità
									int c = ((b-a) + 1); // range di valori di velocità
									int vel = random.nextInt(c) + a; // velocità casuale
									centralina.getRilevatoreVeicoli().getRilevatoreVelocita().setVelocita(vel);
								}

								// Vengono disabilitati gli oggetti non più utilizzati e attivati quelli utilizzabili
								fieldVia.setEnabled(false);
								spinner.setEnabled(false);
								listaTipoStrada.setEnabled(false);
								btnOK.setEnabled(false);
								btnGeneraTraffico.setEnabled(true);
								domandaVelocita.setEnabled(false);
								spinnerVelocita.setEnabled(true);
																
								// Impostazione del titolo della finestra
								String titolo = frame.getTitle() + " (" + inizialiMaiuscole(fieldVia.getText()) + ")";
								frame.setTitle(titolo);

								btnGeneraTraffico.addActionListener(
										new ActionListener(){
											public void actionPerformed(ActionEvent e) {
												if(spinnerVelocita.isEnabled()) { // imponi la velocità selezionata in quell'istante
													centralina.getRilevatoreVeicoli().getRilevatoreVelocita().setVelocita((int) spinnerVelocita.getModel().getValue());
												}
												else
												{
													JOptionPane.showMessageDialog(frame, "Nessuna velocit� selezionata.");
												}
											}
										});
								
								//thread rilevatore veicoli
								Thread t1=new Thread(centralina.getRilevatoreVeicoli());
								t1.start();

								//deve solo aspettare che scada l'intervallo di tempo corrente
								//scaduto l'intervallo calcola immediatamente quello successivo e crea il dato di traffico
								Thread t2=new Thread(centralina);
								t2.start();
								
								
							}
						}

					});






		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static String inizialiMaiuscole(String stringaDaTrasformare) {
		String[] parole = stringaDaTrasformare.split(" ");

		for(int p=0; p<parole.length; ++p) {
			parole[p] = parole[p].substring(0,1).toUpperCase() + parole[p].substring(1).toLowerCase();
		}
								
		return String.join(" ", parole);
	}

	public static void main(String[] args) {

		configurazioneGrafica();

	}


	





}
