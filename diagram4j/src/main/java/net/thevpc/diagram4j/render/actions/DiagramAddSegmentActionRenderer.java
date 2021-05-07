/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramAddSegmentAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.SegmentShape;

/**
 *
 * @author vpc
 */
public class DiagramAddSegmentActionRenderer extends AbstractDiagramRenderer<JDiagramAddSegmentAction> {

    @Override
    public void render(JDiagramAddSegmentAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        SegmentShape s = a.getShape();
        if (s != null && !s.isEmpty()) {
            int x1 = a.getPoint1().x;
            int y1 = a.getPoint1().y;
            int x2 = a.getPoint2().x;
            int y2 = a.getPoint2().y;
            man.prepareLineColor(s, diagram, g);
            g.drawLine(x1, y1, x2, y2);

        }
    }

}
