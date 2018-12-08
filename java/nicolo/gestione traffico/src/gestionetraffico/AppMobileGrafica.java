package gestionetraffico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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


	private static JTextArea areaNotifiche = new JTextArea(10, 10);
	private static JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);
	private static JPanel panel = new JPanel(new BorderLayout());
	private static JPanel panelTop = new JPanel();
	private static JPanel panelBottom = new JPanel();
	
	private static JButton segnalaCodaBtn = new JButton("Segnala coda");
	private static JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");

	public AppMobileGrafica() {
		super("App mobile");

		// da https://coderanch.com/t/341045/java/expand-JTextArea-main-panel-resized
		
	    paneNotifiche.setPreferredSize(new Dimension(175,150));

		/*add(panel, BorderLayout.SOUTH);

		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelBottom, BorderLayout.SOUTH);
		
		
		
		segnalaCodaBtn.addActionListener(e -> segnalaCoda());
		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());
		
		panelBottom.add(segnalaCodaBtn);
		panelBottom.add(svuotaNotificheBtn);*/
	    
	    JPanel jp = new JPanel(new BorderLayout());
	    JPanel top = new JPanel();
	    JPanel bottom = new JPanel();
	    bottom.add(segnalaCodaBtn);
	    bottom.add(svuotaNotificheBtn);

	    JPanel left = new JPanel();
	    JPanel right = new JPanel();
	    jp.add(top,BorderLayout.NORTH);
	    jp.add(bottom,BorderLayout.SOUTH);
	    jp.add(left,BorderLayout.WEST);
	    jp.add(right,BorderLayout.EAST);
	    jp.add(paneNotifiche,BorderLayout.CENTER);
	    getContentPane().add(jp);
	    
	    segnalaCodaBtn.addActionListener(e -> segnalaCoda());
		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());
		
		

		
		
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
		areaNotifiche.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				creaGUI();
			}
		});
	}

}

