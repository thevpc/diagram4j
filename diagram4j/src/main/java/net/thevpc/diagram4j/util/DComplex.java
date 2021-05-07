/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.diagram4j.util;

/**
 *
 * @author vpc
 */
public class DComplex {

    public final double r;
    public final double i;

    public DComplex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public DComplex plus(DComplex o) {
        return new DComplex(r + o.r, i + o.i);
    }

    public DComplex minus(DComplex o) {
        return new DComplex(r - o.r, i - o.i);
    }

    public DComplex mul(double o) {
        return new DComplex(r * o, i * o);
    }
    
    public DComplex mul(DComplex o) {
        double r1 = r;
        double i1 = i;
        double r2 = o.r;
        double i2 = o.i;
        return new DComplex(r1 * r2 - i1 * i2, r1 * i2 + i1 * r2);
    }

    public double abs() {
        return Math.sqrt(r * r + i * i);
    }

    public DComplex div(DComplex o) {
        double a = r;
        double b = i;
        double c = o.r;
        double d = o.i;
        double c2d2 = c * c + d * d;

        return new DComplex(
                (a * c + b * d) / c2d2,
                (b * c - a * d) / c2d2
        );
    }

    @Override
    public String toString() {
        return "Complex{" + "r=" + r + ", i=" + i + '}';
    }
    
}
