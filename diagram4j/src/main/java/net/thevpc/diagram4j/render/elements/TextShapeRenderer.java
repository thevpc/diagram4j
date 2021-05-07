/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.elements;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramBounds;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.TextShape;

/**
 *
 * @author vpc
 */
public class TextShapeRenderer extends AbstractShapeRenderer<TextShape> {

    @Override
    public void drawText(TextShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        if (shape.isEditing()) {
            return;
        }
        FontMetrics fm = g.getFontMetrics();
        int x1 = shape.getX1() + shape.getMarginX();
        int y1 = shape.getY1() + shape.getMarginY() + fm.getAscent();

//        int x2 = shape.getX2() - shape.getxMargin();
//        int y2 = shape.getY2() - shape.getyMargin();
//        JDiagramBounds b = new JDiagramBounds(x1, y1, x2, y2);
        String text = shape.getText();
//        man.drawString(g, b.getCenterX(), b.getCenterY(), text);
        g.drawString(text == null ? "" : text, x1, y1);
//        g.drawString(text == null ? "" : text, shape.getX1(), shape.getY1());
    }

    @Override
    public void drawBorder(TextShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        String text = shape.getText();
        if (text == null) {
            text = "";
        }
        FontMetrics fm = g.getFontMetrics();
        int x1 = shape.getX1();
        int y1 = shape.getY1();
        int x2 = shape.getX1() + fm.stringWidth(text) + shape.getMarginX() * 2;
        int y2 = shape.getY1() + fm.getHeight() + /*fm.getAscent() + */ shape.getMarginY() * 2;

        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public void fill(TextShape shape, Graphics2D g, JDiagram diagram, JDiagramRenderManager man) {
        String text = shape.getText();
        if (text == null) {
            text = "";
        }
        FontMetrics fm = g.getFontMetrics();
        int x1 = shape.getX1();
        int y1 = shape.getY1();
        int x2 = shape.getX1() + fm.stringWidth(text) + shape.getMarginX() * 2;
        int y2 = shape.getY1() + fm.getHeight() + /*fm.getAscent() + */ shape.getMarginY() * 2;

        g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

}
