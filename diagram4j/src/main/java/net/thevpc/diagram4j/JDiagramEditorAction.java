/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author vpc
 */
public interface JDiagramEditorAction extends MouseListener, KeyListener, MouseMotionListener {

    void setCanvas(JDiagramCanvas canvas);

    void dispose();
}
