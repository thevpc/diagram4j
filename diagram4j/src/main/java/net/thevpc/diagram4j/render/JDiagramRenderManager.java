/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.HashMap;
import java.util.Map;
import net.thevpc.diagram4j.JFontFormatter;
import net.thevpc.diagram4j.actions.JDiagramAddEdgeAction;
import net.thevpc.diagram4j.actions.JDiagramAddCircleAction;
import net.thevpc.diagram4j.actions.JDiagramAddEllipseAction;
import net.thevpc.diagram4j.actions.JDiagramAddSegmentAction;
import net.thevpc.diagram4j.actions.JDiagramAddRectangleAction;
import net.thevpc.diagram4j.actions.JDiagramSelectNodeAction;
import net.thevpc.diagram4j.actions.JDiagramAddSquareAction;
import net.thevpc.diagram4j.actions.JDiagramAddTextAction;
import net.thevpc.diagram4j.render.elements.CircleShapeRenderer;
import net.thevpc.diagram4j.render.elements.DiagramEdgeRenderer;
import net.thevpc.diagram4j.render.elements.EllipseShapeRenderer;
import net.thevpc.diagram4j.render.actions.DiagramAddEdgeActionRenderer;
import net.thevpc.diagram4j.render.actions.DiagramAddSegmentActionRenderer;
import net.thevpc.diagram4j.render.actions.DiagramAddTextActionRenderer;
import net.thevpc.diagram4j.render.actions.DiagramSelectNodeActionRenderer;
import net.thevpc.diagram4j.render.elements.DiagramPortRenderer;
import net.thevpc.diagram4j.render.elements.RectangleShapeRenderer;
import net.thevpc.diagram4j.render.elements.SegmentShapeRenderer;
import net.thevpc.diagram4j.render.elements.SquareShapeRenderer;
import net.thevpc.diagram4j.render.elements.TextShapeRenderer;
import net.thevpc.diagram4j.model.JDiagramEdge;
import net.thevpc.diagram4j.model.shapes.CircleShape;
import net.thevpc.diagram4j.model.shapes.EllipseShape;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.shapes.SegmentShape;
import net.thevpc.diagram4j.model.shapes.RectangleShape;
import net.thevpc.diagram4j.model.shapes.SquareShape;
import net.thevpc.diagram4j.model.shapes.TextShape;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramElement;
import net.thevpc.diagram4j.JPaintFormatter;
import net.thevpc.diagram4j.JStrokeFormatter;
import net.thevpc.diagram4j.model.JDiagramShape;
import net.thevpc.diagram4j.render.actions.JDiagramAddCircleActionRenderer;
import net.thevpc.diagram4j.render.actions.JDiagramAddEllipseActionRenderer;
import net.thevpc.diagram4j.render.actions.JDiagramAddRectangleActionRenderer;
import net.thevpc.diagram4j.render.actions.JDiagramAddSquareActionRenderer;

/**
 *
 * @author vpc
 */
public class JDiagramRenderManager {

    private Map<Class, DiagramRenderer> renderers = new HashMap<>();
    private JPaintFormatter paintFormatter;
    private JFontFormatter fontFormatter;
    private JStrokeFormatter strokeFormatter;

    public JDiagramRenderManager() {
        renderers.put(JDiagramAddEdgeAction.class, new DiagramAddEdgeActionRenderer());
        renderers.put(JDiagramSelectNodeAction.class, new DiagramSelectNodeActionRenderer());
        renderers.put(JDiagramAddSegmentAction.class, new DiagramAddSegmentActionRenderer());
        renderers.put(JDiagramAddTextAction.class, new DiagramAddTextActionRenderer());
        renderers.put(JDiagramAddCircleAction.class, new JDiagramAddCircleActionRenderer());
        renderers.put(JDiagramAddEllipseAction.class, new JDiagramAddEllipseActionRenderer());
        renderers.put(JDiagramAddRectangleAction.class, new JDiagramAddRectangleActionRenderer());
        renderers.put(JDiagramAddSquareAction.class, new JDiagramAddSquareActionRenderer());

        //
        renderers.put(SquareShape.class, new SquareShapeRenderer());
        renderers.put(RectangleShape.class, new RectangleShapeRenderer());
        renderers.put(CircleShape.class, new CircleShapeRenderer());
        renderers.put(EllipseShape.class, new EllipseShapeRenderer());
        renderers.put(TextShape.class, new TextShapeRenderer());
        renderers.put(SegmentShape.class, new SegmentShapeRenderer());
        renderers.put(JDiagramEdge.class, new DiagramEdgeRenderer());
        renderers.put(JDiagramPort.class, new DiagramPortRenderer());
    }

    public void render(JDiagram diagram, Graphics2D g) {
        for (JDiagramElement node : diagram.getElements()) {
            render(node, diagram, g);
        }
    }

    public void renderPorts(JDiagramElement element, Graphics2D g, JDiagram diagram) {
        if (diagram.isSelected(element) || diagram.isHover(element)) {
            for (JDiagramPort port : element.getPorts(diagram)) {
                render(port, diagram, g);
            }
        }
    }

    public void drawString(Graphics2D g, int x, int y, String text) {
        if (text == null) {
            return;
        }
        FontMetrics fm = g.getFontMetrics();
        int tx = x - fm.stringWidth(text) / 2;
        int ty = y - fm.getHeight() / 2 + fm.getAscent();
        g.drawString(text, tx, ty);
    }

    public void prepareDrawText(JDiagramElement shape, JDiagram diagram, Graphics2D g) {
        String color = shape.getLineColor();
        if (color == null) {
            color = diagram.getConfig().getLineColor();
        }

//        if (shape.isSelected()) {
//            color = "red";
//        } else if (shape.isHover()) {
//            color = "orange";
//        }
        g.setColor(parseColor(color));
        g.setStroke(new BasicStroke());
    }

    public void prepareLineColor(JDiagramElement shape, JDiagram dialog, Graphics2D g) {
        String color = shape.getLineColor();
        if (color == null) {
            color = dialog.getConfig().getLineColor();
        }

//        if (shape.isSelected()) {
//            color = "red";
//        } else if (shape.isHover()) {
//            color = "orange";
//        }
        g.setColor(parseColor(color));
        Stroke s = parseStroke(shape.getLineStroke());
        g.setStroke(s);
    }

    public void prepareTextColor(JDiagramElement shape, JDiagram dialog, Graphics2D g) {
        String color = shape.getTextColor();
        if (color == null) {
            color = dialog.getConfig().getTextColor();
        }

//        if (shape.isSelected()) {
//            color = "red";
//        } else if (shape.isHover()) {
//            color = "orange";
//        }
        g.setColor(parseColor(color));
        String font = shape.getTextFont();
        if (font == null) {
            font = dialog.getConfig().getTextFont();
        }
        Font f = parseFont(font);
        if (f == null) {

        } else {
            g.setFont(f);
        }
        Stroke s = new BasicStroke();// parseStroke(shape.getLineStroke());
        g.setStroke(s);
    }

    private Font parseFont(String font) {
        JFontFormatter p = getFontFormatter();
        if (p != null) {
            return p.parseFont(font);
        }
        return null;
    }

    private Color parseColor(String color) {
        Paint p = parsePaint(color);
        if (p instanceof Color) {
            return (Color) p;
        }
        return Color.BLACK;
    }

    private Paint parsePaint(String paint) {
        JPaintFormatter sf = getPaintFormatter();
        Paint s = null;
        if (sf == null) {
            s = Color.BLACK;
        } else {
            s = sf.parsePaint(paint);
            if (s == null) {
                s = Color.BLACK;
            }
        }
        return s;
    }

    private Stroke parseStroke(String stroke) {
        JStrokeFormatter sf = getStrokeFormatter();
        Stroke s = null;
        if (sf == null) {
            s = new BasicStroke();
        } else {
            s = sf.parseStroke(stroke);
            if (s == null) {
                s = new BasicStroke();
            }
        }
        return s;
    }

    public void prepareFillColor(JDiagramShape shape, JDiagram config, Graphics2D g) {
        String color = shape.getFillColor();
        if (color == null) {
            color = config.getConfig().getFillColor();
        }
        if (color == null) {
            color = "white";
        }
        Paint p = parsePaint(color);
        g.setPaint(p == null ? Color.lightGray : p);

//        Composite old = g.getComposite();
//        float fillOpacity = config.getFillOpacity();
//        if (fillOpacity < 0) {
//            fillOpacity = 0;
//        } else if (fillOpacity > 1) {
//            fillOpacity = 1;
//        }
//        if (fillOpacity < 1) {
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fillOpacity));
//        } else {
//            g.setComposite(old);
//        }
    }

    public void render(Object element, JDiagram diagram, Graphics2D g) {
        if (element == null) {
            return;
        }
        DiagramRenderer r = renderers.get(element.getClass());
        if (r == null) {
            throw new IllegalArgumentException("renderer not found for " + element.getClass());
        }
        r.render(element, g, diagram, this);
    }

    public JPaintFormatter getPaintFormatter() {
        return paintFormatter;
    }

    public void setPaintFormatter(JPaintFormatter paintFormatter) {
        this.paintFormatter = paintFormatter;
    }

    public JStrokeFormatter getStrokeFormatter() {
        return strokeFormatter;
    }

    public void setStrokeFormatter(JStrokeFormatter strokeFormatter) {
        this.strokeFormatter = strokeFormatter;
    }

    public JFontFormatter getFontFormatter() {
        return fontFormatter;
    }

    public void setFontFormatter(JFontFormatter fontFormatter) {
        this.fontFormatter = fontFormatter;
    }

}
