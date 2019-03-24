package objects;

import board.Board;

import java.awt.*;

public class Rock extends Polygon {

    int uLeftXPos, uLeftYPos;
    int xDirection = 1;
    int yDirection = 1;

    int width = Board.width;
    int height = Board.heigth;

    int[] polyXArray, polyYArray;

    public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
    public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

    public Rock (int[] polyXArray , int[] polyYArray , int pointsInPoly , int randomStartXPos , int randomStartYPos ){

        super(polyXArray , polyYArray , pointsInPoly);

        this.xDirection = (int)(Math.random()*4+1);
        this.yDirection = (int)(Math.random()*4+1);

        this.uLeftXPos = randomStartXPos;
        this.uLeftYPos = randomStartYPos;
    }

    public void move(){

        int uLeftXpos = super.xpoints[0];
        int uLeftYpos = super.ypoints[0];

        if (uLeftXpos < 8 || uLeftXpos + 40 > this.width){
            xDirection = - xDirection;
        }
        if (uLeftYpos < 0 || uLeftYpos + 50 > this.height){
            yDirection = - yDirection;
        }

        for (int i = 0 ; i < super.xpoints.length ; i++){
            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;
        }
    }

    public static int[] getsPolyXArray(int randormXStartPos){
        int[] xTempPoly = (int[])sPolyXArray.clone();
        for (int i = 0 ; i < xTempPoly.length ; i++){
            xTempPoly[i] += randormXStartPos;
        }

        return xTempPoly;
    }

    public static int[] getsPolyYArray(int randormYStartPos){
        int[] yTempPoly = (int[])sPolyYArray.clone();
        for (int i = 0 ; i < yTempPoly.length ; i++){
            yTempPoly[i] += randormYStartPos;
        }
        return yTempPoly;
    }
}
