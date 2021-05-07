package net.thevpc.diagram4j.model.shapes;

import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;

public class RectangleShape extends JDiagramShapeImpl  implements Cloneable{

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public RectangleShape() {
    }

    public RectangleShape(int x1, int y1, int x2, int y2) {
        this();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void build(JDiagram diagram) {
        if (getPorts().length == 0) {
            addReadOnlyPorts(4,diagram);
        }
        int x1 = Math.min(this.x1, this.x2);
        int y1 = Math.min(this.y1, this.y2);
        int x2 = Math.max(this.x1, this.x2);
        int y2 = Math.max(this.y1, this.y2);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        getPort(0, diagram).setXY(x1, y1);
        getPort(1, diagram).setXY(x1, y2);
        getPort(2, diagram).setXY(x2, y2);
        getPort(3, diagram).setXY(x2, y1);
    }

    @Override
    public void setBounds(net.thevpc.diagram4j.model.JDiagramBounds rectangle) {
        this.x1 = (int) rectangle.getMinX();
        this.y1 = (int) rectangle.getMinY();
        this.x2 = (int) rectangle.getMaxX();
        this.y2 = (int) rectangle.getMaxY();
    }

    public boolean isEmpty() {
        return getWidth() == 0 || getHeight() == 0;
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
    public boolean contains(int mx, int my,JDiagram diagram) {
        return mx >= x1 && mx <= x2
                && my >= y1 && my <= y2;
    }

    @Override
    public double distanceTo(int mx, int my,JDiagram diagram) {
        double dx = Math.max(this.x1 - mx, Math.max(0, mx - this.x2));
        double dy = Math.max(this.y1 - my, Math.max(0, my - this.y2));
        return Math.sqrt(dx * dx + dy * dy);
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
                    x1 += dx;
                    y2 += dy;
                    break;
                }
                case 2: {
                    x2 += dx;
                    y2 += dy;
                    break;
                }
                case 3: {
                    x2 += dx;
                    y1 += dy;
                    break;
                }
            }
            build(diagram);
        }
    }

    @Override
    public void moveBy(int dx, int dy, JDiagram diagram) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        build(diagram);
    }

    private int getWidth() {
        return Math.abs(x2 - x1);
    }

    private int getHeight() {
        return Math.abs(y2 - y1);
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        JDiagramPort p1 = getPort(0,diagram);
        JDiagramPort p2 = getPort(2,diagram);
        return new JDiagramBounds(p1.getX(), p1.getY(),
                p2.getX(), p2.getY()
        );
    }

}
