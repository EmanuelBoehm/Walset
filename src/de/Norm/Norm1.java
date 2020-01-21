package de.Norm;
import de.Knot;
import de.NormInter;
public class Norm1 implements NormInter {
    @Override
    public double dist(Knot a, Knot b) {
        return Math.abs(a.getBlue() - b.getBlue()) + Math.abs(a.getGreen() - b.getGreen()) + Math.abs(a.getRed() - b.getRed());
    }
}
