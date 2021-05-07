/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.util;

import java.awt.Point;

/**
 *
 * @author vpc
 */
public class GeomUtils {

    public static boolean isOnSegment(
            Point test,
            Point segmentStartPoint,
            Point segmentEndPoint
    ) {
        int x = test.x;
        int y = test.y;
        int sx = segmentStartPoint.x;
        int sy = segmentStartPoint.y;
        int ex = segmentEndPoint.x;
        int ey = segmentEndPoint.y;

        if ((x - sx == 0 && x - ey == 0)
                || ((x - sx != 0 && x - ey != 0)
                && (y - sy) / (x - sx) == (y - ey) / (x - ey))) {
            return true;
        }
        return false;

    }

    public static boolean isOnSegment(
            int cx,
            int cy,
            int ax,
            int ay,
            int bx,
            int by
    ) {
        double epsilon = 800;
        double crossproduct = (cy - ay) * (bx - ax) - (cx - ax) * (by - ay);
        double dotproduct = (cx - ax) * (bx - ax) + (cy - ay) * (by - ay);
        double squaredlengthba = (bx - ax) * (bx - ax) + (by - ay) * (by - ay);
        if (Math.abs(crossproduct) > epsilon) {
            return false;
        }

        if (dotproduct < 0) {
            return false;
        }

        if (dotproduct > squaredlengthba) {
            return false;
        }
        return true;
    }

    /**
     * see : https://demonstrations.wolfram.com/DistanceOfAPointToASegment To
     * compute the distance from point p to segment ab (all as complex numbers)
     * compute first z=(p-a)/(b-a). If 0 ≤ Re[z] ≤ 1 then the distance is equal
     * to Abs[Im[z](b-a)]. If not, it is equal to the smallest of the distances
     * from p to a or to b.
     *
     * @param px x to check if it is between a and b
     * @param py y to check if it is between a and b
     * @param ax a x
     * @param ay a y
     * @param bx b x
     * @param by b y
     * @return distance
     */
    public static double distance(
            int px,
            int py,
            int ax,
            int ay,
            int bx,
            int by
    ) {
        DComplex p = new DComplex(px, py);
        DComplex a = new DComplex(ax, ay);
        DComplex b = new DComplex(bx, by);
        // (p-a)/(b-a)
        DComplex z = p.minus(a).div(b.minus(a));
        if (z.r >= 0 && z.r <= 1) {
            double d1 = Math.abs(b.minus(a).mul(z.i).abs());
//            System.out.println("z:" + z + " ; " + d1);
            return d1;
        }
        double d2 = Math.min(distance(px, py, bx, by), distance(px, py, ax, ay));
//        System.out.println("z:" + z + " ; " + d2);
        return d2;
    }

    public static double distance(
            int ax,
            int ay,
            int bx,
            int by
    ) {
        double x = ax - bx;
        double y = ay - by;
        return Math.sqrt(x * x + y * y);
    }
}
