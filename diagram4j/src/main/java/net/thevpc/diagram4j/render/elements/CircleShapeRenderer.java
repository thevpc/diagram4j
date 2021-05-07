/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.CircleShape;

/**
 *
 * @author vpc
 */
public class CircleShapeRenderer extends AbstractShapeRenderer<CircleShape> {

    public void drawText(CircleShape shape, Graphics2D g, JDiagram config, JDiagramRenderManager man) {
        int x = shape.getX();
        int y = shape.getY();
        man.drawString(g, x, y, shape.getText());
    }

    public void drawBorder(CircleShape shape, Graphics2D g, JDiagram config, JDiagramRenderManager man) {
        int x = shape.getX();
        int y = shape.getY();
        int r = shape.getR();
        g.drawOval(x - r, y - r, r + r, r + r);
    }

    public void fill(CircleShape shape, Graphics2D g, JDiagram config, JDiagramRenderManager man) {
        int x = shape.getX();
        int y = shape.getY();
        int r = shape.getR();
        g.fillOval(x - r, y - r, r + r, r + r);
    }

}
