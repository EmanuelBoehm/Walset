package de;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PicScanner {
    private BufferedImage img;
    private ArrayList<Knot> knotList;

    public PicScanner(String path, int precision){
        try {
            this.img = ImageIO.read(new File(path));
            setValues(precision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValues(int precision){
        ArrayList<Knot> knotList = new ArrayList<>();
        for(int x = 0; x < this.img.getWidth(); x += precision){
            for(int y = 0; y < this.img.getHeight(); y += precision){
                knotList.add(new Knot((x+1)*this.img.getHeight()+y,img.getRGB(x,y)));
            }
        }
        this.knotList = knotList;


    }

    public ArrayList<Knot> getKnotList() {
        return knotList;
    }

    public BufferedImage getImg() {
        return img;
    }
}
