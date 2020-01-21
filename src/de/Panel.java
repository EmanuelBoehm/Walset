package de;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Panel extends JPanel {
    public ArrayList<Color> col;
    public BufferedImage img;
    public void paintComponent(Graphics g) {
        int count = 0;

        for(Color c: col){
            g.setColor(c);
            g.fillRect(count,0,count+80,400);
            g.drawImage(img,0,400,800,500,null);
            count +=80;

        }
    }
}
