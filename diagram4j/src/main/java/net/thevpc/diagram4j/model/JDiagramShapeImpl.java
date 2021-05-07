package net.thevpc.diagram4j.model;

public abstract class JDiagramShapeImpl extends AbstractJDiagramElement implements JDiagramShape, Cloneable {

    private String fillColor = null;
    private GridMagnetMode mode = GridMagnetMode.INHERITED;

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String color) {
        System.out.println("update shape : " + getUuid() + " :: " + color);
        this.fillColor = color;
    }

    public GridMagnetMode getGridMagnetMode() {
        return mode;
    }

    public void setGridMagnetMode(GridMagnetMode mode) {
        this.mode = mode == null ? GridMagnetMode.INHERITED : mode;
    }

    @Override
    public void configure(JDiagramConfig config) {
        super.configure(config);
        this.setFillColor(config.getFillColor());
    }

//    @Override
//    public String toString() {
//        return "[" + getClass().getSimpleName()+ "]: (" + Integer.toString(x) + ", " + Integer.toString(y) + ") ";
//
//    }
    @Override
    public JDiagramPort createPort(int x, int y, JDiagram diagram) {
        JDiagramPort[] _ports = getPorts(diagram);
        double d0 = Double.NaN;
        int bestIndex = -1;
        JDiagramPort bestPort = null;
        for (int i = 0; i < _ports.length; i++) {
            JDiagramPort _port = _ports[i];
            double d = _port.distanceTo(x, y, diagram);
            if (Double.isNaN(d0) || d < d0) {
                bestIndex = i;
                bestPort = _port;
            }
        }
        if (bestPort == null) {
            if (_ports.length > 0) {
                bestPort = _ports[0];
            } else {
                JDiagramPort p2 = diagram.addPort();
                p2.setIndex(_ports.length);
                p2.setShapeUuid(getUuid());
                JDiagramBounds b = getBounds(diagram);
                p2.setXY(b.getCenterX(), b.getCenterY());
                ports.add(p2.getUuid());
                diagram.fireGeometryAdded(diagram, p2);
                return p2;
            }
        }
        if (bestPort.getEdgeUuid() == null) {
            return bestPort;
        }
        JDiagramPort p2 = diagram.addPort();
        p2.setIndex(_ports.length);
        p2.setShapeUuid(getUuid());
        p2.setXY(bestPort.getX(), bestPort.getY());
        ports.add(p2.getUuid());
        diagram.fireGeometryAdded(diagram, p2);
        return p2;
    }

}
