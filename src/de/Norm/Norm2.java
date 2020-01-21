package de.Norm;

import de.Knot;
import de.NormInter;

public class Norm2 implements NormInter {
    @Override
    public double dist(Knot a, Knot b) {
        double x1 = calc(a.getRed()-b.getRed());
        double x2 = calc(a.getGreen()-b.getGreen());
        double x3 = calc(a.getBlue()-b.getBlue());
        return Math.sqrt(x1+x2+x3);
    }
    private double calc(double x){
        return Math.abs(Math.pow(x,2));
    }
}
