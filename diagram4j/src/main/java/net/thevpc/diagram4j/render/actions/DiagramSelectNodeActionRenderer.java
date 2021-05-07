/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramSelectNodeAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;

/**
 *
 * @author vpc
 */
public class DiagramSelectNodeActionRenderer extends AbstractDiagramRenderer<JDiagramSelectNodeAction> {

    @Override
    public void render(JDiagramSelectNodeAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        JDiagramSelectNodeAction.DraggedInfo d = a.getDragged();
        if (d != null && d.select && d.selectEnd != null) {
            int x10 = d.selectStart.x;
            int y10 = d.selectStart.y;
            int x20 = d.selectEnd.x;
            int y20 = d.selectEnd.y;
//            String s1 = "x1=" + x10 + " " + "y1=" + y10 + " " + "x2=" + x20 + " " + "y2=" + y20;
            int x1 = Math.min(x10, x20);
            int y1 = Math.min(y10, y20);
            int x2 = Math.max(x10, x20);
            int y2 = Math.max(y10, y20);
//            String s2 = "x1=" + x1 + " " + "y1=" + y1 + " " + "x2=" + x2 + " " + "y2=" + y2;
//            System.out.println(s1 + " :: " + s2);
            g.setColor(Color.GRAY);
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{4, 0, 4}, 0));
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
        }
    }

}
