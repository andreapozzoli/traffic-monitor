package gestionetraffico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class AppMobileGrafica extends JFrame {

	private static final long serialVersionUID = 1L;


	private static JTextArea areaTesto = new JTextArea();
	private static JScrollPane areaNotifiche = new JScrollPane(areaTesto);
	private static JPanel panel = new JPanel(new BorderLayout());
	private static JPanel panelTop = new JPanel();
	private static JPanel panelBottom = new JPanel();
	
	private static JButton segnalaCodaBtn = new JButton("Segnala coda");
	private static JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");

	public AppMobileGrafica() {
		super("App mobile");

		add(panel, BorderLayout.SOUTH);

		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelBottom, BorderLayout.SOUTH);
		
		panelTop.add(areaNotifiche, BorderLayout.CENTER);
		
		/*areaNotifiche.setLineWrap(true);
		areaNotifiche.setWrapStyleWord(true);*/
		
		segnalaCodaBtn.addActionListener(e -> segnalaCoda());
		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());
		
		panelBottom.add(segnalaCodaBtn);
		panelBottom.add(svuotaNotificheBtn);
		
		

		
		
	}

	public static void creaGUI(){
		JFrame frame = new AppMobileGrafica();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}


	public void segnalaCoda() {
		System.out.println("Coda");
	}

	private void pulisciNotifiche() {
		//areaNotifiche.
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				creaGUI();
			}
		});
	}

}

