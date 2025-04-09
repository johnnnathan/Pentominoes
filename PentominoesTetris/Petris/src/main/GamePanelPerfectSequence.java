package main;

import Pentominoes.*;

import java.awt.*;

import java.util.ArrayList;




public class GamePanelPerfectSequence  {
    public static int moveInterval = 80;
    // AREA
    final int WIDTH = 200;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;
    public static int score = 0;

    boolean effectCounterOn;
    int effectCounter = 0;
    ArrayList<Integer> effectY = new ArrayList<>();


    // PENTOMINO
    PentominoL currentPentomino;
    PentominoL nextPentomino;
    int NEXT_PENTOMINO_X;
    int NEXT_PENTOMINO_Y;
    public static ArrayList<Block> staticBlocksL = new ArrayList<>();
    int PENTOMINO_START_X;
    int PENTOMINO_START_Y;

    public static int dropInterval = 120;










    public GamePanelPerfectSequence()  {


        left_x=(PanelManager.WIDTH/2)-WIDTH/2;
        right_x=left_x+WIDTH;
        top_y=40;
        bottom_y=top_y+HEIGHT;
        currentPentomino = randomPentomino();
        nextPentomino = randomPentomino();
        PENTOMINO_START_X = left_x  + WIDTH/2 - Block.SIZE/2;
        if(currentPentomino instanceof Pentomino_I_L||currentPentomino instanceof Pentomino_V_L) {
            PENTOMINO_START_Y = top_y + Block.SIZE*2;
        }
        else {
            PENTOMINO_START_Y = top_y + Block.SIZE;
        }


        NEXT_PENTOMINO_X = left_x - 250 + WIDTH/2 - Block.SIZE/2;
        if(nextPentomino instanceof Pentomino_I_L||nextPentomino instanceof Pentomino_V_L) {
            NEXT_PENTOMINO_Y = top_y + Block.SIZE*2;
        }
        else {
            NEXT_PENTOMINO_Y = top_y + Block.SIZE;
        }

        currentPentomino.setXY(PENTOMINO_START_X, PENTOMINO_START_Y);
        nextPentomino.setXY(NEXT_PENTOMINO_X, NEXT_PENTOMINO_Y);




    }

    public int r = 0;
    private PentominoL randomPentomino(){
        PentominoL p;
        if(r>11){
            r=0;
        }
        p = switch (r) {
            case 0 -> new Pentomino_L_L();
            case 1 -> new Pentomino_N_L();
            case 2 -> new Pentomino_U_L();
            case 3 -> new Pentomino_V_L();
            case 4 -> new Pentomino_Z_L();
            case 5 -> new Pentomino_W_L();
            case 6 -> new Pentomino_P_L();
            case 7 -> new Pentomino_X_L();
            case 8 -> new Pentomino_I_L();
            case 9 -> new Pentomino_F_L();
            case 10 -> new Pentomino_T_L();
            case 11 -> new Pentomino_Y_L();
            default -> null;
        };
        r++;
        return p;
    }
    public static int moveCounter=0;
    public void move()  {
        if(moveCounter>moveInterval) {
            if (currentPentomino instanceof Pentomino_L_L) {
                if(currentPentomino.rotation!=4) {
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=left_x) {
                    KeyHandler.leftPressed = true;
                }else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_N_L) {

                KeyHandler.spacePressed = true;

            } else if (currentPentomino instanceof Pentomino_U_L) {
                if(currentPentomino.rotation!=2){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=right_x-Block.SIZE){
                    KeyHandler.rightPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_V_L) {
                if(currentPentomino.b[0].x!=left_x+Block.SIZE){
                    KeyHandler.leftPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_Z_L) {
                if(currentPentomino.rotation!=2){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=right_x-Block.SIZE*2){
                    KeyHandler.rightPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_W_L) {
                if(currentPentomino.rotation!=2){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=right_x-Block.SIZE*2){
                    KeyHandler.rightPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_P_L) {
                if(currentPentomino.b[0].x!=left_x+Block.SIZE){
                    KeyHandler.leftPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_X_L) {
                if(currentPentomino.b[0].x!=right_x-Block.SIZE*2){
                    KeyHandler.rightPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_I_L) {
                if (currentPentomino.b[0].x != left_x) {
                    KeyHandler.leftPressed = true;
                } else {
                    KeyHandler.spacePressed = true;
                }
            } else if (currentPentomino instanceof Pentomino_F_L)  {
                if(currentPentomino.rotation!=2){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=left_x+Block.SIZE){
                    KeyHandler.leftPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_T_L) {
                if(currentPentomino.rotation!=4){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=right_x-Block.SIZE*2){
                    KeyHandler.rightPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            } else if (currentPentomino instanceof Pentomino_Y_L) {
                if(currentPentomino.rotation!=2){
                    KeyHandler.upPressed = true;
                }
                else if(currentPentomino.b[0].x!=left_x+Block.SIZE){
                    KeyHandler.leftPressed = true;
                }
                else{
                    KeyHandler.spacePressed=true;
                }
            }

            moveCounter=0;
        }
        moveCounter++;
        currentPentomino.update();
    }

    public void update() {


        if(!currentPentomino.isAlive) {
            staticBlocksL.add(currentPentomino.b[0]);
            staticBlocksL.add(currentPentomino.b[1]);
            staticBlocksL.add(currentPentomino.b[2]);
            staticBlocksL.add(currentPentomino.b[3]);
            staticBlocksL.add(currentPentomino.b[4]);


            currentPentomino.isDying = false;

            currentPentomino = nextPentomino;
            PENTOMINO_START_X = left_x  + WIDTH/2 - Block.SIZE/2;
            if(currentPentomino instanceof Pentomino_I_L||currentPentomino instanceof Pentomino_V_L) {
                PENTOMINO_START_Y = top_y + Block.SIZE*2;
            }
            else {
                PENTOMINO_START_Y = top_y + Block.SIZE;
            }
            currentPentomino.setXY(PENTOMINO_START_X, PENTOMINO_START_Y);

            nextPentomino = randomPentomino();



            NEXT_PENTOMINO_X = left_x - 250 + WIDTH/2 - Block.SIZE/2;
            if(nextPentomino instanceof Pentomino_I_L||nextPentomino instanceof Pentomino_V_L) {
                NEXT_PENTOMINO_Y = top_y + Block.SIZE*2;
            }
            else {
                NEXT_PENTOMINO_Y = top_y + Block.SIZE;
            }
            nextPentomino.setXY(NEXT_PENTOMINO_X, NEXT_PENTOMINO_Y);
            checkDelete();
        }
        else {
            move();

        }
    }

    private void checkDelete(){
        int x=left_x;
        int y=top_y;
        int blockCount = 0;
        while (x<right_x && y<bottom_y){

            for (Block staticBlock : staticBlocksL) {
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

        for (int i = 0; i < staticBlocksL.size(); i++) {
            if (staticBlocksL.get(i).y == y) {
                staticBlocksL.remove(i);
                i--;
            }
        }
        staticBlocksL.sort((b1, b2) -> Integer.compare(b2.y, b1.y));

        for (int i = 0; i < staticBlocksL.size(); i++) {
            if (staticBlocksL.get(i).y < y) {
                int staticX = staticBlocksL.get(i).x;
                int staticY = staticBlocksL.get(i).y;

                moveBlockDown(staticBlocksL, i, staticX, staticY, bottom_y);
            }
        }


        score++;
    }

    private static void moveBlockDown(ArrayList<Block> staticBlocksL, int index, int x, int y, int bottomBoundary) {
        boolean collision = false;

        while (!collision && y + Block.SIZE != bottomBoundary) {
            for (Block block : staticBlocksL) {
                if (block.x == x && block.y == y + Block.SIZE) {
                    collision = true;
                    break;
                }
            }

            if (!collision) {
                y += Block.SIZE;
            }
        }

        staticBlocksL.get(index).y = y;
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
        g2.setFont(new Font("Tetris 2", Font.PLAIN, 50));
        g2.drawString("To stop press L.", 10, 330);
        g2.setFont(new Font("Tetris 2", Font.PLAIN, 20));
        g2.drawString("To change the move interval press M.", 10, 390);

        g2.drawString("Move intervals: 10, 40, 60, 80 (ms)", 10, 420);
        g2.drawString("Move interval: "+moveInterval, 10, 450);








        if(currentPentomino!=null) {
            currentPentomino.draw(g2);
        }

        nextPentomino.draw(g2);
        for (Block staticBlock : staticBlocksL) {
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
