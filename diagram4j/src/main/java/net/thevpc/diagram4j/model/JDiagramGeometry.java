/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.model;

import java.io.Serializable;

/**
 *
 * @author vpc
 */
public interface JDiagramGeometry extends Serializable{

    double distanceTo(int x, int y, JDiagram diagram);
    String getUuid();

}
