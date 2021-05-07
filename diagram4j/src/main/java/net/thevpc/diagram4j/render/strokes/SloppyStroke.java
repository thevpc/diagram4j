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
 * This Stroke implementation randomly perturbs the line and curve segments that
 * make up a Shape, and then strokes that perturbed shape. It uses PathIterator
 * to loop through the Shape and GeneralPath to build up the modified shape.
 * Finally, it uses a BasicStroke to stroke the modified shape. The result is a
 * "sloppy" looking shape.
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/CustomStrokes.htm
 *
 */
public class SloppyStroke implements Stroke {

    BasicStroke stroke;
    BasicStroke stroke2; // the two strokes to use

    float sloppiness;
    double[] random = new double[20];
    float width;

    public SloppyStroke(float width, float sloppiness) {
        this.width = width;
        this.stroke = new BasicStroke(width); // Used to stroke modified shape
        stroke2 = new BasicStroke(5); // the line widths for the strokes
        this.sloppiness = sloppiness; // How sloppy should we be?
        for (int i = 0; i < random.length; i++) {
            random[i] = Math.random();
        }
    }

    public float getSloppiness() {
        return sloppiness;
    }

    public float getWidth() {
        return width;
    }

    public Shape createStrokedShape(Shape shape) {
//        shape = stroke2.createStrokedShape(shape);
        GeneralPath newshape = new GeneralPath(); // Start with an empty shape

        // Iterate through the specified shape, perturb its coordinates, and
        // use them to build up the new shape.
        float[] coords = new float[6];
        for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
                .next()) {
            int type = i.currentSegment(coords);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    perturb(coords, 2);
                    newshape.moveTo(coords[0], coords[1]);
                    break;
                case PathIterator.SEG_LINETO:
                    perturb(coords, 2);
                    newshape.lineTo(coords[0], coords[1]);
                    break;
                case PathIterator.SEG_QUADTO:
                    perturb(coords, 4);
                    newshape.quadTo(coords[0], coords[1], coords[2], coords[3]);
                    break;
                case PathIterator.SEG_CUBICTO:
                    perturb(coords, 6);
                    newshape.curveTo(coords[0], coords[1], coords[2], coords[3],
                            coords[4], coords[5]);
                    break;
                case PathIterator.SEG_CLOSE:
//                    newshape.closePath();
                    break;
            }
        }
        boolean perturb = false;
        for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
                .next()) {
            int type = i.currentSegment(coords);
            switch (type) {
                case PathIterator.SEG_MOVETO:
//                    perturb2(coords, 2);
                    newshape.moveTo(coords[0], coords[1]);
                    break;
                case PathIterator.SEG_LINETO:
                    if (perturb) {
                        perturb2(coords, 2);
                    }
                    newshape.lineTo(coords[0], coords[1]);
                    break;
                case PathIterator.SEG_QUADTO:
                    if (perturb) {
                        perturb2(coords, 4);
                    }
                    newshape.quadTo(coords[0], coords[1], coords[2], coords[3]);
                    break;
                case PathIterator.SEG_CUBICTO:
                    if (perturb) {
                        perturb2(coords, 6);
                    }
                    newshape.curveTo(coords[0], coords[1], coords[2], coords[3],
                            coords[4], coords[5]);
                    break;
                case PathIterator.SEG_CLOSE:
//                    newshape.closePath();
                    break;
            }
            perturb = !perturb;
        }
        Shape outline = stroke.createStrokedShape(newshape);

        // Finally, stroke the perturbed shape and return the result
        return outline;//stroke2.createStrokedShape(outline);
    }

    // Randomly modify the specified number of coordinates, by an amount
    // specified by the sloppiness field.
    void perturb(float[] coords, int numCoords) {
        for (int i = 0; i < numCoords; i++) {
            coords[i] += (float) ((random[i] * 2 - 1.0) * sloppiness);
        }
    }

    void perturb2(float[] coords, int numCoords) {
        for (int i = 0; i < numCoords; i++) {
            coords[i] += (float) ((0.8 * 2 - 1.0) * sloppiness);
        }
    }

}
