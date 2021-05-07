/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.RectangleShape;

/**
 *
 * @author vpc
 */
public class RectangleShapeRenderer extends AbstractShapeRenderer<RectangleShape> {

    public void drawText(RectangleShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        man.drawString(g, shape.centerX(), shape.centerY(), shape.getText());
    }

    public void drawBorder(RectangleShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x1 = shape.getX1();
        int y1 = shape.getY1();
        int x2 = shape.getX2();
        int y2 = shape.getY2();
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

    public void fill(RectangleShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x1 = shape.getX1();
        int y1 = shape.getY1();
        int x2 = shape.getX2();
        int y2 = shape.getY2();
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

}
