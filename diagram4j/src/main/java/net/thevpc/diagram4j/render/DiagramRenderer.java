/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render;

import java.awt.Graphics2D;
import net.thevpc.diagram4j.model.JDiagram;

/**
 *
 * @author vpc
 */
public interface DiagramRenderer<T> {

    public void render(T item, Graphics2D g, JDiagram config, JDiagramRenderManager man);

}
