/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.render.AbstractDiagramRenderer;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.JDiagramShapeImpl;

/**
 *
 * @author vpc
 */
public abstract class AbstractShapeRenderer<T extends JDiagramShapeImpl> extends AbstractDiagramRenderer<T> {

    public abstract void drawText(T shape, Graphics2D g, JDiagram config, JDiagramRenderManager man);

    public void drawPorts(T shape, Graphics2D g, JDiagram config, JDiagramRenderManager man) {
        man.renderPorts(shape, g, config);
    }

    public abstract void drawBorder(T shape, Graphics2D g, JDiagram config, JDiagramRenderManager man);

    public abstract void fill(T shape, Graphics2D g, JDiagram config, JDiagramRenderManager man);

    @Override
    public void render(T shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        if (shape.getFillColor() != null && shape.getFillColor().length() > 0) {
            Composite old = g.getComposite();
            man.prepareFillColor(shape, diagram, g);
            fill(shape, g, diagram, man);
            g.setComposite(old);
        }
        if (shape.getLineStroke() != null && shape.getLineStroke().length() > 0) {
            man.prepareLineColor(shape, diagram, g);
            drawBorder(shape, g, diagram, man);
        }
        drawPorts(shape, g, diagram, man);
        man.prepareDrawText(shape, diagram, g);

        if (shape.getText() != null && shape.getText().length() > 0) {
            Font f = g.getFont();
            man.prepareTextColor(shape, diagram, g);
            drawText(shape, g, diagram, man);
            g.setFont(f);
        }
    }

}
