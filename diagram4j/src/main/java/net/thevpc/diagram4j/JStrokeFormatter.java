/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import java.awt.Stroke;

/**
 *
 * @author vpc
 */
public interface JStrokeFormatter {
    Stroke parseStroke(String stroke);
    String formatStroke(Stroke stroke);
}
