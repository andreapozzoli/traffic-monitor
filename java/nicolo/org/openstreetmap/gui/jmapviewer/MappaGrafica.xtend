// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Cursor
import java.awt.Point
import java.awt.Font
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.ArrayList
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon
import org.openstreetmap.gui.jmapviewer.interfaces.TileLoader
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource
import gestionetraffico.DatoGenerico

/** 
 * Demonstrates the usage of {@link JMapViewer}
 * @author Jan Peter Stotz
 */
class MappaGrafica extends JFrame implements JMapViewerEventListener {
	static final long serialVersionUID = 1L
	final JMapViewerTree treeMap
	final JLabel zoomLabel
	final JLabel zoomValue
	final JLabel mperpLabelName
	final JLabel mperpLabelValue

	def String creaEtichetta(DatoGenerico dato) {
		if (dato.getTipo().substring(0, 1).equals("M")) {
			return '''«dato.getData()» «dato.getOra()»'''.toString
		} else {
			return '''«dato.getData()» «dato.getOra()» «dato.getTipo().substring(1, dato.getTipo().indexOf(Character.valueOf(' ').charValue))»km/h'''.
				toString
		}
	}

	def void aggiornaMappa(ArrayList<DatoGenerico> listaDati) {
		pulisciMappa()
		for (DatoGenerico dato : listaDati) {
			if (dato.getPosizione() !== null) {
				if (dato.getTipo().endsWith("Coda")) {
					aggiungiVuota(creaEtichetta(dato), dato.getPosizione().getLatitudine(),
						dato.getPosizione().getLongitudine())
				} else if (dato.getTipo().endsWith("Traffico elevato")) {
					aggiungiTraffico(creaEtichetta(dato), dato.getPosizione().getLatitudine(),
						dato.getPosizione().getLongitudine())
				} else if (dato.getTipo().endsWith("Velocità lenta")) {
					aggiungiVelocitaLenta(creaEtichetta(dato), dato.getPosizione().getLatitudine(),
						dato.getPosizione().getLongitudine())
				} else {
					aggiungiVuota(creaEtichetta(dato), dato.getPosizione().getLatitudine(),
						dato.getPosizione().getLongitudine())
				}
			}
		}
	}

	def void pulisciMappa() {
		map().removeAllMapMarkers()
	}

	def MapMarkerDot aggiungiVuota(String etichetta, double lat, double lon) {
		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color::GREEN) // per le centraline che non hanno ancora rilevato nulla di particolare
	}

	def MapMarkerDot aggiungiCoda(String etichetta, double lat, double lon) {
		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color::RED)
	}

	def MapMarkerDot aggiungiVelocitaLenta(String etichetta, double lat, double lon) {
		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color::BLUE)
	}

	def MapMarkerDot aggiungiTraffico(String etichetta, double lat, double lon) {
		return aggiungiMarcatoreGenerico(etichetta, lat, lon, Color::BLACK)
	}

	def MapMarkerDot aggiungiMarcatoreGenerico(String etichetta, double lat, double lon, Color colore) {
		var Coordinate posizione = new Coordinate(lat, lon)
		var Font font = new Font("Helvetica", Font::PLAIN, 13)
		var Style stile = new Style(Color::BLACK, colore, null, font)
		var MapMarkerDot marcatoreGenerico = new MapMarkerDot(null, etichetta, posizione, stile)
		map().addMapMarker(marcatoreGenerico)
		return marcatoreGenerico
	}

	def void rimuoviMarcatore(MapMarkerDot nome) {
		map().removeMapMarker(nome)
	}

	new() {
		super("Mappa del traffico")
		setSize(400, 400)
		treeMap = new JMapViewerTree("Zone")
		map().addJMVListener(this)
		setLayout(new BorderLayout())
		setDefaultCloseOperation(JFrame::EXIT_ON_CLOSE)
		setExtendedState(JFrame::MAXIMIZED_BOTH)
		var JPanel panel = new JPanel(new BorderLayout())
		var JPanel panelTop = new JPanel()
		var JPanel panelBottom = new JPanel()
		var JPanel helpPanel = new JPanel()
		var JPanel legenda = new JPanel()
		// legenda dei colori
		mperpLabelName = new JLabel("Metri/Pixel: ")
		mperpLabelValue = new JLabel(String::format("%s", map().getMeterPerPixel()))
		zoomLabel = new JLabel("Zoom: ")
		zoomValue = new JLabel(String::format("%s", map().getZoom()))
		add(panel, BorderLayout::NORTH)
		add(helpPanel, BorderLayout::EAST)
		panel.add(panelTop, BorderLayout::NORTH)
		panel.add(panelBottom, BorderLayout::SOUTH)
		var JLabel helpLabel = new JLabel(
			'''<html>Usare il pulsante destro<br>del mouse per muoversi,<br> doppio click con il pulsante <br>sinistro o rotellina per <br>fare lo zoom.<br><br><u><b>Legenda:</b></u><br><font color="red">Coda</font><br><font color="blue">Velocità lenta</font><br><font color="black">Traffico elevato</font><br><font color="green">Nessun dato</font><br><br></html>'''.
				toString)
		helpPanel.add(helpLabel)
		var JButton adattaZoom = new JButton("Adatta zoom per vedere tutti i marcatori")
		adattaZoom.addActionListener(null)
		var JButton pulisciMappaBtn = new JButton("Pulire la mappa")
		pulisciMappaBtn.addActionListener(null)
		var JComboBox<TileSource> tileSourceSelector = new JComboBox((#[new OsmTileSource.Mapnik()] as TileSource[]))
		// new OsmTileSource.CycleMap(),
		// new BingAerialTileSource(),
		tileSourceSelector.addItemListener(
			([ItemEvent e|map().setTileSource((e.getItem() as TileSource))] as ItemListener))
		var JComboBox<TileLoader> tileLoaderSelector
		tileLoaderSelector = new JComboBox((#[new OsmTileLoader(map())] as TileLoader[]))
		tileLoaderSelector.addItemListener(
			([ItemEvent e|map().setTileLoader((e.getItem() as TileLoader))] as ItemListener))
		map().setTileLoader((tileLoaderSelector.getSelectedItem() as TileLoader))
		panelTop.add(tileSourceSelector)
		panelTop.add(tileLoaderSelector)
		val JCheckBox showMapMarker = new JCheckBox("Marcatori")
		showMapMarker.setSelected(map().getMapMarkersVisible())
		showMapMarker.addActionListener(null)
		panelBottom.add(showMapMarker)
		// /
		// /
		// /
		val JCheckBox showTileGrid = new JCheckBox("Griglia")
		showTileGrid.setSelected(map().isTileGridVisible())
		showTileGrid.addActionListener(null)
		panelBottom.add(showTileGrid)
		val JCheckBox showZoomControls = new JCheckBox("Controlli dello zoom")
		showZoomControls.setSelected(map().getZoomControlsVisible())
		showZoomControls.addActionListener(null)
		panelBottom.add(showZoomControls)
		panelBottom.add(adattaZoom)
		panelBottom.add(pulisciMappaBtn)
		panelTop.add(zoomLabel)
		panelTop.add(zoomValue)
		panelTop.add(mperpLabelName)
		panelTop.add(mperpLabelValue)
		add(treeMap, BorderLayout::CENTER)
		map().addMouseListener(new MouseAdapter() {
			override void mouseClicked(MouseEvent e) {
				if (e.getButton() === MouseEvent::BUTTON1) {
					map().getAttribution().handleAttribution(e.getPoint(), true)
				}
			}
		})
		map().addMouseMotionListener(new MouseAdapter() {
			override void mouseMoved(MouseEvent e) {
				var Point p = e.getPoint()
				var boolean cursorHand = map().getAttribution().handleAttributionCursor(p)
				if (cursorHand) {
					map().setCursor(new Cursor(Cursor::HAND_CURSOR))
				} else {
					map().setCursor(new Cursor(Cursor::DEFAULT_CURSOR))
				}
			}
		})
	}

	def private JMapViewer map() {
		return treeMap.getViewer()
	}

	def private static Coordinate c(double lat, double lon) {
		return new Coordinate(lat, lon)
	}

	/** 
	 * @param args Main program arguments
	 */
	def static void main(String[] args) {
		new MappaGrafica().setVisible(true)
	}

	def private void updateZoomParameters() {
		if(mperpLabelValue !== null) mperpLabelValue.setText(String::format("%s", map().getMeterPerPixel()))
		if(zoomValue !== null) zoomValue.setText(String::format("%s", map().getZoom()))
	}

	override void processCommand(JMVCommandEvent command) {
		if (command.getCommand().equals(JMVCommandEvent::COMMAND::ZOOM) ||
			command.getCommand().equals(JMVCommandEvent::COMMAND::MOVE)) {
			updateZoomParameters()
		}
	}
}
