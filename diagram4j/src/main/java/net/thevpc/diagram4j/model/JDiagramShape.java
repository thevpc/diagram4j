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
public interface JDiagramShape extends JDiagramElement {

    JDiagramPort createPort(int x, int y, JDiagram diagram);

    GridMagnetMode getGridMagnetMode();

    String getFillColor();

    void setGridMagnetMode(GridMagnetMode mode);

    void setFillColor(String color);

    public abstract void setBounds(JDiagramBounds rectangle);

}
