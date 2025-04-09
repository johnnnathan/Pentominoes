package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;


public class PausePanel {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
        BufferedImage background = null;

        private int bgX, bgY, bgWidth, bgHeight;
    public PausePanel() {
        KeyHandler.escapePressed=false;
        loadBackground();
    }

        private void loadBackground () {
        bgWidth = 1000;
        bgHeight = 220;
        bgX = WIDTH/2-bgWidth/2;
        bgY = 100;
    }



        public void draw (Graphics g){
        try{
            background = ImageIO.read(new File("src/main/Resources/Paused.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(Color.BLACK);
        g.drawRect(0,0,WIDTH,HEIGHT);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);
        g.setFont(new Font("Tetris 2", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Press P to Resume", 498, HEIGHT/2+50);
        g.drawString("Press Escape to Quit", 498, HEIGHT/2+100);
    }
 }

