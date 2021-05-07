/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j;

import java.awt.Font;

/**
 *
 * @author vpc
 */
public interface JFontFormatter {
    Font parseFont(String font);
    String formatFont(Font font);
}
