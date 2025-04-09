package Pentominoes;

import main.*;

import java.awt.*;

public class PentominoL {
    public Block[] b = new Block[5];
    public Block[] tempB = new Block[5];
    public static int autoDropCounter = 0;
    public int rotation = 1;
    public boolean isAlive = true;
    public boolean isDying;
    public int dyingTimer = 0;

    public static boolean leftCollision, rightCollision, bottomCollision, topCollision;

    public void create(Color C) {
        b[0] = new Block(C);
        b[1] = new Block(C);
        b[2] = new Block(C);
        b[3] = new Block(C);
        b[4] = new Block(C);
        tempB[0] = new Block(C);
        tempB[1] = new Block(C);
        tempB[2] = new Block(C);
        tempB[3] = new Block(C);
        tempB[4] = new Block(C);
    }

    public void checkMovementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        topCollision = false;
        checkStaticCollision();
        for (int i = 0; i < 5; i++) {
            if (b[i].x == GamePanelPerfectSequence.left_x) {
                leftCollision = true;
            }
            if (b[i].x >= GamePanelPerfectSequence.right_x - Block.SIZE) {
                rightCollision = true;
            }
            if (b[i].y >= GamePanelPerfectSequence.bottom_y - Block.SIZE) {
                bottomCollision = true;
            }

        }
    }

    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        topCollision = false;
        checkStaticCollision();
        for (Block block : tempB) {
            if (block.x < GamePanelPerfectSequence.left_x) {
                leftCollision = true;
                break;
            }
        }

        for (Block block : tempB) {
            if (block.x + Block.SIZE > GamePanelPerfectSequence.right_x) {
                rightCollision = true;
                break;
            }
        }
        for (Block block : tempB) {
            if (block.y + Block.SIZE > GamePanelPerfectSequence.bottom_y) {
                bottomCollision = true;
                break;
            }
        }
        for (Block block : tempB) {
            if (block.y < GamePanel.top_y) {
                topCollision = true;
                break;
            }
        }

    }

    private void checkStaticCollision() {
        for(int i = 0; i< GamePanelPerfectSequence.staticBlocksL.size(); i++) {
            int staticX = GamePanelPerfectSequence.staticBlocksL.get(i).x;
            int staticY = GamePanelPerfectSequence.staticBlocksL.get(i).y;
            for (int j = 0; j < 5; j++) {
                if (b[j].x == staticX && b[j].y == staticY - Block.SIZE) {
                    bottomCollision = true;
                    break;
                }
            }
            for (int j = 0; j < 5; j++) {
                if (b[j].x == staticX && b[j].y == staticY + Block.SIZE) {
                    topCollision = true;
                    break;
                }
            }
            for (int j = 0; j < 5; j++) {
                if (b[j].x == staticX - Block.SIZE && b[j].y == staticY) {
                    rightCollision = true;
                    break;
                }
            }
            for (int j = 0; j < 5; j++) {
                if (b[j].x == staticX + Block.SIZE && b[j].y == staticY) {
                    leftCollision = true;
                    break;
                }
            }
        }

    }

    public void getRotation1() {
    }

    public void getRotation2() {
    }

    public void getRotation3() {
    }

    public void getRotation4() {
    }
    public void die(){
        dyingTimer++;
        if(dyingTimer>=90){
            dyingTimer =0;
            checkMovementCollision();
            if(bottomCollision){
                isAlive = false;
            }


        }
    }






    public void update() {
        if (isDying) {
            die();
        }

        if(KeyHandler.spacePressed) {
            while (!bottomCollision) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                b[4].y += Block.SIZE;
                checkMovementCollision();
            }
            KeyHandler.spacePressed = false;
            isAlive=false;

        }

        if (KeyHandler.upPressed) {
            int tempRotation = rotation;
            switch (tempRotation) {
                case 1:
                    getRotation2();
                    if (rotation==2) {

                        break;
                    }
                case 2:
                    getRotation3();
                    if(rotation==3) {

                        break;
                    }
                case 3:
                    getRotation4();
                    if(rotation==4) {

                        break;
                    }
                case 4:
                    getRotation1();
                    if(rotation==1) {

                        break;
                    }
            }
            KeyHandler.upPressed = false;
        }
        checkMovementCollision();


        if (KeyHandler.downPressed) {
            if (!bottomCollision) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                b[4].y += Block.SIZE;
                autoDropCounter = 0;
            }

            KeyHandler.downPressed = false;

        }
        if (KeyHandler.leftPressed) {
            if (!leftCollision) {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;
                b[4].x -= Block.SIZE;
            }
            KeyHandler.leftPressed = false;
        }
        if (KeyHandler.rightPressed) {
            if (!rightCollision) {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
                b[4].x += Block.SIZE;
            }
            KeyHandler.rightPressed = false;
        }

        if (bottomCollision) {
            isDying = true;
        } else {
            autoDropCounter++;
            if (autoDropCounter >= GamePanelPerfectSequence.dropInterval) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                b[4].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
        GamePanelPerfectSequence.moveCounter++;
    }


    public void setXY(int x, int y) {

    }

    public void updateXY(int rotation) {
        checkRotationCollision();
        if (!leftCollision && !rightCollision && !bottomCollision && !topCollision) {
            this.rotation = rotation;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
            b[4].x = tempB[4].x;
            b[4].y = tempB[4].y;
        }

    }


    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - margin * 2, Block.SIZE - margin * 2);
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - margin * 2, Block.SIZE - margin * 2);
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - margin * 2, Block.SIZE - margin * 2);
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - margin * 2, Block.SIZE - margin * 2);
        g2.fillRect(b[4].x + margin, b[4].y + margin, Block.SIZE - margin * 2, Block.SIZE - margin * 2);

    }


}
