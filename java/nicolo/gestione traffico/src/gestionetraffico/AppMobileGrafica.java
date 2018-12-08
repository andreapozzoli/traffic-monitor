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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AppMobileGrafica extends JFrame {

	private static final long serialVersionUID = 1L;

	public AppMobileGrafica() {
		super("App mobile");
		setSize(400, 400);

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelTop = new JPanel();
		JPanel panelBottom = new JPanel();


		add(panel, BorderLayout.SOUTH);

		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelBottom, BorderLayout.SOUTH);

		JButton segnalaCodaBtn = new JButton("Segnalare una coda");
		JButton pulisciNotificheBtn = new JButton("Svuotare l'area delle notifiche");


		segnalaCodaBtn.addActionListener(e -> segnalaCoda());
		pulisciNotificheBtn.addActionListener(e -> pulisciNotifiche());

		panelBottom.add(segnalaCodaBtn);
		panelBottom.add(pulisciNotificheBtn);





	}
	
	
	public void segnalaCoda() {
		System.out.println("Coda");
	}
	
	public void pulisciNotifiche() {
		System.out.println("Pulizia notifiche");
	}
	
	public static void main(String[] args) {
		new AppMobileGrafica().setVisible(true);

	}

}
