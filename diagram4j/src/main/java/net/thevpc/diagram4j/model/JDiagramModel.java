package net.thevpc.diagram4j.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class JDiagramModel implements Serializable {

    private double zoom = 1;
    private GridMagnetMode gridMagnetMode = GridMagnetMode.BOUNDS;
    private int zoomPointX;
    private int zoomPointY;
    private boolean gridVisible = true;
    private int gridX = 20;
    private int gridY = 20;
    private double distanceError = 5;
    private boolean editable = true;
    private String graphTitle = "";
    private String hoverElementUuid;
    private String hoverPortUuid;
    private LinkedHashMap<String, JDiagramElement> elements = new LinkedHashMap<>();
    private LinkedHashMap<String, JDiagramPort> ports = new LinkedHashMap<>();
    private LinkedHashSet<String> selectedElements = new LinkedHashSet<>();
    private JDiagramConfig config = new JDiagramConfig();

    public JDiagramModel() {
    }

    public String getHoverPortUuid() {
        return hoverPortUuid;
    }

    public void setHoverPortUuid(String hoverPortUuid) {
        this.hoverPortUuid = hoverPortUuid;
    }

    
    public String getHoverElementUuid() {
        return hoverElementUuid;
    }

    public void setHoverElementUuid(String hoverElementUuid) {
        this.hoverElementUuid = hoverElementUuid;
    }

    public LinkedHashMap<String, JDiagramPort> getPorts() {
        return ports;
    }

    public void setPorts(LinkedHashMap<String, JDiagramPort> ports) {
        this.ports = ports;
    }

    public double getDistanceError() {
        return distanceError;
    }

    public void setDistanceError(double distanceError) {
        this.distanceError = distanceError;
    }

    public LinkedHashSet<String> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(LinkedHashSet<String> selectedElements) {
        this.selectedElements = selectedElements;
    }

    public boolean isGridVisible() {
        return gridVisible;
    }

    public void setGridVisible(boolean gridVisible) {
        this.gridVisible = gridVisible;
    }

    public String getGraphTitle() {
        return graphTitle;
    }

    public void setGraphTitle(String graphTitle) {
        if (graphTitle == null) {
            this.graphTitle = "";
        } else {
            this.graphTitle = graphTitle;
        }
    }

    public LinkedHashMap<String, JDiagramElement> getElements() {
        return elements;
    }

    public void setElements(LinkedHashMap<String, JDiagramElement> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return graphTitle + ": " + elements.size();
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public int getZoomPointX() {
        return zoomPointX;
    }

    public void setZoomPointX(int zoomPointX) {
        this.zoomPointX = zoomPointX;
    }

    public int getZoomPointY() {
        return zoomPointY;
    }

    public void setZoomPointY(int zoomPointY) {
        this.zoomPointY = zoomPointY;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public JDiagramConfig getConfig() {
        return config;
    }

    public void setConfig(JDiagramConfig config) {
        this.config = config;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public GridMagnetMode getGridMagnetMode() {
        return gridMagnetMode;
    }

    public void setGridMagnetMode(GridMagnetMode gridMagnetMode) {
        this.gridMagnetMode = gridMagnetMode;
    }

}
