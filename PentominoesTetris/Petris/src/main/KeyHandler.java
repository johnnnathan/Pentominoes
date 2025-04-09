package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

public static boolean nPressed,mPressed,upPressed, downPressed, leftPressed, rightPressed, spacePressed, pausePressed, enterPressed, escapePressed, restartPressed,hPressed = false,useBot=false, Lpressed;



    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_M){
            if(GamePanelPerfectSequence.moveInterval==40){
                GamePanelPerfectSequence.moveInterval=60;
            } else if (GamePanelPerfectSequence.moveInterval==60) {
                GamePanelPerfectSequence.moveInterval=80;
            }
            else if (GamePanelPerfectSequence.moveInterval==80) {
                    GamePanelPerfectSequence.moveInterval=10;

            } else if (GamePanelPerfectSequence.moveInterval==10) {
                GamePanelPerfectSequence.moveInterval=40;
            }
        }
        if(code == KeyEvent.VK_N){
            nPressed=!nPressed;

        }
        if(code == KeyEvent.VK_L){
            Lpressed = !Lpressed;
        }
        if(code == KeyEvent.VK_H){
            hPressed = !hPressed;
        }
        if(code == KeyEvent.VK_B){
            useBot = !useBot;
        }

        if(code == KeyEvent.VK_UP||code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN||code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT||code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT||code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(!GamePanel.gameOver) {
                pausePressed = !pausePressed;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            escapePressed = true;
        }
        if(code == KeyEvent.VK_R){
            restartPressed = true;
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
