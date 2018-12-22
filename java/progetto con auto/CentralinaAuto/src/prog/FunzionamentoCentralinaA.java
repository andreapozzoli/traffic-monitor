package prog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FunzionamentoCentralinaA {
	
	private static JLabel velocitaLabel;
	private static JLabel posizioneLabel;
	
	public static void main(String[] args) throws BiffException, IOException {
		Random random = new Random();
		int minVel =0;
		int maxVel=110;
		int intornoVel = ((maxVel-minVel) + 1);
		int velocita= random.nextInt(intornoVel) + minVel;
		Random random2 = new Random();
		int minPos =0;
		int maxPos=543;
		int intornoPos = ((maxPos-minPos) + 1);
		int posizione= random2.nextInt(intornoPos) + minPos;
		String via;
		String percorsoCorrente = System.getProperty("user.dir");
		Workbook wb= Workbook.getWorkbook(new File(percorsoCorrente + "/vie3.xls")); // file excel contenente nome via e relative latitudine e longitudine
		Sheet sheet = wb.getSheet(0);		
		Cell cella = sheet.getCell(0,posizione); // nella prima colonna vi e' il nome della via
		via = cella.getContents();
		cella=sheet.getCell(1,posizione); // nella seconda colonna vi e' la latitudine
		double lat2=Double.valueOf(cella.getContents());
		cella=sheet.getCell(2,posizione); // nella terza colonna vi e' la longitudine
		double lon2=Double.valueOf(cella.getContents());
		CentralinaAuto auto=new CentralinaAuto(new Posizione(via, lat2, lon2), velocita);
		Thread t1=new Thread(auto);
		t1.start();
		mostraGUI();
	}
	
	public static void mostraGUI() 
	{
		JFrame frame = new JFrame("Centralina automobilistica"); // titolo del frame

		JPanel panel = new JPanel(new GridBagLayout()); // impostazione del layout
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.BOTH;

		velocitaLabel = new JLabel("Nessuna velocita' rilevata");
		posizioneLabel = new JLabel("Nessuna posizione rilevata");
		
		// Disposizione degli elementi secondo una griglia
		cs.gridx = 0; // colonna 0
		cs.gridy = 0; // riga 0
		cs.gridwidth = 1; // ampiezza in colonne 1
		panel.add(velocitaLabel, cs); // Aggiunta alla griglia come definito dalle tre linee precedenti
		
		
		cs.gridx = 0; // colonna 0
		cs.gridy = 1; // riga 1
		cs.gridwidth = 1; // ampiezza in colonne 1
		panel.add(posizioneLabel, cs); // Aggiunta alla griglia come definito dalle tre linee precedenti
		
		frame.add(panel);
		
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // azione predefinita quando si chiude usando il pulsante di chiusura della finestra
		frame.setSize(300, 100); // dimensione del frame (larghezza * altezza)

		frame.setVisible(true);
	}
	
	public static void setVelocitaLabel(int velocita) {
		velocitaLabel.setText("Ultima velocita' rilevata: " + velocita + " km/h");
	}
	
	public static void setPosizioneLabel(String via) {
		posizioneLabel.setText("Ultima posizione rilevata: " + via);
	}

}
