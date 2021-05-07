package net.thevpc.diagram4j.model.shapes;

import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;

public class EllipseShape extends JDiagramShapeImpl  implements Cloneable{

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public EllipseShape() {

    }

    public EllipseShape(int x1, int y1, int x2, int y2) {
        this();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void setBounds(net.thevpc.diagram4j.model.JDiagramBounds rectangle) {
        this.x1 = (int) rectangle.getMinX();
        this.y1 = (int) rectangle.getMinY();
        this.x2 = (int) rectangle.getMaxX();
        this.y2 = (int) rectangle.getMaxY();
    }

    @Override
    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram) {
        moveBy(dx, dy, diagram);
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

        int xx = centerX() - mx;
        int yy = centerY() - my;
        int rx = radiusX();
        int ry = radiusY();

        return xx * xx / (rx * rx) + (yy * yy) / (ry * ry) <= 1;
    }

    @Override
    public double distanceTo(int mx, int my,JDiagram diagram) {
        int xx = Math.abs(centerX() - mx);
        int yy = Math.abs(centerY() - my);
        int rx = radiusX();
        int ry = radiusY();

        double t = Math.sqrt(1.0 * xx * xx / (rx * rx) + 1.0 * (yy * yy) / (ry * ry));
        if (t <= 1) {
//            System.out.println("distance to ellipse : " + t);
            return 0;
        }
        double dx = Math.abs(xx - rx);
        double dy = Math.abs(yy - ry);
        double an = Math.atan2(yy, xx);

        double d = Math.abs(dx * Math.cos(an) + dy * Math.sin(an)) / 2;
//        double d = 
////                t * Math.sqrt((rx * rx) + (ry * ry));
//        if (Math.abs(xx) < Math.abs(yy)) {
//            d = Math.abs(d - rx);
//        } else {
//            d = Math.abs(d - ry);
//        }
//        System.out.println("distance to ellipse : " + t + " d=" + dx+","+ dy+","+ d + " radius=" + rx + "," + ry);
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

    public void build(JDiagram diagram) {
        if (getPorts().length == 0) {
            addReadOnlyPorts(1, diagram);
        }
        getPort(0,diagram).setXY(centerX(), centerY());
    }

    public boolean isEmpty() {
        return Math.abs(x1 - x2) == 0 || Math.abs(y1 - y2) == 0;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        return new JDiagramBounds(x1, y1, x2, y2);
    }
}
