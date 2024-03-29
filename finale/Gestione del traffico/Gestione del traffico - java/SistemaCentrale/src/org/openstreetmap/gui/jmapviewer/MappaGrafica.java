// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import prog.DatoGenerico;
import prog.FunzionamentoSistemaCentrale;

/**
 * Demonstrates the usage of {@link JMapViewer}
 *
 * @author Jan Peter Stotz
 *
 */
public class MappaGrafica extends JFrame implements JMapViewerEventListener {

	private static final long serialVersionUID = 1L;

	private final JMapViewerTree treeMap;

	private final JLabel zoomLabel;
	private final JLabel zoomValue;

	private final JLabel mperpLabelName;
	private final JLabel mperpLabelValue;

	public String creaEtichetta(DatoGenerico dato)
	{
		if(dato.getTipo().substring(0,1).equals("M")) {
			return dato.getData() + " " + dato.getOra();
		}
		else
		{
			return dato.getData() + " " + dato.getOra() + " " + dato.getTipo().substring(1, dato.getTipo().indexOf(' ')) + "km/h";
		}
	}

	public void aggiornaMappa(ArrayList<DatoGenerico> listaDati) {
		pulisciMappa();
		for (DatoGenerico dato : listaDati) {
			if(dato.getPosizione() != null)
			{
				if(dato.getTipo().endsWith("Coda"))
				{
					aggiungiCoda(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
				}
				else if(dato.getTipo().endsWith("Traffico elevato"))
				{
					aggiungiTraffico(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
				}
				else if(dato.getTipo().endsWith("Velocita lenta")) {
					aggiungiVelocitaLenta(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
				}
				else
				{
					aggiungiVuota(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());

				}
			}
		}

	}

	public void aggiungiPunto(DatoGenerico dato) {

		if(dato.getPosizione() != null)
		{
			if(dato.getTipo().endsWith("Coda"))
			{
				aggiungiCoda(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
			}
			else if(dato.getTipo().endsWith("Traffico elevato"))
			{
				aggiungiTraffico(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
			}
			else if(dato.getTipo().endsWith("Velocita lenta")) {
				aggiungiVelocitaLenta(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());
			}
			else
			{
				aggiungiVuota(creaEtichetta(dato), dato.getPosizione().getLatitudine(), dato.getPosizione().getLongitudine());

			}

		}

	}

	public void pulisciMappa() {
		map().removeAllMapMarkers();
	}

	public MapMarkerDot aggiungiVuota(String etichetta, double lat, double lon) {
		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color.GREEN);
		// per le centraline che non hanno ancora rilevato nulla di particolare
	}

	public MapMarkerDot aggiungiCoda(String etichetta, double lat, double lon) {

		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color.RED);
	}

	public MapMarkerDot aggiungiVelocitaLenta(String etichetta, double lat, double lon) {

		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color.BLUE);
	}

	public MapMarkerDot aggiungiTraffico(String etichetta, double lat, double lon) {

		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color.BLACK);
	}

	public MapMarkerDot aggiungiMarcatoreGenerico(String etichetta, double lat, double lon, Color colore)
	{
		Coordinate posizione = new Coordinate(lat, lon);

		Font font = new Font("Helvetica", Font.PLAIN, 13);

		Style stile = new Style(Color.BLACK, colore, null, font);

		MapMarkerDot marcatoreGenerico = new MapMarkerDot(null, etichetta, posizione, stile);

		map().addMapMarker(marcatoreGenerico);

		return marcatoreGenerico;
	}

	public void rimuoviMarcatore(double lat, double lon) {
		ArrayList<MapMarker> listaEliminare = new ArrayList<MapMarker>();
		for (MapMarker m : map().getMapMarkerList()) {
			if(m.getLat() == lat && m.getLon() == lon) {
				listaEliminare.add(m);
			}
		}

		for (int i=listaEliminare.size()-1; i>=0;--i) {
			map().removeMapMarker(listaEliminare.get(i));
		}

	}

	public MappaGrafica() {
		super("Mappa del traffico");
		setSize(400, 400);

		treeMap = new JMapViewerTree("Zone");

		map().addJMVListener(this);

		setLayout(new BorderLayout());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelTop = new JPanel();
		JPanel panelBottom = new JPanel();
		JPanel helpPanel = new JPanel();

		mperpLabelName = new JLabel("Metri/Pixel: ");
		mperpLabelValue = new JLabel(String.format("%.5g%n", map().getMeterPerPixel()));

		zoomLabel = new JLabel("Zoom: ");
		zoomValue = new JLabel(String.format("%s", map().getZoom()));

		add(panel, BorderLayout.SOUTH);
		add(helpPanel, BorderLayout.EAST);

		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelBottom, BorderLayout.SOUTH);

		String informazioni = "<html>Usare il pulsante destro<br>del mouse per muoversi,<br> "
				+ "doppio click con il pulsante <br>sinistro o rotellina per <br>fare lo zoom.<br><br>"
				+ "<u><b>Legenda:</b></u><br>"
				+ "<font color=\"red\">Coda</font><br>"
				+"<font color=\"blue\">Velocita' lenta</font><br>"
				+"<font color=\"black\">Traffico elevato</font><br>"
				+"<font color=\"green\">Traffico nella norma</font><br><br>"
				+ "Il pulsante <i>Pulire la mappa</i><br>"
				+ "elimina tutti i dati<br>"
				+ "visualizzati sulla mappa<br>"
				+ "e ne aggiunge di nuovi<br>"
				+ "quando arrivano delle<br>"
				+ "notifiche dal sistema."
				+ "</html>";




		JLabel helpLabel = new JLabel(informazioni);

		helpPanel.add(helpLabel);



		JButton adattaZoom = new JButton("Adatta zoom per vedere tutti i marcatori");
		JButton logoutBtn = new JButton("Logout");


		adattaZoom.addActionListener(e -> map().setDisplayToFitMapMarkers());
		logoutBtn.addActionListener(e -> FunzionamentoSistemaCentrale.logout());


		JButton pulisciMappaBtn = new JButton("Pulire la mappa");
		pulisciMappaBtn.addActionListener(e -> pulisciMappa());



		final JCheckBox showMapMarker = new JCheckBox("Marcatori");
		showMapMarker.setSelected(map().getMapMarkersVisible());
		showMapMarker.addActionListener(e -> map().setMapMarkerVisible(showMapMarker.isSelected()));
		panelBottom.add(showMapMarker);
		///

		///

		///
		final JCheckBox showTileGrid = new JCheckBox("Griglia");
		showTileGrid.setSelected(map().isTileGridVisible());
		showTileGrid.addActionListener(e -> map().setTileGridVisible(showTileGrid.isSelected()));
		panelBottom.add(showTileGrid);
		final JCheckBox showZoomControls = new JCheckBox("Controlli dello zoom");
		showZoomControls.setSelected(map().getZoomControlsVisible());
		showZoomControls.addActionListener(e -> map().setZoomControlsVisible(showZoomControls.isSelected()));
		panelBottom.add(showZoomControls);


		panelBottom.add(adattaZoom);
		panelBottom.add(pulisciMappaBtn);
		panelBottom.add(logoutBtn);

		panelTop.add(zoomLabel);
		panelTop.add(zoomValue);
		panelTop.add(mperpLabelName);
		panelTop.add(mperpLabelValue);

		add(treeMap, BorderLayout.CENTER);




		map().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					map().getAttribution().handleAttribution(e.getPoint(), true);
				}
			}
		});

		map().addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				boolean cursorHand = map().getAttribution().handleAttributionCursor(p);
				if (cursorHand) {
					map().setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FunzionamentoSistemaCentrale.logout();

			}
		});
	}

	private JMapViewer map() {
		return treeMap.getViewer();
	}

	private static Coordinate c(double lat, double lon) {
		return new Coordinate(lat, lon);
	}

	/**
	 * @param args Main program arguments
	 */
	public static void main(String[] args) {
		new MappaGrafica().setVisible(true);

	}

	private void updateZoomParameters() {
		if (mperpLabelValue != null)
			mperpLabelValue.setText(String.format("%.5g%n", map().getMeterPerPixel()));
		if (zoomValue != null)
			zoomValue.setText(String.format("%s", map().getZoom()));
	}

	@Override
	public void processCommand(JMVCommandEvent command) {
		if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
				command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
			updateZoomParameters();
		}
	}
}
