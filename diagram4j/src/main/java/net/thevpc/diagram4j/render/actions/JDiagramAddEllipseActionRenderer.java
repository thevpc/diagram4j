/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.actions.JDiagramAddEllipseAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.shapes.EllipseShape;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;

/**
 *
 * @author vpc
 */
public class JDiagramAddEllipseActionRenderer extends AbstractDiagramRenderer<JDiagramAddEllipseAction> {

    @Override
    public void render(JDiagramAddEllipseAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        EllipseShape s = a.getShape();
        if (s != null && !s.isEmpty()) {
            int x = s.centerX();
            int y = s.centerY();
            int rx = s.radiusX();
            int ry = s.radiusY();
            man.prepareLineColor(s, diagram, g);
            g.drawOval(x - rx, y - ry, rx * 2, ry * 2);
        }
    }

}
