/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.actions;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;
import net.thevpc.diagram4j.AbstractDiagramEditorAction;
import net.thevpc.diagram4j.model.GridMagnetMode;
import net.thevpc.diagram4j.model.JDiagramPort;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramElement;
import net.thevpc.diagram4j.model.JDiagramSelectionMode;
import net.thevpc.diagram4j.model.JDiagramShape;

/**
 *
 * @author vpc
 */
public class JDiagramSelectNodeAction extends AbstractDiagramEditorAction {

    int mouseX = 0;
    int mouseY = 0;
    private DraggedInfo dragged = null;

    public static enum DragOp {
        moveGraph,
        moveElems,
        movePorts,
        copyGraph,
        copyElems,
        copyPorts,
        select
    }

    public static class DraggedInfo {

        public List<JDiagramElement> elementsToMove;
        public List<JDiagramPort> portsToMove;
        public JDiagram graphToMove;
        public boolean moveGraph;
        public boolean moveElems;
        public boolean movePorts;
        public boolean select;
        public Point selectStart;
        public Point selectEnd;
    }

    public JDiagramSelectNodeAction() {
    }

    public DraggedInfo getDragged() {
        return dragged;
    }

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
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        dragged = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragged != null) {
            if (dragged.moveGraph) {
                dragged.graphToMove.snapToGrid(GridMagnetMode.INHERITED, false);
            } else if (dragged.moveElems) {
                for (JDiagramElement el : dragged.elementsToMove) {
                    if (el instanceof JDiagramShape) {
                        getDiagram().snapToGrid(null, (JDiagramShape) el);
                    }
                }
            } else if (dragged.movePorts) {
                for (JDiagramPort gp : dragged.portsToMove) {
                    JDiagramElement el = getDiagram().getElement(gp.getShapeUuid());
                    if (el instanceof JDiagramShape) {
                        getDiagram().snapToGrid(null, (JDiagramShape) el);
                    }
                }
            } else if (dragged.select) {
                //nothing...
            }
        }
        dragged = null;
    }

    private DraggedInfo reevalMoving(int mx, int my, boolean ctrlDow) {
        DraggedInfo m = new DraggedInfo();
        List<JDiagramElement> selectedElements = getDiagram().getSelectedElements();

        JDiagram.GraphElementAndPort elCurr = getDiagram().findElementAndPort(mx, my);
        //check if the dragged element is already selected
        if (elCurr != null) {
            boolean elemSelected = false;
            for (JDiagramElement e : selectedElements) {
                if (e.getUuid().equals(elCurr.getElem().getUuid())) {
                    elemSelected = true;
                }
            }
            if (elemSelected) {
                //the selected element is the one dragged
                //check if we are transforming
                if (elCurr.getPort() != null) {
                    //we are dragging the port (movePort)
                    m.portsToMove = Arrays.asList(elCurr.getPort());
                    m.movePorts = true;
                } else {
                    //we are dragging the element (move)
                    if (ctrlDow) {
                        m.elementsToMove = getDiagram().selectNewCopy(selectedElements);
                    } else {
                        m.elementsToMove = selectedElements;
                    }
                    m.moveElems = true;
                }
                return m;
            }
            //we are dragging an element which is not selected.
            // //make is selected and start moving
            selectedElements = Arrays.asList(elCurr.getElem());
            getDiagram().setSelected(Collections.singleton(elCurr.getElem().getUuid()), JDiagramSelectionMode.REPLACE);
            m.elementsToMove = selectedElements;
            m.moveElems = true;
            return m;
        }

        if (ctrlDow) {
            m.moveGraph = true;
            m.graphToMove = getDiagram();
        } else {
            m.select = true;
            m.selectStart = new Point(mx, my);
        }
//        if (selectedElements.isEmpty()) {
//            if (elCurr != null) {
//                if (elCurr.getPort() == null) {
//                    m.moveElems = true;
//                    m.elementsToMove = Arrays.asList(elCurr.getElem());
//                    return m;
//                } else {
//                    m.movePorts = true;
//                    m.portsToMove = Arrays.asList(elCurr.getPort());
//                    return m;
//                }
//            } else {
//                if (ctrlDow) {
//                    m.moveGraph = true;
//                    m.graphToMove = getDiagram();
//                } else {
//                    m.select = true;
//                    m.selectStart = new Point(mx, my);
//                }
//            }
//        } else {
//            int someSelected = 0;
//            JDiagramElement lastSel = null;
//            for (JDiagramElement el : selectedElements) {
//                if (el != null) {
//                    if (el.distanceTo(mx, my, getDiagram()) <= getDiagram().getDistanceError()) {
//                        lastSel = el;
//                        someSelected++;
//                    }
//                }
//            }
//            if (selectedElements.size() > 0) {
//                if (selectedElements.size() == 1 && elCurr != null
//                        && getDiagram().isSelected(elCurr.getElem())) {
//                    if (elCurr.getPort() == null) {
//                        m.moveElems = true;
//                        m.elementsToMove = Arrays.asList(elCurr.getElem());
//                        return m;
//                    } else {
//                        m.movePorts = true;
//                        m.portsToMove = Arrays.asList(elCurr.getPort());
//                        return m;
//                    }
//                } else {
//                    List<JDiagramElement> ok = new ArrayList<>();
//                    for (JDiagramElement el : selectedElements) {
//                        if (el != null) {
//                            ok.add(el);
//                        }
//                    }
//                    m.elementsToMove = ok;
//                    m.moveElems = true;
//                    return m;
//                }
//            } else {
//                if (ctrlDow) {
//                    m.graphToMove = getDiagram();
//                    m.moveGraph = true;
//                } else {
//                    m.select = true;
//                    m.selectStart = new Point(mx, my);
//                }
//            }
//        }

        return m;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        int dx = mx - mouseX;
        int dy = my - mouseY;
        if (dragged == null) {
            dragged = reevalMoving(mx, my, e.isControlDown());
        }
        if (dragged.moveGraph) {
            dragged.graphToMove.moveDiagram(dx, dy);
        } else if (dragged.moveElems) {
            for (JDiagramElement el : dragged.elementsToMove) {
                el.moveBy(dx, dy, getDiagram());
            }
        } else if (dragged.movePorts) {
            for (JDiagramPort gp : dragged.portsToMove) {
                JDiagramElement el = getDiagram().getElement(gp.getShapeUuid());
                if (el != null) {
                    el.movePortBy(dx, dy, gp, getDiagram());
                }
            }
        } else if (dragged.select) {
            dragged.selectEnd = new Point(mx, my);
            getDiagram().setSelected(
                    getDiagram().getSelectionRectUuids(dragged.selectStart, dragged.selectEnd),
                    e.isControlDown() ? JDiagramSelectionMode.TOGGLE : JDiagramSelectionMode.REPLACE
            );
        }
        mouseX = mx;
        mouseY = my;
        getCanvas().repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int moveDistance;
        if (e.isShiftDown()) {
            moveDistance = 10;
        } else {
            moveDistance = 1;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                getDiagram().moveDiagram(-moveDistance, 0);
                break;
            case KeyEvent.VK_RIGHT:
                getDiagram().moveDiagram(moveDistance, 0);
                break;
            case KeyEvent.VK_UP:
                getDiagram().moveDiagram(0, -moveDistance);
                break;
            case KeyEvent.VK_DOWN:
                getDiagram().moveDiagram(0, moveDistance);
                break;
            case KeyEvent.VK_DELETE: {
                getDiagram().removeSelected();
                break;
            }
        }
    }

}
