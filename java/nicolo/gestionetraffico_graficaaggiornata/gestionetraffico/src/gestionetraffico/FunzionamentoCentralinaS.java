package gestionetraffico;

import java.util.*;

import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FunzionamentoCentralinaS {

	public void configurazioneGrafica(){
		try {
			//Workbook wb;

			String percorsoCorrente = System.getProperty("user.dir");
			Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls"));
			Sheet sheet = wb.getSheet(0);

			JFrame frame = new JFrame("Centralina stradale");

			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.BOTH;

			final JLabel labelVia = new JLabel("Via:");
			final JTextField fieldVia = new JTextField(20);

			final JLabel labelTipoStrada = new JLabel("Tipo di strada:");

			String[] scelteTipoStrada = {"urbana", "extraurbana", "superstrada", "autostrada"};

			final JComboBox listaTipoStrada = new JComboBox(scelteTipoStrada);

			final JLabel labelIntervallo = new JLabel("Intervallo di tempo iniziale [s]:");
			final SpinnerNumberModel sceltaIntervallo = new SpinnerNumberModel(11, 11, 360000, 1);
			final JSpinner spinner = new JSpinner(sceltaIntervallo);

			labelVia.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			labelTipoStrada.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
			labelIntervallo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

			listaTipoStrada.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			spinner.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

			cs.gridx = 0;
			cs.gridy = 0;
			cs.gridwidth = 1;
			panel.add(labelVia, cs);

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

			panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

			JPanel velocitaPanel = new JPanel(new GridBagLayout());
			GridBagConstraints velGrid = new GridBagConstraints();

			velGrid.fill = GridBagConstraints.BOTH;

			final JLabel labelVelocita = new JLabel("Velocità iniziale [km/h]:");
			final SpinnerNumberModel modelloVelocita = new SpinnerNumberModel(20, 1, 300, 1);
			final JSpinner spinnerVelocita = new JSpinner(modelloVelocita);

			JCheckBox domandaVelocita = new JCheckBox("Selezionare per impostare una velocità casuale");

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

			JButton btnOK = new JButton("OK");
			JButton btnGeneraTraffico = new JButton("Imporre una velocità");
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

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 250);
			//frame.setLayout(new FlowLayout());
			//frame.getContentPane().add(btnOK);
			frame.setVisible(true);


			

			
			domandaVelocita.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							spinnerVelocita.setEnabled(!spinnerVelocita.isEnabled());
							btnGeneraTraffico.setEnabled(spinnerVelocita.isEnabled() && !btnOK.isEnabled());
						}
					});


			btnOK.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {

							boolean trovato = false;
							int i = 0;
							Cell cella;

							for(i=0;i<543;i++) {
								cella=sheet.getCell(0,i);
								if(cella.getContents().toLowerCase().equals(fieldVia.getText().toLowerCase())) {
									trovato=true;
									break;
								}
							}
							if(!trovato) {
								JOptionPane.showMessageDialog(frame, "L'indirizzo inserito non � stato trovato.");
							}
							else
							{
								//frame.setVisible(false);
								Posizione posizione=new Posizione(fieldVia.getText(),Double.valueOf(sheet.getCell(1,i).getContents()),Double.valueOf(sheet.getCell(2,i).getContents()));
								String tipoStrada = (String) listaTipoStrada.getSelectedItem();
								int intervallo = (int) spinner.getModel().getValue();

								CentralinaStradale centralina=new CentralinaStradale(intervallo,posizione,tipoStrada);
								
								if(spinnerVelocita.isEnabled())
								{
									centralina.setVelocita((int) spinnerVelocita.getModel().getValue());
								}

								fieldVia.setEnabled(false);
								spinner.setEnabled(false);
								listaTipoStrada.setEnabled(false);
								btnOK.setEnabled(false);
								btnGeneraTraffico.setEnabled(true);
								
								String titoloQuasiCompleto = frame.getTitle() + " (" + fieldVia.getText() + ")";
								frame.setTitle(titoloQuasiCompleto);

								btnGeneraTraffico.addActionListener(
										new ActionListener(){
											public void actionPerformed(ActionEvent e) {
												if(spinnerVelocita.isEnabled()) {
													centralina.setVelocita((int) spinnerVelocita.getModel().getValue());
												}
												else
												{
													JOptionPane.showMessageDialog(frame, "Nessuna velocità selezionata.");
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

	public void main(String[] args) {

		configurazioneGrafica();

	}








}
