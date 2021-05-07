/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.strokes;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Stroke;


/**
 * This Stroke implementation applies a BasicStroke to a shape twice. If you
 * draw with this Stroke, then instead of outlining the shape, you're outlining
 * the outline of the shape.
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/CustomStrokes.htm
 */

public class DoubleStroke implements Stroke {
  BasicStroke stroke1, stroke2; // the two strokes to use

  public DoubleStroke(float width1, float width2) {
    stroke1 = new BasicStroke(width1); // Constructor arguments specify
    stroke2 = new BasicStroke(width2); // the line widths for the strokes
  }

  public Shape createStrokedShape(Shape s) {
    // Use the first stroke to create an outline of the shape
    Shape outline = stroke1.createStrokedShape(s);
    // Use the second stroke to create an outline of that outline.
    // It is this outline of the outline that will be filled in
    return stroke2.createStrokedShape(outline);
  }
}