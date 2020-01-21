package de;

import java.awt.*;

public class Knot{
    //each knot does have a unique ID
    public final int ID;
    public Color knot;
    public int power = 1;
    public Knot(int id, Color col){
        this.knot = col;
        ID = id;
    }
    public Knot(int id, int rgb){
        ID = id;
        this.knot = new Color(rgb);
    }
    public double sum(){
        return knot.getBlue()+knot.getGreen()+knot.getRed();
    }

    public int getRed(){
        return knot.getRed();
    }
    public int getGreen(){
        return knot.getGreen();
    }
    public int getBlue() {
        return knot.getBlue();
    }
    public String getRGB(){
        StringBuilder str = new StringBuilder();
        return str.append(knot.getRed()).append(" ").append(knot.getGreen()).append(" ").append(knot.getBlue()).toString();

    }
}
