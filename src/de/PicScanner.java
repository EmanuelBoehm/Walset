package de;

import de.Norm.Norm1;
import de.Norm.Norm2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PicScanner {
    private BufferedImage img;
    private ArrayList<Knot> knotList;
    private Graph graph;

    public PicScanner(String path){
        try {
            this.graph = new Graph(new Norm2());
            this.img = ImageIO.read(new File(path));
            setValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValues(){
        knotList = new ArrayList<>();
        int precision = 50;
        for(int x = 0; x < this.img.getWidth(); x += precision){
            for(int y = 0; y < this.img.getHeight(); y += precision){
                knotList.add(new Knot((x+1)*this.img.getHeight()+y,img.getRGB(x,y)));
            }
        }
        graph.add(knotList);
        graph.disgraph(new Norm1());
        graph.merge();
        System.out.println("best colors: " + graph.getMax(10));
        drawToScreen(graph.maxCol);
    }
    private void drawToScreen(ArrayList<Color> list){
        Frame frame = new Frame();
        frame.pan.col = list;
        frame.pan.img = img;
    }

}
