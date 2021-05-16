package net.thevpc.diagram4j;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.thevpc.diagram4j.model.JDiagramModel;
import net.thevpc.diagram4j.model.JDiagramGrid;
import net.thevpc.diagram4j.render.JDiagramRenderManager;
import net.thevpc.diagram4j.model.shapes.TextShape;
import net.thevpc.diagram4j.model.JDiagram;
import net.thevpc.diagram4j.model.JDiagramGeometry;
import net.thevpc.diagram4j.model.JDiagramListener;

public class JDiagramCanvas extends JPanel
        implements
        ComponentListener {

    private JDiagramGrid grid;

    private JDiagram diagram;

    private JDiagramRenderManager renderManager = new JDiagramRenderManager();
    private JDiagramEditorAction action;
    private EditInfo editInfo = new EditInfo(this);
    private List<JDiagramListener> changeListeners = new ArrayList<>();

    public void addDiagramChangeListener(JDiagramListener changeListener) {
        changeListeners.add(changeListener);
    }

    private static class EditInfo {

        private TextShape currentText;
        private Runnable run;
        private CanvasTextFieldEditor textEditor = new CanvasTextFieldEditor();
        private JPanel parentPanel;
        final FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                endEdit();
            }
        };

        public EditInfo(JPanel parentPanel) {
            this.parentPanel = parentPanel;
            textEditor.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE
                            || e.getKeyCode() == KeyEvent.VK_TAB
                            || e.getKeyCode() == KeyEvent.VK_ENTER) {
                        endEdit();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        public boolean isEditing() {
            return currentText != null;
        }

        public void startEdit(TextShape aa, Runnable run) {
            if (currentText != null) {
                currentText.setEditing(false);
                parentPanel.remove(textEditor);
            }
            this.run = run;
            currentText = aa;
            parentPanel.add(textEditor);
            currentText.setEditing(true);
            textEditor.setText(aa.getText());
            textEditor.setLocation(new Point(aa.getX1(), aa.getY1()));
            textEditor.addFocusListener(focusListener);
            textEditor.requestFocusInWindow();
            textEditor.selectAll();
            ((JDiagramCanvas) parentPanel).updateView();
        }

        public void endEdit() {
            if (currentText != null) {
                textEditor.removeFocusListener(focusListener);
                JDiagram diagram = ((JDiagramCanvas) parentPanel).getDiagram();
                diagram.setElementText(currentText, textEditor.getText());
                currentText.setEditing(false);
                currentText = null;
                parentPanel.remove(textEditor);
                if (run != null) {
                    run.run();
                }
            }
            ((JDiagramCanvas) parentPanel).updateView();
        }
    }

    public JDiagramCanvas() {
        this.setLayout(null);
        diagram = new JDiagram(new JDiagramModel());
        diagram.addDiagramChangeListener(new JDiagramListener() {
            @Override
            public void geometryAdded(JDiagram diagram, JDiagramGeometry element) {
                updateView();
                fireGeometryAdded(diagram, element);
            }

            @Override
            public void geometryRemoved(JDiagram diagram, JDiagramGeometry element) {
                updateView();
                fireGeometryRemoved(diagram, element);
            }

            @Override
            public void selectionChanged(JDiagram diagram, Set<String> before, Set<String> after) {
                updateView();
                fireSelectionChanged(diagram, before, after);
            }

            @Override
            public void propertyChanged(JDiagram diagram, JDiagramGeometry geometry, String property, Object oldValue, Object newValue) {
                if (geometry == null
                        && (property.equals("gridX") || property.equals("gridY"))) {
                    int gx = diagram.getGridX();
                    int gy = diagram.getGridY();
                    int tx = grid.getGridX();
                    int ty = grid.getGridY();
                    if (tx != gx || ty != gy) {
                        grid.setGridX(gx);
                        grid.setGridY(gy);
                        grid.rebuild();
                    }
                }
                updateView();

                JDiagramCanvas.this.firePropertyChanged(diagram, geometry, property, oldValue, newValue);
            }
        }
        );
        grid = new JDiagramGrid(getSize(), diagram.getGridX(), diagram.getGridY());
        diagram.setGridVisible(true);

        addComponentListener(this);

        setFocusable(true);
        requestFocus();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocus();
                if (action != null) {
                    e = transformEvent(e);
                    action.mouseClicked(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (action != null) {
                    e = transformEvent(e);
                    action.mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (action != null) {
                    e = transformEvent(e);
                    action.mouseReleased(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (action != null) {
                    e = transformEvent(e);
                    action.mouseEntered(e);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (action != null) {
                    e = transformEvent(e);
                    action.mouseExited(e);
                }
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (action != null) {
                    action.keyTyped(e);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (action != null) {
                    action.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (action != null) {
                    action.keyReleased(e);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (action != null) {
                    e = transformEvent(e);
                    action.mouseDragged(e);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                e = transformEvent(e);
                JDiagram.GraphElementAndPort ep = diagram.findElementAndPort(e.getX(), e.getY());
                if (ep == null) {
                    diagram.setHoverElementUuid(null);
                    diagram.setHoverPortUuid(null);
                } else if (ep.getPort() == null) {
                    diagram.setHoverElementUuid(ep.getElem().getUuid());
                    diagram.setHoverPortUuid(null);
                } else {
                    diagram.setHoverElementUuid(ep.getElem().getUuid());
                    diagram.setHoverPortUuid(ep.getPort().getUuid());
                }
                updateView();
                if (action != null) {
                    action.mouseMoved(e);
                }

            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                getDiagram().setZoomPointX(e.getX());
                getDiagram().setZoomPointY(e.getY());
                if (e.isControlDown()) {
                    if (e.getPreciseWheelRotation() < 0) {
                        getDiagram().zoomIn();
                    } else {
                        getDiagram().zoomOut();
                    }
                }
            }
        });
    }

    public MouseEvent transformEvent(MouseEvent p) {
        Point p2 = transformPoint(p.getPoint());
        return new MouseEvent(p.getComponent(), p.getID(), p.getWhen(), p.getModifiers(), p2.x, p2.y, p.getClickCount(), p.isPopupTrigger());
    }

    public Point transformPoint(Point p) {
        AffineTransform at = new AffineTransform();
        at.translate(getDiagram().getZoomPointX(), getDiagram().getZoomPointY());
        at.scale(getDiagram().getZoom(), getDiagram().getZoom());
        at.translate(-getDiagram().getZoomPointX(), -getDiagram().getZoomPointY());
        try {
            at.invert();
        } catch (NoninvertibleTransformException ex) {
            throw new IllegalArgumentException(ex);
        }
        Point2D.Double d = new Point2D.Double(0, 0);
        at.transform(new Point2D.Double(p.getX(), p.getY()), d);
        return new Point((int) d.x, (int) d.y);
    }

    public boolean isEditing() {
        return editInfo.isEditing();
    }

    public void startEdit(TextShape aa, Runnable run) {
        editInfo.startEdit(aa, run);
    }

    public void endEdit() {
        editInfo.endEdit();
    }

    public JDiagram getDiagram() {
        return diagram;
    }

    public void setDiagram(JDiagramModel diagram) {
        if (diagram == null) {
            diagram = new JDiagramModel();
        }
        grid.setGridX(diagram.getGridX());
        grid.setGridY(diagram.getGridY());
        this.diagram.setModel(diagram);
        updateView();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = g2.getTransform();

        at.translate(getDiagram().getZoomPointX(), getDiagram().getZoomPointY());
        at.scale(getDiagram().getZoom(), getDiagram().getZoom());
        at.translate(-getDiagram().getZoomPointX(), -getDiagram().getZoomPointY());
        g2.setTransform(at);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (isFocusOwner()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.ORANGE);
            g.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
        }
        if (grid != null && diagram.isGridVisible()) {
            grid.draw(g2);
        }

        if (diagram != null) {
            renderManager.render(diagram, g2);
        }
        if (action != null) {
            renderManager.render(action, diagram, g2);
        }

    }

    public void createNewDiagram() {
        setDiagram(new JDiagramModel());
    }

    public void enableGrid(boolean drawGrid) {
        grid.scaleGrid(getSize());
        diagram.setGridVisible(drawGrid);
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        Object eSource = e.getSource();
        if (eSource == this && diagram.isGridVisible()) {
            grid.scaleGrid(getSize());
            updateView();
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    public void uninstall() {
        //
    }

    public JDiagramEditorAction getAction() {
        return action;
    }

    public boolean isAction(Class<? extends JDiagramEditorAction> actionType) {
        if (actionType == null) {
            return action == null;
        }
        return actionType.isInstance(action);
    }

    public void setAction(Class<? extends JDiagramEditorAction> actionType) {
        JDiagramEditorAction action = null;
        if (actionType != null) {
            try {
                action = actionType.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        setAction(action);
    }

    public void setAction(JDiagramEditorAction action) {
        JDiagramEditorAction old=this.action;
        if(!Objects.equals(this.action,action)) {
            if (this.action != null) {
                this.action.dispose();
            }
            this.action = action;
            if (this.action != null) {
                action.setCanvas(this);
            }
            updateView();
            diagram.firePropertyChanged(diagram, null,"action",old,action);
        }
    }

    public void unsetAction() {
        setAction((JDiagramEditorAction) null);
    }

    public void updateView() {
        repaint();
    }

    public JDiagramRenderManager getRenderManager() {
        return renderManager;
    }

//    protected void fireDiagramChanged(ChangeEvent e) {
//        
//        updateView();
//        for (ChangeListener changeListener : changeListeners) {
//            changeListener.stateChanged(e);
//        }
//    }
    protected void fireGeometryAdded(JDiagram diagram, JDiagramGeometry element) {
        for (JDiagramListener li : changeListeners) {
            li.geometryAdded(diagram, element);
        }
    }

    protected void fireGeometryRemoved(JDiagram diagram, JDiagramGeometry element) {
        for (JDiagramListener li : changeListeners) {
            li.geometryRemoved(diagram, element);
        }
    }

    protected void fireSelectionChanged(JDiagram diagram, Set<String> before, Set<String> after) {
        if (!before.equals(after)) {
            for (JDiagramListener li : changeListeners) {
                li.selectionChanged(diagram, before, after);
            }
        }
    }

    protected void firePropertyChanged(JDiagram diagram, JDiagramGeometry geometry, String property, Object oldValue, Object newValue) {
        for (JDiagramListener li : changeListeners) {
            li.propertyChanged(diagram, geometry, property, oldValue, newValue);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (editInfo != null && editInfo.textEditor != null) {
            editInfo.textEditor.updateUI();
        }
    }

}
