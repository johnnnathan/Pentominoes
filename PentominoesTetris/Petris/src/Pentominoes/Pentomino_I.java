package Pentominoes;

import java.awt.*;

public class Pentomino_I extends Pentomino{
    public Pentomino_I(){
        create(new Color(100, 0, 0));
    }
    public void setXY(int x, int y){
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        b[0].x=x;
        b[0].y=y;
        b[1].x=x;
        b[1].y=y-Block.SIZE*2;
        b[2].x=x;
        b[2].y=y-Block.SIZE;
        b[3].x=x;
        b[3].y=y+Block.SIZE;
        b[4].x=x;
        b[4].y=y+Block.SIZE*2;
    }
    public void getRotation1(){
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x;
        tempB[1].y=b[0].y-Block.SIZE*2;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y-Block.SIZE;
        tempB[3].x=b[0].x;
        tempB[3].y=b[0].y+Block.SIZE;
        tempB[4].x=b[0].x;
        tempB[4].y=b[0].y+Block.SIZE*2;
        updateXY(1);

    }
    public void getRotation2(){
        // 0 0 0 0 0
        // 0 0 0 0 0
        // 1 1 1 1 1
        // 0 0 0 0 0
        // 0 0 0 0 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x-Block.SIZE*2;
        tempB[1].y=b[0].y;
        tempB[2].x=b[0].x-Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x+Block.SIZE;
        tempB[3].y=b[0].y;
        tempB[4].x=b[0].x+Block.SIZE*2;
        tempB[4].y=b[0].y;
        updateXY(2);

    }
    public void getRotation3(){
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        // 0 0 1 0 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x;
        tempB[1].y=b[0].y-Block.SIZE*2;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y-Block.SIZE;
        tempB[3].x=b[0].x;
        tempB[3].y=b[0].y+Block.SIZE;
        tempB[4].x=b[0].x;
        tempB[4].y=b[0].y+Block.SIZE*2;
        updateXY(3);
    }
    public void getRotation4(){
        // 0 0 0 0 0
        // 0 0 0 0 0
        // 1 1 1 1 1
        // 0 0 0 0 0
        // 0 0 0 0 0
        if (b[0] != null) {
            tempB[0].x = b[0].x;
            tempB[0].y = b[0].y;
        }
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x-Block.SIZE*2;
        tempB[1].y=b[0].y;
        tempB[2].x=b[0].x-Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x+Block.SIZE;
        tempB[3].y=b[0].y;
        tempB[4].x=b[0].x+Block.SIZE*2;
        tempB[4].y=b[0].y;
        updateXY(4);
    }

}
