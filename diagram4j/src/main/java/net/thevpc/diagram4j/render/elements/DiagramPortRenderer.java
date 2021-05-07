/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.JDiagramPort;

/**
 *
 * @author vpc
 */
public class DiagramPortRenderer extends AbstractDiagramRenderer<JDiagramPort> {

    @Override
    public void render(JDiagramPort port, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        int x = port.getX();
        int y = port.getY();
        if (diagram.isHover(port)) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x - 4, y - 4, 9, 8);
    }

}
