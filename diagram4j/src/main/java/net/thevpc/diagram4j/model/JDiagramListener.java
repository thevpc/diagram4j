/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.model;

import java.util.Set;

/**
 *
 * @author vpc
 */
public interface JDiagramListener {
    default void geometryAdded(JDiagram diagram,JDiagramGeometry element){}
    default void geometryRemoved(JDiagram diagram,JDiagramGeometry element){}
    default void selectionChanged(JDiagram diagram,Set<String> before,Set<String> after){}
    default void propertyChanged(JDiagram diagram,JDiagramGeometry geometry,String property, Object oldValue, Object newValue){}
}
