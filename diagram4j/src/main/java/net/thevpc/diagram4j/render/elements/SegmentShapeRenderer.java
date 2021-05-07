/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.SegmentShape;
import net.thevpc.diagram4j.util.DiagramArrows;

/**
 *
 * @author vpc
 */
public class SegmentShapeRenderer extends AbstractShapeRenderer<SegmentShape> {

    @Override
    public void drawText(SegmentShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        man.drawString(g, shape.centerX(), shape.centerY(), shape.getText());
    }

    @Override
    public void drawBorder(SegmentShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x1 = shape.getX1();
        int y1 = shape.getY1();
        int x2 = shape.getX2();
        int y2 = shape.getY2();

        DiagramArrows.drawArrow(g, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), g.getStroke(), new BasicStroke(), 20,
                DiagramArrows.parseArrowTypeLenient(shape.getStartArrowType()),
                DiagramArrows.parseArrowTypeLenient(shape.getEndArrowType())
        );
    }

    @Override
    public void fill(SegmentShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        //do nothing...
    }

}
