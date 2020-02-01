package de;

import de.Norm.Norm2;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String path = "/home/emi/pic/wallpaper/";
        PicScanner scn = new PicScanner(path + setup().get(1), 80);
        Graph graph = new Graph(new Norm2());
        graph.add(scn.getKnotList());
        graph.disgraph(new Norm2());
        graph.merge(2);
        System.out.println("best colors: " + graph.getMax(10));
        drawToScreen(graph.maxCol, scn.getImg());

    }

    private static void drawToScreen(ArrayList<Color> list, BufferedImage img){
        Frame frame = new Frame();
        frame.pan.col = list;
        frame.pan.img = img;
    }

    private static ArrayList<String> setup(){
        var a = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/de/path.txt")));
            br.lines().forEach(a::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }

}
