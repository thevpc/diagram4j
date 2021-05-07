/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.strokes;

import java.awt.Shape;
import java.awt.Stroke;

/**
 * This Stroke implementation does nothing. Its createStrokedShape() method
 * returns an unmodified shape. Thus, drawing a shape with this Stroke is the
 * same as filling that shape!
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/CustomStrokes.htm
 */

public class NullStroke implements Stroke {
  public Shape createStrokedShape(Shape s) {
    return s;
  }
}