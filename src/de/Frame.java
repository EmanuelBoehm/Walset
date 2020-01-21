package de;

import javax.swing.*;

public class Frame extends JFrame {
    public Panel pan;
    public Frame(){
        super("test Frame");
        pan = new Panel();
        config();
    }
    public void config(){
        setContentPane(pan);
        setSize(1200,800);
        setVisible(true);
    }

}
