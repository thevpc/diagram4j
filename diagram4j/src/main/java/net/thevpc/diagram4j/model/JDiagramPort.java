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
public class JDiagramPort implements JDiagramGeometry {

    private String uuid;
    private String shapeUuid;
    private String edgeUuid;
    private int index;
    private boolean readOnly;
    private int x;
    private int y;

    public JDiagramPort() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEdgeUuid() {
        return edgeUuid;
    }

    public void setEdgeUuid(String edgeUuid) {
        this.edgeUuid = edgeUuid;
    }

    public String getShapeUuid() {
        return shapeUuid;
    }

    public void setShapeUuid(String shapeUuid) {
        this.shapeUuid = shapeUuid;
    }

    public int getIndex() {
        return index;
    }

    public JDiagramPort setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getX() {
        return x;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public double distanceTo(int x, int y,JDiagram diagram) {
        int x0 = getX();
        int y0 = getY();
        return Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));
    }

}
