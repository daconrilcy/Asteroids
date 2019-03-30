package objects;

import board.Board;

import java.awt.*;

public class Rock extends Polygon {

    private int xDirection;
    private int yDirection;

    private int width = Board.width;
    private int height = Board.heigth;

    private static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
    private static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

    public Rock (int[] polyXArray , int[] polyYArray , int pointsInPoly , int xpoints, int ypoints ){

        super(polyXArray , polyYArray , pointsInPoly);
        super.xpoints[0] = xpoints;
        super.ypoints[0] = ypoints;

        this.xDirection = (int)(Math.random()*4+1);
        this.yDirection = (int)(Math.random()*4+1);
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
        int[] xTempPoly = sPolyXArray.clone();
        for (int i = 0 ; i < xTempPoly.length ; i++){
            xTempPoly[i] += randormXStartPos;
        }

        return xTempPoly;
    }

    public static int[] getsPolyYArray(int randormYStartPos){
        int[] yTempPoly = sPolyYArray.clone();
        for (int i = 0 ; i < yTempPoly.length ; i++){
            yTempPoly[i] += randormYStartPos;
        }
        return yTempPoly;
    }
}
