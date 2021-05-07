/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramAddRectangleAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.shapes.RectangleShape;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;

/**
 *
 * @author vpc
 */
public class JDiagramAddRectangleActionRenderer extends AbstractDiagramRenderer<JDiagramAddRectangleAction> {

    @Override
    public void render(JDiagramAddRectangleAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        RectangleShape s = a.getShape();
        if (s != null && !s.isEmpty()) {
            int x1 = s.getX1();
            int y1 = s.getY1();
            int x2 = s.getX2();
            int y2 = s.getY2();
            man.prepareLineColor(s, diagram, g);
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
        }
        return;
    }

}
