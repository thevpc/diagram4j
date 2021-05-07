/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.model;

/**
 *
 * @author vpc
 */
public class JDiagramConfig implements Cloneable {

    private String fillColor;
    private String lineColor;
    private String lineStroke;
    private String textColor;
    private String textFont;
    private String startArrowType;
    private String endArrowType;
    private GridMagnetMode mode;

    public JDiagramConfig() {
    }

    public GridMagnetMode getGridMagnetMode() {
        return mode;
    }

    public void setGridMagnetMode(GridMagnetMode mode) {
        this.mode = mode;
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

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getLineColor() {
        return lineColor;
    }

    public String getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(String lineStroke) {
        this.lineStroke = lineStroke;
    }

    public String getStartArrowType() {
        return startArrowType;
    }

    public void setStartArrowType(String startArrowType) {
        this.startArrowType = startArrowType;
    }

    public String getEndArrowType() {
        return endArrowType;
    }

    public void setEndArrowType(String endArrowType) {
        this.endArrowType = endArrowType;
    }

    public JDiagramConfig copy() {
        try {
            return (JDiagramConfig) clone();
        } catch (CloneNotSupportedException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
