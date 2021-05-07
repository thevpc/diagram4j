/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.actions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collections;
import javax.swing.SwingUtilities;
import net.thevpc.diagram4j.AbstractDiagramEditorAction;
import net.thevpc.diagram4j.model.shapes.RectangleShape;
import net.thevpc.diagram4j.model.JDiagramElement;
import net.thevpc.diagram4j.model.JDiagramSelectionMode;

/**
 *
 * @author vpc
 */
public class JDiagramAddRectangleAction extends AbstractDiagramEditorAction {

    Point point1 = null;
    Point point2 = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            JDiagramElement el = getDiagram().findElement(e.getX(), e.getY());
            getDiagram().setSelected(
                    el == null ? Collections.emptySet() : Collections.singleton(el.getUuid()),
                    e.isControlDown() ? JDiagramSelectionMode.TOGGLE : JDiagramSelectionMode.REPLACE);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (point1 == null) {
                point1 = e.getPoint();
            } else {
                point2 = e.getPoint();
            }
            getCanvas().repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        RectangleShape c = getShape();
        if (c != null) {
            getDiagram().addElement(c);
            getCanvas().repaint();
            reset();
        }
    }

    public RectangleShape getShape() {
        if (point1 == null && point2 == null) {
            return null;
        }
        RectangleShape c;
        if (point2 == null) {
            c = new RectangleShape(point1.x, point1.y, point1.x, point1.y);
        } else {
            c = new RectangleShape(point1.x, point1.y, point2.x, point2.y);
        }
        c.configure(getConfigSnapshot());
        return c;
    }

    public void reset() {
        point1 = null;
        point2 = null;
    }

    public int getRadius() {
        if (point1 != null && point2 != null) {
            return (int) Math.sqrt(
                    1.0 * (point1.getX() - point2.getX()) * (point1.getX() - point2.getX())
                    + 1.0 * (point1.getY() - point2.getY()) * (point1.getY() - point2.getY())
            );

        }
        return 0;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

}
