package net.thevpc.diagram4j.model.shapes;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramConfig;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;
import net.thevpc.diagram4j.util.GeomUtils;

public class TextShape extends JDiagramShapeImpl implements Cloneable {

    private int marginX;
    private int marginY;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String text;
    private boolean editing;

    public TextShape() {
        marginX = 6;
        marginY = 5;
    }

    public TextShape(int x1, int y1, String text) {
        this();
        this.x1 = x1;
        this.y1 = y1;
        this.text = text;
    }

    @Override
    public void setBounds(net.thevpc.diagram4j.model.JDiagramBounds rectangle) {
        this.x1 = (int) rectangle.getMinX();
        this.y1 = (int) rectangle.getMinY();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
            addReadOnlyPorts(1, diagram);
        }
        Font font = new JLabel().getFont();//change me with real font!
        Graphics2D g = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout layout = new TextLayout(text == null ? "" : text, font, frc);
        Rectangle2D b = layout.getBounds();
        x2 = ((int) (b.getWidth() + x1 + 2 * marginX));
        y2 = ((int) (b.getHeight() + y1 + 2 * marginY));
        getPort(0, diagram).setXY(x1, y1);
    }

    public double length() {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public boolean isEmpty() {
        return ((int) length()) == 0;
    }

    @Override
    public void configure(JDiagramConfig config) {
        super.configure(config);
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    @Override
    public JDiagramBounds getBounds(JDiagram diagram) {
        return new JDiagramBounds(x1, y1, x2, y2);
    }

    public int getMarginX() {
        return marginX;
    }

    public void setMarginX(int marginX) {
        this.marginX = marginX;
    }

    public int getMarginY() {
        return marginY;
    }

    public void setMarginY(int marginY) {
        this.marginY = marginY;
    }

}
