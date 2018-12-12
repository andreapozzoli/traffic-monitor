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
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FunzionamentoCentralinaS {

	public static void main(String[] args) {
		try {
			//Workbook wb;

			String percorsoCorrente = System.getProperty("user.dir");
			Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls"));
			Sheet sheet = wb.getSheet(0);
			Scanner sc;

			JFrame frame = new JFrame("Centralina stradale");

			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.HORIZONTAL;

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


			final JButton btnOK = new JButton("OK");


			JPanel bp = new JPanel();
			bp.add(btnOK);

			frame.add(panel, BorderLayout.CENTER);
			frame.add(bp, BorderLayout.PAGE_END);

			frame.pack();
			frame.setLocationRelativeTo(frame);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 150);
			frame.setLayout(new FlowLayout());
			frame.getContentPane().add(btnOK);
			frame.setVisible(true);


			btnOK.addActionListener(e -> {

				boolean trovato = false;
				int i = 0;
				Cell cella;

				for(i=0;i<543;i++) {
					cella=sheet.getCell(0,i);
					if(cella.getContents().toLowerCase().equals(fieldVia.getText())) {
						trovato=true;
						break;
					}
				}
				if(!trovato) {
					JOptionPane.showMessageDialog(frame, "L'indirizzo inserito non è stato trovato.");
				}
				else
				{
					Posizione posizione=new Posizione(fieldVia.getText(),Double.valueOf(sheet.getCell(1,i).getContents()),Double.valueOf(sheet.getCell(2,i).getContents()));
					String tipoStrada = (String) listaTipoStrada.getSelectedItem();
					int intervallo = (int) spinner.getModel().getValue();
					
					CentralinaStradale centralina=new CentralinaStradale(intervallo,posizione,tipoStrada);

					//thread rilevatore veicoli
					Thread t1=new Thread(centralina.getRilevatoreVeicoli());
					t1.start();

					//deve solo aspettare che scada l'intervallo di tempo corrente
					//scaduto l'intervallo calcola immediatamente quello successivo e crea il dato di traffico
					Thread t2=new Thread(centralina);
					t2.run();
				}


			});


			
			
			

		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}








}
