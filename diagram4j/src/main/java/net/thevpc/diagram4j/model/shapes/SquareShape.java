package net.thevpc.diagram4j.model.shapes;

import java.awt.Point;
import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;

public class SquareShape extends JDiagramShapeImpl implements Cloneable {

    protected int r;
    protected int x;
    protected int y;

    public SquareShape() {
    }

    public SquareShape(int x, int y, int r) {
        this();
        this.x = x;
        this.y = y;
        this.r = r;
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

    public int centerX() {
        return x;
    }

    public int centerY() {
        return y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram) {
        moveBy(dx, dy, diagram);
        build(diagram);
    }

    @Override
    public boolean contains(int mx, int my, JDiagram diagram) {
        Point a = resolvePoint1();
        Point b = resolvePoint2();
        int x1 = a.x;
        int y1 = a.y;
        int x2 = b.x;
        int y2 = b.y;
        return mx >= x1 && mx <= x2
                && my >= y1 && my <= y2;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        Point p1 = resolvePoint1();
        Point p2 = resolvePoint2();
        return new JDiagramBounds(p1.x, p1.y, p2.x, p2.y);
    }

    @Override
    public double distanceTo(int mx, int my, JDiagram diagram) {
        Point a = resolvePoint1();
        Point b = resolvePoint2();
        int x1 = a.x;
        int y1 = a.y;
        int x2 = b.x;
        int y2 = b.y;
        double dx = Math.max(x1 - mx, Math.max(0, mx - x2));
        double dy = Math.max(y1 - my, Math.max(0, my - y2));
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void moveBy(int dx, int dy, JDiagram diagram) {
        x += dx;
        y += dy;
        build(diagram);
    }

    public int resolveWidth() {
        return (int) (r / Math.sqrt(2)) * 2;
    }

    public void build(JDiagram diagram) {
        if (getPorts().length == 0) {
            addReadOnlyPorts(4, diagram);
        }

        int f = (int) (r / Math.sqrt(2));
        int x1 = x - f;
        int y1 = y - f;
        int x2 = x + f;
        int y2 = y + f;
        getPort(0, diagram).setXY(x1, y1);
        getPort(1, diagram).setXY(x1, y2);
        getPort(2, diagram).setXY(x2, y2);
        getPort(3, diagram).setXY(x2, y1);
    }

    public Point resolvePoint1() {
        int f = (int) (r / Math.sqrt(2));
        int x1 = x - f;
        int y1 = y - f;
        return new Point(x1, y1);
    }

    public Point resolvePoint2() {
        int f = (int) (r / Math.sqrt(2));
        int x1 = x + f;
        int y1 = y + f;
        return new Point(x1, y1);
    }

    public boolean isEmpty() {
        return r == 0;
    }
}
