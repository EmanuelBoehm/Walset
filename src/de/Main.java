package de;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String path = "/home/emi/files/pic/wallpaper/";
        PicScanner scn = new PicScanner("src/de/" + setup().get(3));

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
