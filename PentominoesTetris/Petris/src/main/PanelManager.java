package main;

import javax.swing.*;
import java.awt.*;


import java.io.FileNotFoundException;
import java.io.IOException;

public class PanelManager extends JPanel{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 120;
    Thread gameThread;
    GamePanel pm;
    PausePanel pausePanel;
    StartPanel startPanel;
    GameOverPanel gameOverPanel;
    GamePanelPerfectSequence perfectSequence;

    public PanelManager() throws FileNotFoundException {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        perfectSequence = new GamePanelPerfectSequence();
        pm= new GamePanel();
        pausePanel = new PausePanel();
        startPanel = new StartPanel();
        gameOverPanel = new GameOverPanel();
    }

    public void launchGame(){

        gameThread = new Thread(() -> {
            try {
                run();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        gameThread.start();
    }



    public void run() throws IOException, InterruptedException {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long now;
        while(gameThread != null) {
            now = System.nanoTime();
            delta += (now - lastTime) / drawInterval;
            lastTime = now;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() throws IOException, InterruptedException {
        if(!KeyHandler.pausePressed&&KeyHandler.enterPressed&&!GamePanel.gameOver&&!KeyHandler.Lpressed) {
            pm.update();
        }
        else if (!KeyHandler.pausePressed&&KeyHandler.enterPressed&&!GamePanel.gameOver&&KeyHandler.Lpressed){
            perfectSequence.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(!KeyHandler.enterPressed) {
            startPanel.draw(g2);
            if (KeyHandler.escapePressed) {
                System.exit(0);
            }
        }
        else if(KeyHandler.pausePressed&&!GamePanel.gameOver) {
            pausePanel.draw(g2);
            if (KeyHandler.escapePressed) {
                System.exit(0);
            }

        }
        else if(GamePanel.gameOver) {
            gameOverPanel.draw(g2);
            if (KeyHandler.escapePressed) {

                System.exit(0);
            }
            if(KeyHandler.restartPressed){
                GamePanel.gameOver = false;
                GamePanel.staticBlocks.clear();
                KeyHandler.restartPressed = false;
                try {
                    GamePanel.score=0;
                    pm= new GamePanel();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                pm.draw(g2);
            }
        }
        else if(!KeyHandler.Lpressed){
            Main.window.remove(StartPanel.playerName);
            pm.draw(g2);
        }
        else {
            perfectSequence.draw(g2);
        }
    }
}
