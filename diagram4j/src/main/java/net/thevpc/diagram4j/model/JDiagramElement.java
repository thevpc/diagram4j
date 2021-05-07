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
public interface JDiagramElement extends JDiagramGeometry {

    JDiagramPort[] getPorts(JDiagram diagram);

    boolean contains(int mx, int my, JDiagram diagram);

    void moveBy(int dx, int dy, JDiagram diagram);

    void moveTo(int x, int y, JDiagram diagram);

    JDiagramBounds getBounds(JDiagram diagram);

    public void movePortBy(int dx, int dy, JDiagramPort port, JDiagram diagram);

    String getLineStroke();

    String getLineColor();

    void setLineStroke(String value);

    void setLineColor(String color);

    void setTextColor(String color);

    void setTextFont(String color);

    String getText();

    String getTextColor();

    String getTextFont();

    void setText(String text);

    public void build(JDiagram diagram);

    public JDiagramElement copy(JDiagram diagram);

}
