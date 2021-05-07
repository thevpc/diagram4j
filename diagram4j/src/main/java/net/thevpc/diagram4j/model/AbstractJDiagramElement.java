/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author vpc
 */
public abstract class AbstractJDiagramElement implements JDiagramElement {

    protected String uuid;
    private String lineColor;
    private String text;
    private String textColor;
    private String textFont;
    private String lineStroke;
    protected List<String> ports = new ArrayList<>();

    public void ensureUuid() {
        if (getUuid() == null) {
            String toString = UUID.randomUUID().toString();
            setUuid(toString);
        }
    }

    @Override
    public JDiagramElement copy(JDiagram diagram) {
        return clone();
    }

    @Override
    protected JDiagramElement clone() {
        AbstractJDiagramElement a;
        try {
            a = (AbstractJDiagramElement) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new IllegalArgumentException(ex);
        }
        a.setUuid(null);
        a.ports = new ArrayList<>();
        return a;
    }

    @Override
    public void moveTo(int x, int y, JDiagram diagram) {
        JDiagramBounds b = getBounds(diagram);
        int x0 = b.getMinX();
        int y0 = b.getMinY();
        int dx = x - x0;
        int dy = y - y0;
        moveBy(dx, dy, diagram);
    }

    public JDiagramPort getPort(int c, JDiagram diagram) {
        return diagram.getPort(ports.get(c));
    }

    @Override
    public JDiagramPort[] getPorts(JDiagram diagram) {
        JDiagramPort[] a = new JDiagramPort[ports.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = diagram.getPort(ports.get(i));

        }
        return a;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getLineColor() {
        return lineColor;
    }

    @Override
    public void setLineColor(String color) {
        this.lineColor = color;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            this.text = "";
        } else {
            this.text = text;
        }
    }

    protected void addReadOnlyPorts(int count, JDiagram diagram) {
        for (int i = 0; i < count; i++) {
            JDiagramPort port = diagram.addPort();
            port.setIndex(i);
            port.setReadOnly(true);
            port.setShapeUuid(getUuid());
            ports.add(port.getUuid());
        }
    }

    public void configure(JDiagramConfig config) {
        this.setLineColor(config.getLineColor());
        this.setLineStroke(config.getLineStroke());
        this.setTextColor(config.getTextColor());
        this.setTextFont(config.getTextFont());
    }

    @Override
    public String getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(String lineStroke) {
        this.lineStroke = lineStroke;
    }

    public String[] getPorts() {
        return ports.toArray(new String[0]);
    }

    public void build(JDiagram diagram) {

    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

}
