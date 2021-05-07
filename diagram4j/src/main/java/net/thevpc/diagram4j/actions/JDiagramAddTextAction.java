/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.actions;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import net.thevpc.diagram4j.AbstractDiagramEditorAction;
import net.thevpc.diagram4j.model.shapes.TextShape;
import net.thevpc.diagram4j.model.JDiagramElement;

/**
 *
 * @author vpc
 */
public class JDiagramAddTextAction extends AbstractDiagramEditorAction {

    private TextShape shape;


    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if(getCanvas().isEditing()){
                return;
            }
            JDiagramElement ge = getCanvas().getDiagram().findElement(e.getX(), e.getY());
            if (ge instanceof TextShape) {
                shape = (TextShape) ge;
            } else {
                shape = new TextShape(e.getX(), e.getY(), "text");
                shape.configure(getConfigSnapshot());
                getCanvas().getDiagram().addElement(shape);
            }
            getCanvas().startEdit(shape, () -> reset());
        }
    }

    public TextShape getShape() {
        return shape;
    }

    public void reset() {
        shape = null;
    }

}
