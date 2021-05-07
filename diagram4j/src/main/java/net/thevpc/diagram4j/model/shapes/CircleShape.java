package net.thevpc.diagram4j.model.shapes;

import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;

public class CircleShape extends JDiagramShapeImpl implements Cloneable{

    protected int r;
    protected int x;
    protected int y;

    public CircleShape() {

    }

    public CircleShape(int x, int y, int r) {
        this();
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public void build(JDiagram diagram) {
        if (getPorts().length == 0) {
            addReadOnlyPorts(1, diagram);
        }
        getPort(0, diagram).setXY(x, y);
    }

    @Override
    public void setBounds(net.thevpc.diagram4j.model.JDiagramBounds rectangle) {
        int x1 = (int) rectangle.getMinX();
        int y1 = (int) rectangle.getMinY();
        int x2 = (int) rectangle.getMaxX();
        int y2 = (int) rectangle.getMaxY();
        int x0 = (int) ((rectangle.getMinX() + rectangle.getMaxX()) / 2);
        int y0 = (int) ((rectangle.getMinY() + rectangle.getMaxY()) / 2);
        int w = Math.min(x2 - x1, y2 - y1);
        this.x = x0;
        this.y = y0;
        this.r = w / 2;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void changeRadius(int step) {
        setR(r + step);
    }

    @Override
    public String toString() {
        return super.toString() + "{r: " + Integer.toString(r) + ", c: " + getLineColor() + ", t: " + getText() + "}";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean contains(int mx, int my, JDiagram diagram) {
        int a = x - mx;
        int b = y - my;

        return a * a + b * b <= r * r;
    }

    @Override
    public double distanceTo(int mx, int my, JDiagram diagram) {
        int a = x - mx;
        int b = y - my;
        double d = Math.sqrt(a * a + b * b);
        double t = (d - r);
        if (t <= 0) {
            return 0;
        }
        return t;
    }

    @Override
    public void moveBy(int dx, int dy, JDiagram diagram) {
        x += dx;
        y += dy;
        build(diagram);
    }

    @Override
    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram) {
        moveBy(dx, dy, diagram);
    }

    public boolean isEmpty() {
        return r == 0;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        int x1 = x - r;
        int y1 = y - r;
        int x2 = x + r;
        int y2 = y + r;
        return new JDiagramBounds(x1, y1, x2, y2);
    }

}
