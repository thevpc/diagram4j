/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.EllipseShape;

/**
 *
 * @author vpc
 */
public class EllipseShapeRenderer extends AbstractShapeRenderer<EllipseShape> {

    public void drawText(EllipseShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x = shape.centerX();
        int y = shape.centerY();
        man.drawString(g, x, y, shape.getText());
    }

    public void drawBorder(EllipseShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x = shape.centerX();
        int y = shape.centerY();
        int rx = shape.radiusX();
        int ry = shape.radiusY();
        g.drawOval(x - rx, y - ry, rx + rx, ry + ry);
    }

    public void fill(EllipseShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x = shape.centerX();
        int y = shape.centerY();
        int rx = shape.radiusX();
        int ry = shape.radiusY();
        g.fillOval(x - rx, y - ry, rx + rx, ry + ry);
    }

}
