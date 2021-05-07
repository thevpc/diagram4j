/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.render.strokes;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

/**
 * This Stroke implementation strokes the shape using a thin line, and also
 * displays the end points and Bezier curve control points of all the line and
 * curve segments that make up the shape. The radius argument to the constructor
 * specifies the size of the control point markers. Note the use of PathIterator
 * to break the shape down into its segments, and of GeneralPath to build up the
 * stroked shape.
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/CustomStrokes.htm
 */
public class ControlPointsStroke implements Stroke {
  float radius; // how big the control point markers should be

  public ControlPointsStroke(float radius) {
    this.radius = radius;
  }

  public Shape createStrokedShape(Shape shape) {
    // Start off by stroking the shape with a thin line. Store the
    // resulting shape in a GeneralPath object so we can add to it.
    GeneralPath strokedShape = new GeneralPath(new BasicStroke(1.0f)
        .createStrokedShape(shape));

    // Use a PathIterator object to iterate through each of the line and
    // curve segments of the shape. For each one, mark the endpoint and
    // control points (if any) by adding a rectangle to the GeneralPath
    float[] coords = new float[6];
    for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
        .next()) {
      int type = i.currentSegment(coords);
      Shape s = null, s2 = null, s3 = null;
      switch (type) {
      case PathIterator.SEG_CUBICTO:
        markPoint(strokedShape, coords[4], coords[5]); // falls through
      case PathIterator.SEG_QUADTO:
        markPoint(strokedShape, coords[2], coords[3]); // falls through
      case PathIterator.SEG_MOVETO:
      case PathIterator.SEG_LINETO:
        markPoint(strokedShape, coords[0], coords[1]); // falls through
      case PathIterator.SEG_CLOSE:
        break;
      }
    }

    return strokedShape;
  }

  /** Add a small square centered at (x,y) to the specified path */
  void markPoint(GeneralPath path, float x, float y) {
    path.moveTo(x - radius, y - radius); // Begin a new sub-path
    path.lineTo(x + radius, y - radius); // Add a line segment to it
    path.lineTo(x + radius, y + radius); // Add a second line segment
    path.lineTo(x - radius, y + radius); // And a third
    path.closePath(); // Go back to last moveTo position
  }
}