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

public class AppMobileGrafica /*extends JFrame*/ {

	private JFrame frame;
	private static final long serialVersionUID = 1L;

	private static JTextArea areaNotifiche = new JTextArea(10, 10);
	private static JScrollPane paneNotifiche = new JScrollPane(areaNotifiche);
	private static JPanel panel = new JPanel(new BorderLayout());
	private static JPanel panelTop = new JPanel();
	private static JPanel panelBottom = new JPanel();

	private static JButton segnalaCodaBtn = new JButton("Segnala coda");
	private static JButton svuotaNotificheBtn = new JButton("Svuotare area delle notifiche");
	

	public AppMobileGrafica() {
		frame = new JFrame();

		frame.setTitle("Applicazione mobile");
	
		// da https://coderanch.com/t/341045/java/expand-JTextArea-main-panel-resized
		// https://stackoverflow.com/questions/33100147/how-to-set-window-size-without-extending-jframe

		paneNotifiche.setPreferredSize(new Dimension(175,150));

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
		frame.getContentPane().add(jp);

		segnalaCodaBtn.addActionListener(e -> segnalaCoda());
		svuotaNotificheBtn.addActionListener(e -> pulisciNotifiche());


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}



	public void segnalaCoda() {
		// todo
	}

	private void pulisciNotifiche() {
		areaNotifiche.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//creaGUI();
				new AppMobileGrafica();
			}
		});
	}

}

