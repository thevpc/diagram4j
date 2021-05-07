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
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.JDiagramConfig;
import net.thevpc.diagram4j.model.JDiagramEdge;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.util.DiagramArrows;

/**
 *
 * @author vpc
 */
public class DiagramEdgeRenderer extends AbstractDiagramRenderer<JDiagramEdge> {

    public void drawText(JDiagramEdge shape, Graphics2D g, JDiagram config, JDiagramRenderManager man) {
//        man.drawString(g, shape.centerX(), shape.centerY(), shape.getText());
    }

    public void drawLine(JDiagramEdge edge, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        JDiagramPort port1 = edge.getPorts(diagram)[0];
        JDiagramPort port2 = edge.getPorts(diagram)[1];

        int x1 = port1.getX();
        int y1 = port1.getY();
        int x2 = port2.getX();
        int y2 = port2.getY();

        DiagramArrows.drawArrow(g, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), g.getStroke(), new BasicStroke(), 20,
                DiagramArrows.parseArrowTypeLenient(edge.getStartArrowType()),
                DiagramArrows.parseArrowTypeLenient(edge.getEndArrowType())
        );

    }

    @Override
    public void render(JDiagramEdge shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        if (shape.getLineStroke() != null && shape.getLineStroke().length() > 0) {
            man.prepareLineColor(shape, diagram, g);
            drawLine(shape, g, diagram, man);
        }
        drawText(shape, g, diagram, man);
    }

}
