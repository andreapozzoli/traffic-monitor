// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.TileLoader;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

/**
 * Demonstrates the usage of {@link JMapViewer}
 *
 * @author Jan Peter Stotz
 *
 */
public class Demo extends JFrame implements JMapViewerEventListener {

    private static final long serialVersionUID = 1L;

    private final JMapViewerTree treeMap;

    private final JLabel zoomLabel;
    private final JLabel zoomValue;

    private final JLabel mperpLabelName;
    private final JLabel mperpLabelValue;

    /**
     * Constructs the {@code Demo}.
     */
    public Demo() {
        super("Mappa del traffico");
        setSize(400, 400);

        treeMap = new JMapViewerTree("Zone");

        // Listen to the map viewer for user operations so components will
        // receive events and update
        map().addJMVListener(this);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel helpPanel = new JPanel();

        mperpLabelName = new JLabel("Metri/Pixel: ");
        mperpLabelValue = new JLabel(String.format("%s", map().getMeterPerPixel()));

        zoomLabel = new JLabel("Zoom: ");
        zoomValue = new JLabel(String.format("%s", map().getZoom()));

        add(panel, BorderLayout.NORTH);
        add(helpPanel, BorderLayout.SOUTH);
        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(panelBottom, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Usare il pulsante destro del mouse per muoversi,\n "
                + "doppio click con il pulsante sinistro o rotellina per fare lo zoom.");
        helpPanel.add(helpLabel);
        JButton button = new JButton("Adatta zoom per vedere tutti i marker");
        button.addActionListener(e -> map().setDisplayToFitMapMarkers());
        JComboBox<TileSource> tileSourceSelector = new JComboBox<>(new TileSource[] {
                new OsmTileSource.Mapnik(),
                new OsmTileSource.CycleMap(),
                new BingAerialTileSource(),
        });
        tileSourceSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                map().setTileSource((TileSource) e.getItem());
            }
        });
        JComboBox<TileLoader> tileLoaderSelector;
        tileLoaderSelector = new JComboBox<>(new TileLoader[] {new OsmTileLoader(map())});
        tileLoaderSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                map().setTileLoader((TileLoader) e.getItem());
            }
        });
        map().setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());
        panelTop.add(tileSourceSelector);
        panelTop.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Marcatori di mappa visibili");
        showMapMarker.setSelected(map().getMapMarkersVisible());
        showMapMarker.addActionListener(e -> map().setMapMarkerVisible(showMapMarker.isSelected()));
        panelBottom.add(showMapMarker);
        ///
        final JCheckBox showTreeLayers = new JCheckBox("Tre layer visibili");
        showTreeLayers.addActionListener(e -> treeMap.setTreeVisible(showTreeLayers.isSelected()));
        panelBottom.add(showTreeLayers);
        ///
        final JCheckBox showToolTip = new JCheckBox("ToolTip visibile");
        showToolTip.addActionListener(e -> map().setToolTipText(null));
        panelBottom.add(showToolTip);
        ///
        final JCheckBox showTileGrid = new JCheckBox("Griglia visibile");
        showTileGrid.setSelected(map().isTileGridVisible());
        showTileGrid.addActionListener(e -> map().setTileGridVisible(showTileGrid.isSelected()));
        panelBottom.add(showTileGrid);
        final JCheckBox showZoomControls = new JCheckBox("Mostrare i controlli dello zoom");
        showZoomControls.setSelected(map().getZoomControlsVisible());
        showZoomControls.addActionListener(e -> map().setZoomControlsVisible(showZoomControls.isSelected()));
        panelBottom.add(showZoomControls);
        final JCheckBox scrollWrapEnabled = new JCheckBox("Abilitazione scrollwrap");
        scrollWrapEnabled.addActionListener(e -> map().setScrollWrapEnabled(scrollWrapEnabled.isSelected()));
        panelBottom.add(scrollWrapEnabled);
        panelBottom.add(button);

        panelTop.add(zoomLabel);
        panelTop.add(zoomValue);
        panelTop.add(mperpLabelName);
        panelTop.add(mperpLabelValue);

        add(treeMap, BorderLayout.CENTER);

        
        LayerGroup italyGroup = new LayerGroup("Italy");
        Layer lombardiaLayer = italyGroup.addLayer("Lombardia");
        
        Coordinate cooCS = new Coordinate(45.806620, 9.088631);
        
        MapMarkerDot automobile = new MapMarkerDot(lombardiaLayer, "Automobile", 45.806457, 9.088859);
        Font trb = new Font("Helvetica", Font.ITALIC, 13);
        
        Style styleCS = new Style(Color.BLACK, Color.RED, null, trb);
        
        MapMarkerDot CS = new MapMarkerDot(lombardiaLayer, "Centralina", cooCS, styleCS);
        
        map().addMapMarker(automobile);
        map().addMapMarker(CS);


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
                if (showToolTip.isSelected()) map().setToolTipText(map().getPosition(p).toString());
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
        new Demo().setVisible(true);
    }

    private void updateZoomParameters() {
        if (mperpLabelValue != null)
            mperpLabelValue.setText(String.format("%s", map().getMeterPerPixel()));
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
