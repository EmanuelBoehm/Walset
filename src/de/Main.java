package de;

import de.Norm.Norm2;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        String path = "/home/emi/pic/wallpaper/";
        PicScanner scn = new PicScanner(path + setup().get(1), 80);
        Graph graph = new Graph(new Norm2());
        graph.add(scn.getKnotList());
        graph.disgraph(new Norm2());
        graph.merge(1);
        //System.out.println("best colors: " + graph.getMax(10));
        //drawToScreen(graph.maxCol, scn.getImg());
        print(graph.getMax(18));


    }
    private static String decToHexa(int n) {
        // char array to store hexadecimal number
        char[] hexaDeciNum = new char[100];

        // counter for hexadecimal number array
        int i = 0;
        while(n!=0)
        {
            // temporary variable to store remainder
            int temp  = 0;

            // storing remainder in temp variable.
            temp = n % 16;

            // check if temp < 10
            if(temp < 10)
            {
                hexaDeciNum[i] = (char)(temp + 48);
                i++;
            }
            else
            {
                hexaDeciNum[i] = (char)(temp + 55);
                i++;
            }

            n = n/16;
        }

        // printing hexadecimal number array in reverse order
        StringBuilder str = new StringBuilder();
        for(int j=i-1; j>=0; j--) {
            str.append(hexaDeciNum[j]);
        }
        return str.toString();
    }
    private static void drawToScreen(ArrayList<Color> list, BufferedImage img){
        Frame frame = new Frame();
        frame.pan.col = list;
        frame.pan.img = img;
    }

    private static ArrayList<String> setup(){
        var a = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/home/emi/.config/path.txt")));
            br.lines().forEach(a::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }
    private static void print(ArrayList<String> colorList){
        int count = 0;
        for(String str :colorList){
            StringTokenizer tok = new StringTokenizer(str,",");
            while (tok.hasMoreElements()){
                StringTokenizer tok2 = new StringTokenizer(tok.nextToken());
                String col = "";
                while (tok2.hasMoreElements()) {
                    col += decToHexa(Integer.parseInt(tok2.nextToken()));
                }
                System.out.println("color" + ++count + ": #" + col);
            }
        }
    }

}
