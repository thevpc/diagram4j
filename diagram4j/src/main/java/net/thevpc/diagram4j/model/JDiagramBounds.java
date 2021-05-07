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
public class JDiagramBounds {

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public JDiagramBounds(int x1, int y1, int x2, int y2) {
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
    }

    public int getCenterX() {
        return (maxX + minX) / 2;
    }

    public int getCenterY() {
        return (maxY + minY) / 2;
    }

    public int getWidth() {
        return maxX - minX;
    }

    public int getHeight() {
        return maxY - minY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean contains(JDiagramBounds bounds) {
        if (bounds.getMinX() < getMinX()) {
            return false;
        }
        if (bounds.getMinY() < getMinY()) {
            return false;
        }
        if (bounds.getMaxX() > getMaxX()) {
            return false;
        }
        if (bounds.getMaxY() > getMaxY()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiagramBounds{" + "minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.minX;
        hash = 61 * hash + this.maxX;
        hash = 61 * hash + this.minY;
        hash = 61 * hash + this.maxY;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JDiagramBounds other = (JDiagramBounds) obj;
        if (this.minX != other.minX) {
            return false;
        }
        if (this.maxX != other.maxX) {
            return false;
        }
        if (this.minY != other.minY) {
            return false;
        }
        if (this.maxY != other.maxY) {
            return false;
        }
        return true;
    }
    

}
