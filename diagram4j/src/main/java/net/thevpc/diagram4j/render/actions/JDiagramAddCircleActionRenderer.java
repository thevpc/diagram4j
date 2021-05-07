/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramAddCircleAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.shapes.CircleShape;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;

/**
 *
 * @author vpc
 */
public class JDiagramAddCircleActionRenderer extends AbstractDiagramRenderer<JDiagramAddCircleAction> {

    @Override
    public void render(JDiagramAddCircleAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        CircleShape s = a.getShape();
        if (s != null && !s.isEmpty()) {
            int x = s.getX();
            int y = s.getY();
            int r = s.getR();
            man.prepareLineColor(s, diagram, g);
            g.drawOval(x - r, y - r, r + r, r + r);
        }
    }

}
