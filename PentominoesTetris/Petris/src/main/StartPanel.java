package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartPanel extends JFrame{
    private int bgWidth, bgHeight, bgX, bgY;
    public static java.awt.TextField playerName = new TextField();
    public static String playerNameString = "";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    BufferedImage background = null;
    public StartPanel()  {
        loadBackground();
    }


    private void loadBackground () {
        bgWidth = 1000;
        bgHeight = 214;
        bgX = 140;
        bgY = 100;
    }
    public void draw (Graphics g) {
        try {
            background = ImageIO.read(new File("src/main/Resources/Petris.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerName.paint(g);
        playerName.setFont(new Font("Tetris 2", Font.PLAIN, 35));
        playerName.setBounds(475, HEIGHT / 2 + 230, 330, 40);
        playerName.setBackground(Color.BLACK);
        playerName.setForeground(Color.WHITE);
        playerName.setVisible(true);
        playerName.setEditable(true);
        g.setColor(Color.WHITE);
        g.drawRect(475, HEIGHT / 2 + 230, 330, 40);



        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);
        g.setFont(new Font("Tetris 2", Font.PLAIN, 35));
        g.setColor(Color.WHITE);

        g.drawString("A Game by BCS24", 475, HEIGHT / 2 + 50);
        g.drawString("Enter your name below ", 475, HEIGHT / 2 + 200);
        g.drawString("Press ENTER to Start", 475, HEIGHT / 2 + 100);
        g.drawString("Press ESCAPE to Quit", 475, HEIGHT / 2 + 150);

        playerName.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerNameString = playerName.getText();
                KeyHandler.enterPressed = true;

            }
        });



    }
}
