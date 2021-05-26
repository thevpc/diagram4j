/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import net.thevpc.diagram4j.model.shapes.SegmentShape;

/**
 *
 * @author vpc
 */
public class JDiagram {

    private JDiagramModel model;
    private List<JDiagramListener> changeListeners = new ArrayList<>();

    public JDiagram(JDiagramModel model) {
        this.model = model;
    }

    public JDiagram() {
    }

    public JDiagramModel getModel() {
        return model;
    }

    public void setModel(JDiagramModel model) {
        this.model = model;
        JDiagramModel old = model;
        if (model != old) {
            this.model = model;
            firePropertyChanged(this, null, "model", old, model);
        }
    }

    public int getGridX() {
        return model.getGridX();
    }

    public void setGridX(int gridX) {
        int old = model.getGridX();
        if (gridX != old) {
            model.setGridX(gridX);
            firePropertyChanged(this, null, "gridX", old, gridX);
        }
    }

    public int getGridY() {
        return model.getGridY();
    }

    public void setGridY(int gridY) {
        int old = model.getGridY();
        if (gridY != old) {
            model.setGridY(gridY);
            firePropertyChanged(this, null, "gridY", old, gridY);
        }
    }

    public JDiagramConfig getConfig() {
        return model.getConfig();
    }

    public void setConfig(JDiagramConfig config) {
        JDiagramConfig old = this.model.getConfig();
        if (config != old) {
            this.model.setConfig(config);
            firePropertyChanged(this, null, "config", old, config);
        }
    }

    public GridMagnetMode getConfigGridMagnetMode() {
        return getConfig().getGridMagnetMode();
    }

    public void setConfigGridMagnetMode(GridMagnetMode gridMagnetMode) {
        GridMagnetMode old = this.model.getConfig().getGridMagnetMode();
        if (gridMagnetMode != old) {
            this.model.getConfig().setGridMagnetMode(gridMagnetMode);
            firePropertyChanged(this, null, "config.gridMagnetMode", old, gridMagnetMode);
        }
    }

    public GridMagnetMode getGridMagnetMode() {
        GridMagnetMode a = model.getGridMagnetMode();
        return a == null ? GridMagnetMode.INHERITED : a;
    }

    public void setGridMagnetMode(GridMagnetMode gridMagnetMode) {
        GridMagnetMode old = getGridMagnetMode();
        gridMagnetMode = gridMagnetMode == null ? GridMagnetMode.INHERITED : gridMagnetMode;
        if (gridMagnetMode != old) {
            this.model.setGridMagnetMode(gridMagnetMode);
            firePropertyChanged(this, null, "gridMagnetMode", old, gridMagnetMode);
        }
    }

    public void setConfigFillColor(String fillColor) {
        String old = this.model.getConfig().getFillColor();
        if (!Objects.equals(fillColor, old)) {
            this.model.getConfig().setFillColor(fillColor);
            firePropertyChanged(this, null, "config.fillColor", old, fillColor);
        }
    }

    public void setConfigTextColor(String textColor) {
        String old = this.model.getConfig().getTextColor();
        if (!Objects.equals(textColor, old)) {
            this.model.getConfig().setTextColor(textColor);
            firePropertyChanged(this, null, "config.textColor", old, textColor);
        }
    }

    public void setConfigTextFont(String textFont) {
        String old = this.model.getConfig().getTextFont();
        if (!Objects.equals(textFont, old)) {
            this.model.getConfig().setTextFont(textFont);
            firePropertyChanged(this, null, "config.textFont", old, textFont);
        }
    }

    public void setConfigLineColor(String lineColor) {
        String old = this.model.getConfig().getLineColor();
        if (!Objects.equals(lineColor, old)) {
            this.model.getConfig().setLineColor(lineColor);
            firePropertyChanged(this, null, "config.lineColor", old, lineColor);
        }
    }

    public String getConfigFillColor() {
        return getConfig().getFillColor();
    }

    public String getConfigTextColor() {
        return getConfig().getTextColor();
    }

    public String getConfigTextFont() {
        return getConfig().getTextFont();
    }

    public String getConfigLineColor() {
        return getConfig().getLineColor();
    }

    public String getConfigLineStroke() {
        return getConfig().getLineStroke();
    }

    public void setConfigLineStroke(String lineStroke) {
        String old = this.model.getConfig().getLineStroke();
        if (!Objects.equals(lineStroke, old)) {
            this.model.getConfig().setLineColor(lineStroke);
            firePropertyChanged(this, null, "config.lineStroke", old, lineStroke);
        }
    }

    public String getConfigStartArrowType() {
        return getConfig().getStartArrowType();
    }

    public void setConfigStartArrowType(String startArrowType) {
        String old = this.model.getConfig().getStartArrowType();
        if (!Objects.equals(startArrowType, old)) {
            this.model.getConfig().setStartArrowType(startArrowType);
            firePropertyChanged(this, null, "config.startArrowType", old, startArrowType);
        }
    }

    public String getConfigEndArrowType() {
        return getConfig().getEndArrowType();
    }

    public void setConfigEndArrowType(String endArrowType) {
        String old = this.model.getConfig().getEndArrowType();
        if (!Objects.equals(endArrowType, old)) {
            this.model.getConfig().setEndArrowType(endArrowType);
            firePropertyChanged(this, null, "config.endArrowType", old, endArrowType);
        }
    }

    public double getDistanceError() {
        return model.getDistanceError();
    }

    public void setDistanceError(double distanceError) {
        double old = this.model.getDistanceError();
        if (distanceError != old) {
            this.model.setDistanceError(distanceError);
            firePropertyChanged(this, null, "distanceError", old, distanceError);
        }
    }

    public List<JDiagramShape> getSelectedShapes() {
        List<JDiagramShape> a = new ArrayList<>();
        for (String selectedElement : model.getSelectedElements()) {
            JDiagramElement e = getElement(selectedElement);
            if (e instanceof JDiagramShape) {
                a.add((JDiagramShape) e);
            }
        }
        return a;
    }

    public int getSelectionCount() {
        return model.getSelectedElements().size();
    }

    public List<JDiagramElement> getSelectedElements() {
        List<JDiagramElement> a = new ArrayList<>();
        for (String selectedElement : model.getSelectedElements()) {
            JDiagramElement e = getElement(selectedElement);
            if (e != null) {
                a.add(e);
            }
        }
        return a;
    }

    public boolean isGridVisible() {
        return model.isGridVisible();
    }

    public void setGridVisible(boolean gridVisible) {
        boolean old = this.model.isGridVisible();
        if (gridVisible != old) {
            this.model.setGridVisible(gridVisible);
            firePropertyChanged(this, null, "gridVisible", old, gridVisible);
        }
    }

    public String getGraphTitle() {
        return model.getGraphTitle();
    }

    public void setGraphTitle(String graphTitle) {
        graphTitle = graphTitle == null ? "" : graphTitle;
        String old = this.model.getGraphTitle();
        if (!Objects.equals(graphTitle, old)) {
            this.model.setGraphTitle(graphTitle);
            firePropertyChanged(this, null, "graphTitle", old, graphTitle);
        }
    }

    public List<JDiagramElement> getElements() {
        return new ArrayList<>(model.getElements().values());
    }

    public JDiagramElement getElement(String uuid) {
        return model.getElements().get(uuid);
    }

    public JDiagramShape getShape(String uuid) {
        return (JDiagramShape) model.getElements().get(uuid);
    }

    public JDiagramEdge getEdge(String uuid) {
        return (JDiagramEdge) getElement(uuid);
    }

    public List<JDiagramShape> getShapes() {
        return getElements().stream().filter(x -> (x instanceof JDiagramShape)).map(x -> (JDiagramShape) x).collect(Collectors.toList());
    }

    public List<JDiagramEdge> getEdges() {
        return getElements().stream().filter(x -> (x instanceof JDiagramEdge)).map(x -> (JDiagramEdge) x).collect(Collectors.toList());
    }

    public void addElement(JDiagramElement n) {
        String s = n.getUuid();
        if (s != null) {
            if (model.getElements().containsKey(s)) {
                return;
            }
        } else {
            for (JDiagramElement elem : model.getElements().values()) {
                ((AbstractJDiagramElement) n).setUuid(elem.getUuid());
                if (elem.equals(n)) {
                    ((AbstractJDiagramElement) n).setUuid(null);
                    return;
                }
            }
            ((AbstractJDiagramElement) n).setUuid(null);
        }
        ((AbstractJDiagramElement) n).ensureUuid();
        model.getElements().put(n.getUuid(), n);
        n.build(this);
        fireGeometryAdded(this, n);
        if (n instanceof JDiagramShape) {
            snapToGrid(GridMagnetMode.INHERITED, (JDiagramShape) n);
        }
    }

    public void align(GridAlignMode mode, boolean selected) {
        List<JDiagramShape> all = selected ? getSelectedShapes() : getShapes();
        if (all.size() < 2) {
            return;
        }
        boolean firstIsRef = true;
        switch (mode) {
            case TOP: {
                int y = firstIsRef ? all.get(0).getBounds(this).getMinY()
                        : all.stream().map(x -> x.getBounds(this).getMinY()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    moveTo(e, (int) e.getBounds(this).getMinX(), y);
                }
                return;
            }
            case BOTTOM: {
                int y2 = firstIsRef ? all.get(0).getBounds(this).getMaxY()
                        : all.stream().map(x -> x.getBounds(this).getMaxY()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    moveTo(e, b.getMinX(), y2 - b.getHeight());
                }
                return;
            }
            case LEFT: {
                int x = firstIsRef ? all.get(0).getBounds(this).getMinX()
                        : all.stream().map(e -> e.getBounds(this).getMinX()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    moveTo(e, x, b.getMinY());
                }
                return;
            }
            case RIGHT: {
                int x = firstIsRef ? all.get(0).getBounds(this).getMaxX()
                        : all.stream().map(e -> e.getBounds(this).getMaxX()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    moveTo(e, x - b.getWidth(), b.getMinY());
                }
                return;
            }
            case CENTER_HORIZONTAL: {
                int y = firstIsRef ? all.get(0).getBounds(this).getCenterY()
                        : all.stream().map(e -> e.getBounds(this).getCenterY()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    moveTo(e, b.getMinX(), y - b.getHeight() / 2);
                }
                return;
            }
            case CENTER_VERTICAL: {
                int x = firstIsRef ? all.get(0).getBounds(this).getCenterX()
                        : all.stream().map(e -> e.getBounds(this).getCenterX()).min((a, b) -> Integer.compare(a, b)).orElse(0);
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    moveTo(e, x - b.getWidth() / 2, b.getMinY());
                }
                return;
            }
        }
    }

    public void distribute(GridDistributeMode mode, boolean selected) {
        List<JDiagramShape> all = selected ? getSelectedShapes() : getShapes();
        if (all.size() <= 2) {
            return;
        }
        switch (mode) {
            case VERTICAL: {
                all.sort((a, b) -> Integer.compare(a.getBounds(this).getMinY(), b.getBounds(this).getMinY()));
                int minY = all.get(0).getBounds(this).getMinY();
                int maxY = all.get(0).getBounds(this).getMinY();
                int len = 0;
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    int y = b.getMinY();
                    if (y < minY) {
                        minY = y;
                    }
                    if (y < maxY) {
                        maxY = y;
                    }
                    len += b.getHeight();
                }
                int space = maxY - len;
                if (space >= 0) {
                    int z = space / (all.size() - 1);
                    int next = minY;
                    for (JDiagramShape e : all) {
                        JDiagramBounds b = e.getBounds(this);
                        moveTo(e, b.getMinX(), next);
                        next += b.getHeight() + z;
                    }
                }
                return;
            }
            case HORIZONTAL: {
                all.sort((a, b) -> Integer.compare(a.getBounds(this).getMinX(), b.getBounds(this).getMinX()));
                int minX = all.get(0).getBounds(this).getMinX();
                int maxX = all.get(0).getBounds(this).getMinX();
                int len = 0;
                for (JDiagramShape e : all) {
                    JDiagramBounds b = e.getBounds(this);
                    int x = b.getMinX();
                    if (x < minX) {
                        minX = x;
                    }
                    if (x < maxX) {
                        maxX = x;
                    }
                    len += b.getWidth();
                }
                int space = maxX - len;
                if (space >= 0) {
                    int z = space / (all.size() - 1);
                    int next = minX;
                    for (JDiagramShape e : all) {
                        JDiagramBounds b = e.getBounds(this);
                        moveTo(e, next, b.getMinY());
                        next += b.getWidth() + z;
                    }
                }
                return;
            }
        }
    }

    public static int snapToGrid(int x, int gx, boolean floor) {
        if (gx > 1) {
            int x2 = (int) (floor ? Math.floorDiv(x, gx) : Math.ceil(x * 1.0 / gx))
                    * gx;
            return x2;
        }
        return x;
    }

    public int snapToGridMinX(int x) {
        return snapToGrid(x, getGridX(), true);
    }

    public int snapToGridMaxX(int x) {
        return snapToGrid(x, getGridX(), false);
    }

    public int snapToGridMinY(int y) {
        return snapToGrid(y, getGridY(), true);
    }

    public int snapToGridMaxY(int y) {
        return snapToGrid(y, getGridY(), false);
    }

    public JDiagramBounds snapToGrid(JDiagramBounds b) {
        int x01 = b.getMinX();
        int y01 = b.getMinY();
        int x02 = b.getMaxX();
        int y02 = b.getMaxY();
        int w = snapToGridMaxX(x02 - x01);
        int h = snapToGridMaxY(y02 - y01);

        int x1 = snapToGridMinX(x01);
        int y1 = snapToGridMinY(y01);
        int x2 = x1 + w;
        int y2 = y1 + h;
        return new JDiagramBounds(x1, y1, x2, y2);
    }

    public boolean snapToGrid(GridMagnetMode mode, boolean selected) {
        boolean someChanges = false;
        List<JDiagramShape> selectedShapes = selected ? getSelectedShapes() : getShapes();
        for (JDiagramShape shape : selectedShapes) {
            someChanges |= snapToGrid(mode, shape);
        }
        return someChanges;
    }

    public boolean snapToGrid(GridMagnetMode mode, JDiagramShape shape) {
        if (mode == null || mode == GridMagnetMode.INHERITED) {
            mode = shape.getGridMagnetMode();
            if (mode == null || mode == GridMagnetMode.INHERITED) {
                mode = getGridMagnetMode();
            }
            if (mode == null || mode == GridMagnetMode.INHERITED) {
                mode = getConfig().getGridMagnetMode();
            }
        }
        if (mode == null || mode == GridMagnetMode.INHERITED) {
            mode = GridMagnetMode.NONE;
        }
        switch (mode) {
            case POSITION: {
                JDiagramBounds b = shape.getBounds(this);
                int x = snapToGridMinX(b.getMinX());
                int y = snapToGridMinY(b.getMinY());
                moveTo(shape, x, y);
                return true;
            }
            case BOUNDS: {
                if (shape instanceof SegmentShape || shape instanceof JDiagramEdge) {
                    JDiagramBounds b = shape.getBounds(this);
                    int x01 = b.getMinX();
                    int y01 = b.getMinY();
                    int x02 = b.getMaxX();
                    int y02 = b.getMaxY();

                    int x1 = snapToGridMinX(x01);
                    int y1 = snapToGridMinY(y01);
                    int x2 = snapToGridMinX(x02);
                    int y2 = snapToGridMinX(y02);
                    JDiagramBounds b2 = new JDiagramBounds(x1, y1, x2, y2);
                    setBounds(shape, b2);
                    return true;
                }
                JDiagramBounds b = shape.getBounds(this);
                JDiagramBounds b2 = snapToGrid(b);
                setBounds(shape, b2);
                return true;
            }
        }
        return false;
    }

    public JDiagramPort findPort(String portUuid) {
        return model.getPorts().get(portUuid);
    }

    public JDiagramPort getPort(String portUuid) {
        JDiagramPort p = findPort(portUuid);
        if (p == null) {
            throw new IllegalArgumentException("not found");
        }
        return p;
    }

    public JDiagramPort addPort() {
        JDiagramPort p = new JDiagramPort();
        p.setUuid(UUID.randomUUID().toString());
        getModel().getPorts().put(p.getUuid(), p);
        fireGeometryAdded(this, p);
        return p;
    }

    public List<JDiagramElement> selectNewCopy(List<JDiagramElement> selectedElements) {
        List<JDiagramElement> newCopy = new ArrayList<>();
        Set<String> selectedElements2UUIDs = new LinkedHashSet<>();
        for (JDiagramElement s : selectedElements) {
            JDiagramElement c = s.copy(this);
            addElement(c);
            newCopy.add(c);
            selectedElements2UUIDs.add(c.getUuid());
        }
        setSelected(selectedElements2UUIDs, JDiagramSelectionMode.REPLACE);
        return newCopy;
    }

    public static class GraphElementAndPort {

        private JDiagramElement elem;
        private JDiagramPort port;

        public GraphElementAndPort(JDiagramElement elem, JDiagramPort port) {
            this.elem = elem;
            this.port = port;
        }

        public JDiagramElement getElem() {
            return elem;
        }

        public JDiagramPort getPort() {
            return port;
        }

    }

    public GraphElementAndPort findElementAndPort(int x, int y) {
        for (String se : model.getSelectedElements()) {
            JDiagramElement ee = getElement(se);
            if (ee != null) {
                for (JDiagramPort gp : ee.getPorts(this)) {
                    if (gp.distanceTo(x, y, this) <= getDistanceError()) {
                        return new GraphElementAndPort(ee, gp);
                    }
                }
            }
        }
        JDiagramElement ee = findElement(x, y);
        if (ee != null) {
            return new GraphElementAndPort(ee, null);
        }
        return null;
    }

    public JDiagramElement findElement(int mx, int my) {
        //search backward!
        JDiagramElement[] all = getElements().toArray(new JDiagramElement[0]);
        for (int i = all.length - 1; i >= 0; i--) {
            JDiagramElement elem = all[i];
            if (elem.distanceTo(mx, my, this) <= getDistanceError()) {
                return elem;
            }
        }
        return null;
    }

    private void removeElementEdges(JDiagramElement element) {
        JDiagramPort[] ports = element.getPorts(this);
        for (JDiagramPort port : ports) {
            String euuid = port.getEdgeUuid();
            if (euuid != null) {
                port.setEdgeUuid(null);
                JDiagramElement edge = getElement(euuid);
                if (edge != null) {
                    remove(edge);
                }
            }
        }
    }

    public void removeEdge(JDiagramEdge edge) {
        setSelected(Collections.singleton(edge.getUuid()), JDiagramSelectionMode.EXCLUDE);
        JDiagramEdge old = (JDiagramEdge) model.getElements().remove(edge.getUuid());
        if (old != null) {
            JDiagramPort[] ports = old.getPorts(this);
            for (JDiagramPort port : ports) {
                port.setEdgeUuid(null);
                ((AbstractJDiagramElement) edge).ports.remove(port.getUuid());
                if (!port.isReadOnly()) {
                    removePort(port);
                }
            }
            fireGeometryRemoved(this, old);
        }
    }

    public void removePort(JDiagramPort port) {
        if (port.getEdgeUuid() == null && !port.isReadOnly()) {
            JDiagramShape s = getShape(port.getShapeUuid());
            if (s != null) {
                JDiagramBounds before = s.getBounds(this);
                ((AbstractJDiagramElement) s).ports.remove(port.getUuid());
                ((AbstractJDiagramElement) s).build(this);
                getModel().getPorts().remove(s);
                fireGeometryRemoved(this, port);
                JDiagramBounds after = s.getBounds(this);
                if (!after.equals(before)) {
                    firePropertyChanged(this, s, "bounds", before, after);
                }
            }
        }
    }

    public void removeShape(JDiagramShape shape) {
        setSelected(Collections.singleton(shape.getUuid()), JDiagramSelectionMode.EXCLUDE);
        JDiagramShape old = (JDiagramShape) model.getElements().get(shape.getUuid());
        if (old != null) {
            JDiagramPort[] ports = old.getPorts(this);
            for (JDiagramPort port : ports) {
                if (port.getEdgeUuid() != null) {
                    removeEdge(getEdge(port.getUuid()));
                }
            }
            ports = old.getPorts(this);
            for (JDiagramPort port : ports) {
                if (!port.isReadOnly()) {
                    removePort(getPort(port.getUuid()));
                }
            }
            ports = old.getPorts(this);
            for (JDiagramPort port : ports) {
                ((AbstractJDiagramElement) shape).ports.remove(port.getUuid());
                model.getPorts().remove(port.getUuid());
            }
            model.getElements().remove(shape.getUuid());
            fireGeometryRemoved(this, old);
        }
    }

    public void remove(JDiagramGeometry element) {
        if (element instanceof JDiagramEdge) {
            removeEdge((JDiagramEdge) element);
        } else if (element instanceof JDiagramShape) {
            removeShape((JDiagramShape) element);
        } else if (element instanceof JDiagramPort) {
            removePort((JDiagramPort) element);
        }
    }

    public void remove(String uuid) {
        JDiagramElement old = model.getElements().remove(uuid);
        if (old != null) {
            if (old instanceof JDiagramEdge) {
                removeEdge((JDiagramEdge) old);
            } else if (old instanceof JDiagramShape) {
                removeShape((JDiagramShape) old);
            }
        }
        JDiagramPort pold = model.getPorts().remove(uuid);
        if (pold != null) {
            if (!pold.isReadOnly()) {
                removePort(pold);
            }
        }
    }

    public void moveDiagram(int dx, int dy) {
        for (JDiagramElement elem : getElements()) {
            moveBy(elem, dx, dy);
        }
    }

    @Override
    public String toString() {
        return model.getGraphTitle() + ": " + model.getElements().size();
    }

    public void clear() {
        int count;
        while ((count = model.getElements().size()) > 0) {
            JDiagramElement ee = (JDiagramElement) model.getElements().values().stream().findFirst().get();
            remove(ee);
            if (model.getElements().size() >= count) {
                throw new IllegalArgumentException("unable to remove " + ee);
            }
        }
        while ((count = model.getPorts().size()) > 0) {
            JDiagramPort ee = (JDiagramPort) model.getPorts().values().stream().findFirst().get();
            remove(ee);
            if (model.getPorts().size() >= count) {
                throw new IllegalArgumentException("unable to remove " + ee);
            }
        }
    }

    public void removeSelected() {
        for (String uuid : new HashSet<String>(model.getSelectedElements())) {
            JDiagramElement el = getElement(uuid);
            if (el != null) {
                remove(el);
            }
        }
    }

    public double getZoom() {
        return this.model.getZoom();
    }

    public void unzoom() {
        setZoom(1);
    }

    public void setZoom(double zoom) {
        double old = zoom;
        if (old != model.getZoom()) {
            this.model.setZoom(zoom);
            firePropertyChanged(this, null, "zoom", old, zoom);
        }
    }

    public int getZoomPointX() {
        return this.model.getZoomPointX();
    }

    public void setZoomPointX(int zoomPointX) {
        int old = zoomPointX;
        if (old != model.getZoomPointX()) {
            this.model.setZoomPointX(zoomPointX);
            firePropertyChanged(this, null, "zoomPointX", old, zoomPointX);
        }
    }

    public int getZoomPointY() {
        return this.model.getZoomPointY();
    }

    public void setZoomPointY(int zoomPointY) {
        int old = zoomPointY;
        if (old != model.getZoomPointY()) {
            this.model.setZoomPointY(zoomPointY);
            firePropertyChanged(this, null, "zoomPointY", old, zoomPointY);
        }
    }

    public boolean isSelected(String uuid) {
        return uuid != null
                && model.getSelectedElements().contains(uuid);
    }

    public boolean isHover(JDiagramPort e) {
        return e != null && Objects.equals(model.getHoverPortUuid(), e.getUuid());
    }

    public boolean isHover(JDiagramElement e) {
        return e != null && Objects.equals(model.getHoverElementUuid(), e.getUuid());
    }

    public boolean isHover(String uuid) {
        return uuid != null
                && (Objects.equals(model.getHoverElementUuid(), uuid)
                || Objects.equals(model.getHoverPortUuid(), uuid));
    }

    public boolean isSelected(JDiagramElement e) {
        return e != null && model.getSelectedElements().contains(e.getUuid());
    }

    public boolean isEditable() {
        return this.model.isEditable();
    }

    public void setEditable(boolean editable) {
        boolean old = this.model.isEditable();
        if (editable != old) {
            this.model.setEditable(editable);
            firePropertyChanged(this, null, "editable", old, editable);
        }
    }

//    public void diagramChanged() {
//        ChangeEvent ce = new ChangeEvent(this);
//        for (ChangeListener changeListener : changeListeners) {
//            changeListener.stateChanged(ce);
//        }
//    }
    public void fireGeometryAdded(JDiagram diagram, JDiagramGeometry element) {
        for (JDiagramListener li : changeListeners) {
            li.geometryAdded(diagram, element);
        }
    }

    public void fireGeometryRemoved(JDiagram diagram, JDiagramGeometry element) {
        for (JDiagramListener li : changeListeners) {
            li.geometryRemoved(diagram, element);
        }
    }

    public void fireSelectionChanged(JDiagram diagram, Set<String> before, Set<String> after) {
        if (!before.equals(after)) {
            for (JDiagramListener li : changeListeners) {
                li.selectionChanged(diagram, before, after);
            }
        }
    }

    public void firePropertyChanged(JDiagram diagram, JDiagramGeometry geometry, String property, Object oldValue, Object newValue) {
        for (JDiagramListener li : changeListeners) {
            li.propertyChanged(diagram, geometry, property, oldValue, newValue);
        }
    }

    public void addDiagramChangeListener(JDiagramListener changeListener) {
        changeListeners.add(changeListener);
    }

    public void zoomOut() {
        double zoom = getZoom();
        zoom -= 0.1;
        if (zoom < 0.01) {
            zoom = 0.01;
        }
        setZoom(zoom);
    }

    public void zoomIn() {
        double zoom = getZoom();
        zoom += 0.1;
        if (zoom > 100) {
            zoom = 100;
        }
        setZoom(zoom);
    }

//    private boolean clearSelection0() {
//        LinkedHashSet<String> elems = model.getSelectedElements();
//        if (!elems.isEmpty()) {
//            elems.clear();
//            return true;
//        }
//        return false;
//    }
    public boolean clearSelection() {
        boolean e = !model.getSelectedElements().isEmpty();
        setSelected(Collections.EMPTY_SET, JDiagramSelectionMode.REPLACE);
        return e;
    }

    public boolean switchElementPositions(int i, int j) {
        List<JDiagramElement> elem = getElements();
        int size = elem.size();
        if (i != j) {
            if (i >= 0 && i <= size) {
                if (i >= 0 && i <= size) {
                    JDiagramElement a = elem.get(i);
                    JDiagramElement b = elem.get(j);
                    elem.set(i, b);
                    elem.set(j, a);
                    model.getElements().clear();
                    for (JDiagramElement ee : elem) {
                        model.getElements().put(ee.getUuid(), ee);
                    }
                    firePropertyChanged(this, a, "z", i, j);
                    firePropertyChanged(this, b, "z", j, i);
                    return true;
                }
            }
        }
        return false;
    }

    public int getElementPosition(String uuid) {
        List<JDiagramElement> elem = getElements();
        for (int i = 0; i < elem.size(); i++) {
            String s = elem.get(i).getUuid();
            if (uuid.equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public void moveToLayer(GridLayerMode mode, boolean selected) {
        moveToLayer(mode, selected ? getSelectedElements() : getElements());
    }

    public void moveToLayer(GridLayerMode mode, List<? extends JDiagramElement> list) {
        for (JDiagramElement selectedElement : list) {
            moveToLayer(mode, selectedElement.getUuid());
        }
    }

    public void moveToLayer(GridLayerMode mode, String uuid) {
        int p = getElementPosition(uuid);
        switch (mode) {
            case UP: {
                switchElementPositions(p, p + 1);
                break;
            }
            case DOWN: {
                switchElementPositions(p, p - 1);
                break;
            }
            case TOP: {
                switchElementPositions(p, model.getElements().size() - 1);
                break;
            }
            case BOTTOM: {
                switchElementPositions(p, 0);
                break;
            }
        }
    }

    public void setElementFillColor(JDiagramShape s, String fillColor) {
        String old = s.getFillColor();
        if (!Objects.equals(fillColor, old)) {
            s.setFillColor(fillColor);
            firePropertyChanged(this, s, "fillColor", old, fillColor);
        }
    }

    public void setElementLineColor(JDiagramElement s, String lineColor) {
        String old = s.getLineColor();
        if (!Objects.equals(lineColor, old)) {
            s.setLineColor(lineColor);
            firePropertyChanged(this, s, "lineColor", old, lineColor);
        }
    }

    public void setElementTextColor(JDiagramElement s, String textColor) {
        String old = s.getTextColor();
        if (!Objects.equals(textColor, old)) {
            s.setTextColor(textColor);
            firePropertyChanged(this, s, "textColor", old, textColor);
        }
    }

    public void setElementTextFont(JDiagramElement s, String textFont) {
        String old = s.getTextFont();
        if (!Objects.equals(textFont, old)) {
            s.setTextFont(textFont);
            firePropertyChanged(this, s, "textFont", old, textFont);
        }
    }

    public void setElementText(JDiagramElement s, String text) {
        String old = s.getText();
        if (!Objects.equals(text, old)) {
            s.setText(text);
            s.build(this);
            firePropertyChanged(this, s, "tont", old, text);
        }
    }

    public void setElemenLineStroke(JDiagramElement s, String lineStroke) {
        String old = s.getLineStroke();
        if (!Objects.equals(lineStroke, old)) {
            s.setLineStroke(lineStroke);
            firePropertyChanged(this, s, "lineStroke", old, lineStroke);
        }
    }

    public void setElemenStartArrowType(SegmentShape s, String startArrowType) {
        String old = s.getStartArrowType();
        if (!Objects.equals(startArrowType, old)) {
            s.setStartArrowType(startArrowType);
            firePropertyChanged(this, s, "startArrowType", old, startArrowType);
        }
    }

    public void setElemenStartArrowType(JDiagramEdge s, String startArrowType) {
        String old = s.getStartArrowType();
        if (!Objects.equals(startArrowType, old)) {
            s.setStartArrowType(startArrowType);
            firePropertyChanged(this, s, "startArrowType", old, startArrowType);
        }
    }

    public void setElemenEndArrowType(SegmentShape s, String endArrowType) {
        String old = s.getEndArrowType();
        if (!Objects.equals(endArrowType, old)) {
            s.setEndArrowType(endArrowType);
            firePropertyChanged(this, s, "endArrowType", old, endArrowType);
        }
    }

    public void setElemenEndArrowType(JDiagramEdge s, String endArrowType) {
        String old = s.getEndArrowType();
        if (!Objects.equals(endArrowType, old)) {
            s.setEndArrowType(endArrowType);
            firePropertyChanged(this, s, "endArrowType", old, endArrowType);
        }
    }

    public void setSelectionFillColor(String c) {
        for (JDiagramElement se : getSelectedElements()) {
            if (se instanceof JDiagramShape) {
                JDiagramShape s = (JDiagramShape) se;
                setElementFillColor(s, c);
            }
        }
    }

    public void setSelectionTextColor(String c) {
        for (JDiagramElement se : getSelectedElements()) {
            setElementTextColor(se, c);
        }
    }

    public void setSelectionTextFont(String c) {
        for (JDiagramElement se : getSelectedElements()) {
            setElementTextFont(se, c);
        }
    }

    public void setSelectionLineColor(String c) {
        for (JDiagramElement se : getSelectedElements()) {
            setElementLineColor(se, c);
        }
    }

    public void updateLineStroke(String c) {
        if (!Objects.equals(c, getConfig().getLineStroke())) {
            setConfigLineStroke(c);
        }
        for (JDiagramElement se : getSelectedElements()) {
            if (!Objects.equals(c, se.getLineStroke())) {
                setElemenLineStroke(se, c);
            }
        }
    }

    public void setSelectionStartArrowType(String c) {
        for (JDiagramElement se : getSelectedElements()) {
            if (se instanceof SegmentShape) {
                SegmentShape a = (SegmentShape) se;
                setElemenStartArrowType(a, c);
            } else if (se instanceof JDiagramEdge) {
                JDiagramEdge a = (JDiagramEdge) se;
                setElemenStartArrowType(a, c);
            }
        }
    }

    public void setSelectionEndArrowType(String c) {
        if (!Objects.equals(c, getConfig().getEndArrowType())) {
            setConfigEndArrowType(c);
        }
        for (JDiagramElement se : getSelectedElements()) {
            if (se instanceof SegmentShape) {
                SegmentShape a = (SegmentShape) se;
                setElemenEndArrowType(a, c);
            } else if (se instanceof JDiagramEdge) {
                JDiagramEdge a = (JDiagramEdge) se;
                setElemenEndArrowType(a, c);
            }
        }
    }

    /**
     *
     * @param uuidSelection
     * @param mode selection mode
     * @return true of some changes
     */
    public boolean setSelected(Set<String> uuidSelection, JDiagramSelectionMode mode) {
        LinkedHashSet<String> before = new LinkedHashSet<>(model.getSelectedElements());
        if (mode == null) {
            mode = JDiagramSelectionMode.REPLACE;
            //switch the set selection
        }
        switch (mode) {
            case APPEND: {
                Set<String> newSelection = new LinkedHashSet<>();
                LinkedHashSet<String> initial = model.getSelectedElements();
                newSelection.addAll(initial);
                newSelection.addAll(uuidSelection);
                if (newSelection.equals(initial)) {
                    return false;
                }
                model.getSelectedElements().clear();
                model.getSelectedElements().addAll(newSelection);
                fireSelectionChanged(this, before, model.getSelectedElements());
                return true;
            }
            case REPLACE: {
                Set<String> newSelection = new LinkedHashSet<>();
                LinkedHashSet<String> initial = model.getSelectedElements();
                newSelection.addAll(uuidSelection);
                if (newSelection.equals(initial)) {
                    return false;
                }
                model.getSelectedElements().clear();
                model.getSelectedElements().addAll(newSelection);
                fireSelectionChanged(this, before, model.getSelectedElements());
                return true;
            }
            case EXCLUDE: {
                Set<String> newSelection = new LinkedHashSet<>();
                LinkedHashSet<String> initial = model.getSelectedElements();
                newSelection.addAll(initial);
                newSelection.removeAll(uuidSelection);
                if (newSelection.equals(initial)) {
                    return false;
                }
                model.getSelectedElements().clear();
                model.getSelectedElements().addAll(newSelection);
                fireSelectionChanged(this, before, model.getSelectedElements());
                return true;
            }
            case TOGGLE: {
                Set<String> newSelection = new LinkedHashSet<>();
                LinkedHashSet<String> initial = model.getSelectedElements();
                newSelection.addAll(initial);
                for (String e : uuidSelection) {
                    if (newSelection.contains(e)) {
                        newSelection.remove(e);
                    } else {
                        newSelection.add(e);
                    }
                }
                if (newSelection.equals(initial)) {
                    return false;
                }
                model.getSelectedElements().clear();
                model.getSelectedElements().addAll(newSelection);
                fireSelectionChanged(this, before, model.getSelectedElements());
                return true;
            }
        }

        throw new IllegalArgumentException("unsupported selection mode");
    }

    public Set<String> getSelectionRectUuids(Point selectStart, Point selectEnd) {
        int x1 = selectStart.x;
        int y1 = selectStart.y;
        int x2 = selectEnd.x;
        int y2 = selectEnd.y;
        x1 = Math.min(x1, x2);
        y1 = Math.min(y1, y2);
        x2 = Math.max(x1, x2);
        y2 = Math.max(y1, y2);
        JDiagramBounds r2 = new JDiagramBounds(x1, y1, x2, y2);
        Set<String> uuidSelection = new LinkedHashSet<>();
        for (JDiagramElement element : getElements()) {
            if (r2.contains(element.getBounds(this))) {
                uuidSelection.add(element.getUuid());
            }
        }
        return uuidSelection;
    }

    public String getHoverElementUuid() {
        return model.getHoverElementUuid();
    }

    public void setHoverElementUuid(String hoverElementUuid) {
        String old = model.getHoverElementUuid();
        if (!Objects.equals(old, hoverElementUuid)) {
            this.model.setHoverElementUuid(hoverElementUuid);
            firePropertyChanged(this, null, "hoverElementUuid", old, hoverElementUuid);
        }
    }

    public String getHoverPortUuid() {
        return model.getHoverPortUuid();
    }

    public void setHoverPortUuid(String hoverPortUuid) {
        String old = model.getHoverPortUuid();
        if (!Objects.equals(old, hoverPortUuid)) {
            this.model.setHoverPortUuid(hoverPortUuid);
            firePropertyChanged(this, null, "hoverPortUuid", old, hoverPortUuid);
        }
    }

    public void moveTo(JDiagramElement e, int x, int y) {
        JDiagramBounds before = e.getBounds(this);
        e.moveTo(x, y, this);
        JDiagramBounds after = e.getBounds(this);
        if (!before.equals(after)) {
            firePropertyChanged(this, e, "bounds", before, after);
        }
    }

    public void moveBy(JDiagramElement e, int x, int y) {
        JDiagramBounds before = e.getBounds(this);
        e.moveBy(x, y, this);
        JDiagramBounds after = e.getBounds(this);
        if (!before.equals(after)) {
            firePropertyChanged(this, e, "bounds", before, after);
        }
    }

    public void setBounds(JDiagramShape e, JDiagramBounds rectangle) {
        JDiagramBounds before = e.getBounds(this);
        e.setBounds(rectangle);
        e.build(this);
        JDiagramBounds after = e.getBounds(this);
        if (!before.equals(after)) {
            firePropertyChanged(this, e, "bounds", before, after);
        }
    }

}
