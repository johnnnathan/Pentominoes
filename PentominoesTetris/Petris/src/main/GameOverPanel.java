package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverPanel {
    private int bgWidth, bgHeight, bgX, bgY;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    BufferedImage background = null;

    public GameOverPanel()   {
        loadBackground();
    }


    private void loadBackground() {
        bgWidth = 1100;
        bgHeight = 161;
        bgX = 1280/2-1100/2;
        bgY = 100;
    }

    public void draw(Graphics g) {
        try {
            background = ImageIO.read(new File("src/main/Resources/GameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH, HEIGHT);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);
        g.setFont(new Font("Tetris 2", Font.PLAIN, 35));
        g.setColor(Color.WHITE);
        g.drawString("Press R to Restart", 498, HEIGHT / 2 + 50);
        g.drawString("Press ESCAPE to Quit", 498, HEIGHT / 2 + 100);

    }
}
