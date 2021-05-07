/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramAddEdgeAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.JDiagramEdge;
import net.thevpc.diagram4j.model.JDiagramPort;

/**
 *
 * @author vpc
 */
public class DiagramAddEdgeActionRenderer extends AbstractDiagramRenderer<JDiagramAddEdgeAction> {

    @Override
    public void render(JDiagramAddEdgeAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        JDiagramEdge e = a.getEdge();
        if (e != null && !e.isEmpty(diagram)) {
            int x1 = a.getFirstPort().getX();
            int y1 = a.getFirstPort().getY();
            int x2 = a.getSecondPort().getX();
            int y2 = a.getSecondPort().getY();
            man.prepareLineColor(e, diagram, g);
            g.drawLine(x1, y1, x2, y2);
            return;
        }
        if (a.getSecondPoint() != null) {
            JDiagramPort gp = new JDiagramPort();
            gp.setXY(a.getSecondPoint().x, a.getSecondPoint().y);
            e = new JDiagramEdge();
            int x1 = a.getFirstPort().getX();
            int y1 = a.getFirstPort().getY();
            int x2 = a.getSecondPoint().x;
            int y2 = a.getSecondPoint().y;
            man.prepareLineColor(e, diagram, g);
            g.drawLine(x1, y1, x2, y2);
            return;
        }
    }

}
