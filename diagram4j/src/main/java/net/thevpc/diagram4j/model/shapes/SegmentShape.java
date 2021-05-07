package net.thevpc.diagram4j.model.shapes;

import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramConfig;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;
import net.thevpc.diagram4j.util.GeomUtils;

public class SegmentShape extends JDiagramShapeImpl implements Cloneable {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String startArrowType;
    private String endArrowType;

    public SegmentShape() {
    }

    public SegmentShape(int x1, int y1, int x2, int y2) {
        this();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void setBounds(net.thevpc.diagram4j.model.JDiagramBounds rectangle) {
        //must retain order
        JDiagramBounds initial = new JDiagramBounds(x1, y1, x2, y2);

        if (this.x1 == initial.getMinX()) {
            this.x1 = rectangle.getMinX();
        } else if (this.x1 == initial.getMaxX()) {
            this.x1 = rectangle.getMaxX();
        }

        if (this.x2 == initial.getMinX()) {
            this.x2 = rectangle.getMinX();
        } else if (this.x2 == initial.getMaxX()) {
            this.x2 = rectangle.getMaxX();
        }

        if (this.y1 == initial.getMinY()) {
            this.y1 = rectangle.getMinY();
        } else if (this.y1 == initial.getMaxY()) {
            this.y1 = rectangle.getMaxY();
        }

        if (this.y2 == initial.getMinY()) {
            this.y2 = rectangle.getMinY();
        } else if (this.y2 == initial.getMaxY()) {
            this.y2 = rectangle.getMaxY();
        }
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

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int centerX() {
        return Math.abs(x1 + x2) / 2;
    }

    public int radiusX() {
        return Math.abs(x1 - x2) / 2;
    }

    public int radiusY() {
        return Math.abs(y1 - y2) / 2;
    }

    public int centerY() {
        return Math.abs(y1 + y2) / 2;
    }

    @Override
    public boolean contains(int mx, int my, JDiagram diagram) {
        return GeomUtils.isOnSegment(mx, my, x1, y1, x2, y2);
    }

    @Override
    public double distanceTo(int x, int y, JDiagram diagram) {
        double d = GeomUtils.distance(x, y, x1, y1, x2, y2);
        return d;
    }

    @Override
    public void moveBy(int dx, int dy, JDiagram diagram) {
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
        build(diagram);
    }

    @Override
    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram) {
        if (port == null) {
            moveBy(dx, dy, diagram);
        } else {
            switch (port.getIndex()) {
                case 0: {
                    x1 += dx;
                    y1 += dy;
                    break;
                }
                case 1: {
                    x2 += dx;
                    y2 += dy;
                    break;
                }
                case 3: {
                    break;
                }
            }
            build(diagram);
        }
    }

    public void build(JDiagram diagram) {
        if (getPorts().length == 0) {
            addReadOnlyPorts(2, diagram);

        }
        getPort(0, diagram).setXY(x1, y1);
        getPort(1, diagram).setXY(x2, y2);
    }

    public double length() {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public boolean isEmpty() {
        return ((int) length()) == 0;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        return new JDiagramBounds(x1, y1, x2, y2);
    }

    @Override
    public void configure(JDiagramConfig config) {
        super.configure(config);
        setStartArrowType(config.getStartArrowType());
        setEndArrowType(config.getEndArrowType());
    }

}
