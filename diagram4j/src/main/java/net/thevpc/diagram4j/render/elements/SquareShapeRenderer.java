/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.SquareShape;

/**
 *
 * @author vpc
 */
public class SquareShapeRenderer extends AbstractShapeRenderer<SquareShape> {

    public void drawText(SquareShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        man.drawString(g, shape.centerX(), shape.centerY(), shape.getText());
    }

    public void drawBorder(SquareShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        Point p1 = shape.resolvePoint1();
        int w = shape.resolveWidth();
        g.drawRect(p1.x, p1.y, w, w);
    }

    public void fill(SquareShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        Point p1 = shape.resolvePoint1();
        int w = shape.resolveWidth();
        g.fillRect(p1.x, p1.y, w, w);
    }

}
