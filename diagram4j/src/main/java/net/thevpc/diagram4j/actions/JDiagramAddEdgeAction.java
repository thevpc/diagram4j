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
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagramEdge;
import net.thevpc.diagram4j.model.JDiagramElement;
import net.thevpc.diagram4j.model.JDiagramSelectionMode;
import net.thevpc.diagram4j.model.JDiagramShape;

/**
 *
 * @author vpc
 */
public class JDiagramAddEdgeAction extends AbstractDiagramEditorAction {

    JDiagramPort firstConnector = null;
    JDiagramPort secondConnector = null;
    Point secondPoint = null;

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
            if (firstConnector == null) {
                JDiagramElement n = getDiagram().findElement(e.getX(), e.getY());
                if (n instanceof JDiagramShape) {
                    JDiagramShape first = (JDiagramShape) n;
                    firstConnector = first.createPort(e.getX(), e.getY(), getDiagram());
                }
            } else {
                secondPoint = new Point(e.getX(), e.getY());
                JDiagramElement n = getDiagram().findElement(e.getX(), e.getY());
                if (n instanceof JDiagramShape) {
                    JDiagramShape second = (JDiagramShape) n;
                    secondConnector = second.createPort(e.getX(), e.getY(), getDiagram());
                } else {
                    secondConnector = null;
                }
            }
            getCanvas().updateView();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (secondConnector != null) {
            JDiagramEdge ee = getEdge();
            getDiagram().addElement(ee);
        }
        reset();
    }

    public JDiagramEdge getEdge() {
        if (firstConnector == null || secondConnector == null) {
            return null;
        }

        JDiagramEdge ee = new JDiagramEdge();
        ee.setStartPort(firstConnector.getUuid());
        ee.setEndPort(secondConnector.getUuid());
        ee.configure(getConfigSnapshot());
        return ee;
    }

    private void reset() {
        firstConnector = null;
        secondConnector = null;
        secondPoint = null;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public JDiagramPort getFirstPort() {
        return firstConnector;
    }

    public JDiagramPort getSecondPort() {
        return secondConnector;
    }

}
