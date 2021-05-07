package net.thevpc.diagram4j.model;

import net.thevpc.diagram4j.util.GeomUtils;

public class JDiagramEdge extends AbstractJDiagramElement {

    private String startArrowType;
    private String endArrowType;
    private String startPort;
    private String endPort;

    public JDiagramEdge() {
    }

    public String getStartPort() {
        return startPort;
    }

    public void setStartPort(String startPort) {
        this.startPort = startPort;
    }

    public String getEndPort() {
        return endPort;
    }

    public void setEndPort(String endPort) {
        this.endPort = endPort;
    }

    public String getStartArrowType() {
        return startArrowType;
    }

    public void setStartArrowType(String startArrowType) {
        this.startArrowType = startArrowType;
    }

    public String getEndArrowType() {
        return endArrowType;
    }

    public void setEndArrowType(String endArrowType) {
        this.endArrowType = endArrowType;
    }

    @Override
    public void configure(JDiagramConfig config) {
        super.configure(config);
        setStartArrowType(config.getStartArrowType());
        setEndArrowType(config.getEndArrowType());
    }

    public void build(JDiagram diagram) {
        super.build(diagram);
//        JDiagramPort[] pp = getPorts(diagram);
//        for (JDiagramPort connector : pp) {
//            connector.setEdgeUuid(uuid);
//        }
    }

    public boolean contains(int mx, int my, JDiagram diagram) {
        JDiagramPort p1 = getPort(0, diagram);
        JDiagramPort p2 = getPort(1, diagram);
        if (mx < Math.min(p1.getX(), p2.getX())
                || mx > Math.max(p1.getX(), p2.getX())
                || my < Math.min(p1.getY(), p2.getY())
                || my > Math.max(p1.getY(), p2.getY())) {
            return false;
        }

        int A = p2.getY() - p1.getY();
        int B = p2.getX() - p1.getX();

        double distance = Math.abs(A * mx - B * my + p2.getX() * p1.getY() - p2.getY() * p1.getX()) / Math.sqrt(A * A + B * B);
        return distance <= 5;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        JDiagramPort p1 = getPort(0, diagram);
        JDiagramPort p2 = getPort(1, diagram);
        return new JDiagramBounds(p1.getX(), p1.getY(),
                p2.getX(), p2.getY()
        );
    }

    public void moveBy(int dx, int dy, JDiagram diagram) {
        JDiagramPort p1 = getPort(0, diagram);
        JDiagramPort p2 = getPort(1, diagram);
        if (p1 != null) {
            JDiagramShape n = diagram.getShape(p1.getShapeUuid());
            if (n != null) {
                n.moveBy(dx, dy, diagram);
            }
        }
        if (p2 != null) {
            JDiagramShape n = diagram.getShape(p2.getShapeUuid());
            if (n != null) {
                n.moveBy(dx, dy, diagram);
            }
        }
    }

    @Override
    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram) {
        switch (port.getIndex()) {
            case 0: {
                JDiagramPort p = getPort(0, diagram);
                p.setX(p.getX() + dx);
                p.setY(p.getY() + dy);
                break;
            }
            case 1: {
                JDiagramPort p = getPort(1, diagram);
                p.setX(p.getX() + dx);
                p.setY(p.getY() + dy);
                break;
            }

        }
    }

    @Override
    public String toString() {
        return "[" + getClass().getName() + "]: (" + getPorts()[0] + ") ===> (" + getPorts()[1] + ") ";
    }

    @Override
    public double distanceTo(int x, int y, JDiagram diagram) {
        JDiagramPort p1 = getPort(0, diagram);
        JDiagramPort p2 = getPort(1, diagram);
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        return GeomUtils.distance(x, y, x1, y1, x2, y2);
    }

    public double length(JDiagram diagram) {
        JDiagramPort p1 = getPort(0, diagram);
        JDiagramPort p2 = getPort(1, diagram);
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public boolean isEmpty(JDiagram diagram) {
        return ((int) length(diagram)) == 0;
    }
}
