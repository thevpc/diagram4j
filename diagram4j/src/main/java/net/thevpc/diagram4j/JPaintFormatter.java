/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import java.awt.Paint;

/**
 *
 * @author vpc
 */
public interface JPaintFormatter {
    Paint parsePaint(String paint);
    String formatPaint(Paint paint);
}
