package net.thevpc.diagram4j.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JDiagramGrid implements Serializable {

    private Dimension dimension;
    private int gridX;
    private int gridY;

    private Color color;

    private List<Rectangle2D.Double> rects;

    public JDiagramGrid(Dimension dimension, int gridX, int gridY) {
        color = new Color(0, 0, 0, 0.1f);
        scaleGrid(dimension, gridX, gridY);

    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            this.dimension = new Dimension(0, 0);
        } else {
            this.dimension = dimension;
        }
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridX(int gridX) {
        if (gridX <= 0) {
            gridX = 10;
        }
        this.gridX = gridX;
    }

    public void setGridY(int gridY) {
        if (gridY <= 0) {
            gridY = 10;
        }
        this.gridY = gridY;
    }

    public void scaleGrid(Dimension dimension, int ticX, int ticY) {
        setGridX(ticX);
        setGridY(ticY);
        scaleGrid(dimension);
    }

    public void scaleGrid(Dimension dimension) {
        setDimension(dimension);
        rebuild();
    }

    public void rebuild() {
        rects = new ArrayList<Rectangle2D.Double>();
        for (int x = gridX; x <= dimension.getWidth(); x += 2 * gridX) {
            rects.add(new Rectangle2D.Double(x, -1, gridX, (int) dimension.getHeight()));
        }

        for (int y = gridY; y < dimension.getHeight(); y += 2 * gridY) {
            rects.add(new Rectangle2D.Double(0, y, (int) dimension.getWidth(), gridY));

        }

    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        for (Rectangle2D.Double rect : rects) {
            g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        }

    }

}
