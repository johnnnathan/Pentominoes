package main;

import Pentominoes.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class GamePanel extends Component {

    // AREA
        final int WIDTH = 200;
        final int HEIGHT = 600;
        public static int left_x;
        public static int right_x;
        public static int top_y;
        public static int bottom_y;
        public static  ArrayList<String> highScores = new ArrayList<>();
        public static int score = 0;
        static boolean gameOver = false;
        boolean effectCounterOn;
        int effectCounter = 0;
         ArrayList<Integer> effectY = new ArrayList<>();


        // PENTOMINO
        static Pentomino currentPentomino;
        Pentomino nextPentomino;
        int NEXT_PENTOMINO_X;
        int NEXT_PENTOMINO_Y;
        public static ArrayList<Block> staticBlocks = new ArrayList<>();
        int PENTOMINO_START_X;
        int PENTOMINO_START_Y;

        public static int dropInterval = 120;










    public GamePanel() throws FileNotFoundException {


            left_x=(PanelManager.WIDTH/2)-WIDTH/2;
            right_x=left_x+WIDTH;
            top_y=40;
            bottom_y=top_y+HEIGHT;
            currentPentomino = randomPentomino();
            nextPentomino = randomPentomino();
            PENTOMINO_START_X = left_x  + WIDTH/2 - Block.SIZE/2;
            if(currentPentomino instanceof Pentomino_I||currentPentomino instanceof Pentomino_V) {
                PENTOMINO_START_Y = top_y + Block.SIZE*2;
            }
            else {
                PENTOMINO_START_Y = top_y + Block.SIZE;
            }


            NEXT_PENTOMINO_X = left_x - 250 + WIDTH/2 - Block.SIZE/2;
            if(nextPentomino instanceof Pentomino_I||nextPentomino instanceof Pentomino_V) {
                NEXT_PENTOMINO_Y = top_y + Block.SIZE*2;
            }
            else {
                NEXT_PENTOMINO_Y = top_y + Block.SIZE;
            }

            currentPentomino.setXY(PENTOMINO_START_X, PENTOMINO_START_Y);
            nextPentomino.setXY(NEXT_PENTOMINO_X, NEXT_PENTOMINO_Y);
            File file = new File("src/main/Resources/Highscore.csv");
            Scanner reader = new Scanner(file);
            String highScore;
            highScores.clear();
            while (reader.hasNextLine()) {
                highScore = reader.nextLine();
                highScores.add(highScore);
            }
            reader.close();


    }
    public static ArrayList<Block> getStaticBlocks() {
        return staticBlocks;
    }


    private Pentomino randomPentomino(){
            Pentomino p;
            int r= (int)(Math.random()*12);
            p = switch (r) {
                case 0 -> new Pentomino_L();
                case 1 -> new Pentomino_N();
                case 2 -> new Pentomino_U();
                case 3 -> new Pentomino_V();
                case 4 -> new Pentomino_Z();
                case 5 -> new Pentomino_W();
                case 6 -> new Pentomino_P();
                case 7 -> new Pentomino_X();
                case 8 -> new Pentomino_I();
                case 9 -> new Pentomino_F();
                case 10 -> new Pentomino_T();
                case 11 -> new Pentomino_Y();
                default -> null;
            };
            return p;
        }
        private int counter = 0;
        public void update() throws IOException {
            if(KeyHandler.useBot){
                TetrisBot.playGame();
            }
            if(counter>=dropInterval/2) {
                if (KeyHandler.nPressed) {
                    int r = (int) (Math.random() * 12);
                    switch (r) {
                        case 0 -> KeyHandler.leftPressed = true;
                        case 1 -> KeyHandler.leftPressed = true;
                        case 2 -> KeyHandler.leftPressed = true;
                        case 3 -> KeyHandler.leftPressed = true;
                        case 4 -> KeyHandler.leftPressed = true;
                        case 5 -> KeyHandler.rightPressed = true;
                        case 6 -> KeyHandler.rightPressed = true;
                        case 7 -> KeyHandler.rightPressed = true;
                        case 8 -> KeyHandler.leftPressed = true;
                        case 9 -> KeyHandler.leftPressed = true;
                        case 10 -> KeyHandler.upPressed = true;
                        case 11 -> KeyHandler.spacePressed = true;
                    }
                    counter = 0;
                }
            }
            counter++;


            if(KeyHandler.escapePressed){
                gameOver = true;
                KeyHandler.escapePressed = false;
            }

            if(!currentPentomino.isAlive) {
                staticBlocks.add(currentPentomino.b[0]);
                staticBlocks.add(currentPentomino.b[1]);
                staticBlocks.add(currentPentomino.b[2]);
                staticBlocks.add(currentPentomino.b[3]);
                staticBlocks.add(currentPentomino.b[4]);


                currentPentomino.isDying = false;

                currentPentomino = nextPentomino;
                PENTOMINO_START_X = left_x  + WIDTH/2 - Block.SIZE/2;
                if(currentPentomino instanceof Pentomino_I||currentPentomino instanceof Pentomino_V) {
                    PENTOMINO_START_Y = top_y + Block.SIZE*2;
                }
                else {
                    PENTOMINO_START_Y = top_y + Block.SIZE;
                }
                currentPentomino.setXY(PENTOMINO_START_X, PENTOMINO_START_Y);

                nextPentomino = randomPentomino();



                NEXT_PENTOMINO_X = left_x - 250 + WIDTH/2 - Block.SIZE/2;
                if(nextPentomino instanceof Pentomino_I||nextPentomino instanceof Pentomino_V) {
                    NEXT_PENTOMINO_Y = top_y + Block.SIZE*2;
                }
                else {
                    NEXT_PENTOMINO_Y = top_y + Block.SIZE;
                }
                nextPentomino.setXY(NEXT_PENTOMINO_X, NEXT_PENTOMINO_Y);
                checkDelete();
                for(Block staticBlock : staticBlocks) {
                    for (int i = 0 ; i < 5 ; i++){
                        if (staticBlock.x == currentPentomino.b[i].x && staticBlock.y == currentPentomino.b[i].y) {
                            gameOver = true;
                            break;
                        }
                    }
                }
                if(gameOver){
                    new WriteScore();
                }
            }
            else {
                currentPentomino.update();
            }
        }
        private void checkDelete(){
            int x=left_x;
            int y=top_y;
            int blockCount = 0;
            while (x<right_x && y<bottom_y){

                for (Block staticBlock : staticBlocks) {
                    if (staticBlock.x == x && staticBlock.y == y) {
                        blockCount++;
                    }
                }
                x+=Block.SIZE;
                if(x==right_x) {
                    if(blockCount==5) {
                        deleteLine(y);
                    }
                    blockCount=0;
                    x=left_x;
                    y+=Block.SIZE;
                }

            }
        }

    private void deleteLine(int y) {
        effectCounterOn = true;
        effectY.add(y);

        for (int i = 0; i < staticBlocks.size(); i++) {
            if (staticBlocks.get(i).y == y) {
                staticBlocks.remove(i);
                i--;
            }
        }
        staticBlocks.sort((b1, b2) -> Integer.compare(b2.y, b1.y));

        for (int i = 0; i < staticBlocks.size(); i++) {
            if (staticBlocks.get(i).y < y) {
                int staticX = staticBlocks.get(i).x;
                int staticY = staticBlocks.get(i).y;

                moveBlockDown(staticBlocks, i, staticX, staticY, bottom_y);
            }
        }


        score++;
    }

    private static void moveBlockDown(ArrayList<Block> staticBlocks, int index, int x, int y, int bottomBoundary) {
        boolean collision = false;

        while (!collision && y + Block.SIZE != bottomBoundary) {
            for (Block block : staticBlocks) {
                if (block.x == x && block.y == y + Block.SIZE) {
                    collision = true;
                    break;
                }
            }

            if (!collision) {
                y += Block.SIZE;
            }
        }

        staticBlocks.get(index).y = y;
    }

    public static Pentomino getCurrPento(){
        return currentPentomino;
    }


    public void draw(Graphics2D g2) {
            g2.setColor(Color.ORANGE);
            g2.setStroke(new BasicStroke(4f));
            g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);
            for (int i = left_x; i<right_x; i+=Block.SIZE){
                for (int j = top_y; j<bottom_y; j+=Block.SIZE){
                    g2.setColor(new Color(35, 34, 34));
                    g2.drawRect(i,j,Block.SIZE,Block.SIZE);
                }
            }


           int x = left_x - 250;
           int y = top_y ;
           g2.setColor(Color.ORANGE);
           g2.drawRect(x-4,y-4, 208, 208);
           g2.setFont(new Font("Tetris 2", Font.PLAIN, 20));
           g2.drawString("NEXT PENTOMINO", x+10, y-10);
            if(!KeyHandler.hPressed) {
                g2.drawString("How to play", 100, 300);
                g2.setFont(new Font("Tetris 2", Font.PLAIN, 19));
                g2.drawString("Press the left and right arrow keys or A/D to move the pentomino.", 10, 330);
                g2.drawString("Press the up arrow key or W to rotate the pentomino.", 10, 360);
                g2.drawString("Press the down arrow key or S to soft drop the pentomino.", 10, 390);
                g2.drawString("Press the Space Bar to hard drop the pentomino.", 10, 420);
                g2.drawString("Press the P  to pause the game.", 10, 450);
                g2.drawString("Every line you will clear will earn you 1 point.", 10, 480);
                g2.drawString("Press the Escape key to abandon this run.", 10, 510);
                g2.drawString("To see the optimal sequence press L.",10,540);
                g2.drawString("Press H to hide this menu.",10,570);
            }

        g2.setFont(new Font("Tetris 2", Font.PLAIN, 40));
        g2.drawString("Score : " + score, right_x + 50, top_y);

            g2.drawRect(right_x+100,top_y+50, 300, 550);
        g2.setFont(new Font("Tetris 2", Font.PLAIN, 40));
            g2.drawString("High Scores", right_x+150, top_y+100);
            g2.setColor(new Color(220, 182, 51));
            if(!highScores.isEmpty()) {
                for (int i = 0; i < highScores.size(); i++) {
                    String[] split = highScores.get(i).split(",");
                    g2.drawString(split[0] + " : " + split[1], right_x + 120, top_y + 180 + i * 50);
                }
            }




           if(currentPentomino!=null) {
               currentPentomino.draw(g2);
           }

           nextPentomino.draw(g2);
        for (Block staticBlock : staticBlocks) {
            staticBlock.draw(g2);
        }
        if(effectCounterOn){
            effectCounter++;
            g2.setColor(new Color(255, 0, 0));
            for (Integer integer : effectY) {
                g2.fillRect(left_x, integer, WIDTH, Block.SIZE);
            }
            if(effectCounter==20){
                effectCounterOn= false;
                effectCounter=0;
                effectY.clear();
            }
        }
        }


}
