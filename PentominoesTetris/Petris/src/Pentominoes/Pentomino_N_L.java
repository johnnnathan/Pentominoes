package Pentominoes;

import java.awt.*;

public class Pentomino_N_L extends PentominoL {
    public Pentomino_N_L() {
        create(new Color(62, 153, 218));
    }

    public void setXY(int x, int y) {
        // 1 1 0 0
        // 0 1 1 1
        b[0].x=x;
        b[0].y=y;
        b[1].x=x-Block.SIZE;
        b[1].y=y-Block.SIZE;
        b[2].x=x;
        b[2].y=y-Block.SIZE;
        b[3].x=x+Block.SIZE;
        b[3].y=y;
        b[4].x=x+Block.SIZE*2;
        b[4].y=y;
    }

    public void getRotation1() {
        // 1 1 0 0
        // 0 1 1 1
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x-Block.SIZE;
        tempB[1].y=b[0].y-Block.SIZE;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y-Block.SIZE;
        tempB[3].x=b[0].x+Block.SIZE;
        tempB[3].y=b[0].y;
        tempB[4].x=b[0].x+Block.SIZE*2;
        tempB[4].y=b[0].y;
        updateXY(1);
    }

    public void getRotation2() {
        // 0 1
        // 0 1
        // 1 1
        // 1 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x-Block.SIZE;
        tempB[1].y=b[0].y+Block.SIZE;
        tempB[2].x=b[0].x-Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x;
        tempB[3].y=b[0].y-Block.SIZE;
        tempB[4].x=b[0].x;
        tempB[4].y=b[0].y-Block.SIZE*2;
        updateXY(2);

    }

    public void getRotation3() {
        // 1 1 1 0
        // 0 0 1 1
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x+Block.SIZE;
        tempB[1].y=b[0].y+Block.SIZE;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y+Block.SIZE;
        tempB[3].x=b[0].x-Block.SIZE;
        tempB[3].y=b[0].y;
        tempB[4].x=b[0].x-Block.SIZE*2;
        tempB[4].y=b[0].y;
        updateXY(3);

    }

    public void getRotation4() {
        // 0 1
        // 1 1
        // 1 0
        // 1 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x+Block.SIZE;
        tempB[1].y=b[0].y-Block.SIZE;
        tempB[2].x=b[0].x+Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x;
        tempB[3].y=b[0].y+Block.SIZE;
        tempB[4].x=b[0].x;
        tempB[4].y=b[0].y+Block.SIZE*2;
        updateXY(4);
    }
}
