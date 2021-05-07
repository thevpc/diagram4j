/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import net.thevpc.diagram4j.model.JDiagramConfig;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import net.thevpc.diagram4j.actions.JDiagramSelectNodeAction;
import net.thevpc.diagram4j.model.JDiagram;

/**
 *
 * @author vpc
 */
public class AbstractDiagramEditorAction implements JDiagramEditorAction {

    private JDiagramCanvas canvas;

    public AbstractDiagramEditorAction() {
    }

    public void setCanvas(JDiagramCanvas canvas) {
        this.canvas = canvas;
    }

    public JDiagramConfig getConfigSnapshot() {
        JDiagramConfig cc = getConfig();
        cc = cc.copy();
        JPaintFormatter pf = getCanvas().getRenderManager().getPaintFormatter();
        if (pf != null) {
            //if the file coloris a function it will be evaluated here
            // agood example is 'random' color
            cc.setFillColor(pf.formatPaint(pf.parsePaint(cc.getFillColor())));
            cc.setLineColor(pf.formatPaint(pf.parsePaint(cc.getFillColor())));
        }
        JStrokeFormatter sf = getCanvas().getRenderManager().getStrokeFormatter();
        if (sf != null) {
            //if the file coloris a function it will be evaluated here
            // agood example is 'random' color
            cc.setLineStroke(sf.formatStroke(sf.parseStroke(cc.getLineColor())));
        }
        return cc;
    }

    public JDiagramConfig getConfig() {
        return canvas.getDiagram().getConfig();
    }

    public JDiagram getDiagram() {
        return canvas.getDiagram();
    }

    public JDiagramCanvas getCanvas() {
        return canvas;
    }

    @Override
    public void dispose() {
    }

    public void end() {
        //always return to select mode!
        canvas.setAction(new JDiagramSelectNodeAction());
        this.canvas = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
