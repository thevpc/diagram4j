/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.actions;

import java.awt.Graphics2D;
import java.awt.Point;
import net.thevpc.diagram4j.actions.JDiagramAddSquareAction;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.shapes.SquareShape;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;

/**
 *
 * @author vpc
 */
public class JDiagramAddSquareActionRenderer extends AbstractDiagramRenderer<JDiagramAddSquareAction> {

    @Override
    public void render(JDiagramAddSquareAction a, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        SquareShape s = a.getShape();
        if (s != null && !s.isEmpty()) {
            Point p1 = s.resolvePoint1();
            int w = s.resolveWidth();
            man.prepareLineColor(s, diagram, g);
            g.drawRect(p1.x, p1.y, w, w);
        }
    }

}
