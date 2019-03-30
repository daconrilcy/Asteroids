package score;

import java.awt.*;

/*
         0
         -
      3 | | 1
         -  2
      6 | | 4
         -
         5
*/

public class Digit {

    private Rectangle[] rectangles;

    private int topX,topY;
    private int width, height,weight,longUnit;

    private int[] x;
    private int[] y;
    private int[] longeurUnits;
    private int[] hauteurUnits;
    private int NDIGIT = 7;
    private int numberEncours;

    private Color color;

    public void setNumberEncours(int numberEncours) {
        this.numberEncours = numberEncours;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTopY() {
        return topY;
    }

    public Color getColor() {
        return color;
    }

    public Digit(int number){
        topX=0;
        topY=0;
        width = 50;
        height = 180;
        weight = 10;
        longUnit = height-3*weight/2;
        color = Color.black;
        rectangles = new Rectangle[NDIGIT];
        numberEncours = number;
        x = new int[NDIGIT];
        y = new int[NDIGIT];
        longeurUnits = new int[NDIGIT];
        hauteurUnits = new int[NDIGIT];
        positionneElement();
        affecteWHRectangle();
        createDigit();
    }

    private void positionneElement(){
        x[0] = topX+weight;
        x[1] = topX+width-weight;
        x[2] = x[0];
        x[3] = topX;
        x[4] = x[1];
        x[5] = x[2];
        x[6] = x[3];

        y[0] = topY;
        y[1] = topY;
        y[2] = topY+longUnit-weight/2;
        y[3] = y[1];
        y[4] = topY+longUnit;
        y[5] = y[4]+longUnit-weight;
        y[6] = y[4];
    }

    private void affecteWHRectangle(){
        for (int i = 0 ; i < 7 ; i++){
            switch (i){
                case 0: case 2: case 5:
                    longeurUnits[i] = width-weight*2;
                    hauteurUnits[i] = weight;
                    break;
                case 1: case 3: case 4: case 6:
                    longeurUnits[i] = weight;
                    hauteurUnits[i] = longUnit;
                    break;
            }
        }
    }

    private void createDigit(){
        for (int i=0 ; i<7 ; i++){
            rectangles[i] = new Rectangle(x[i],y[i] ,longeurUnits[i], hauteurUnits[i]);
        }
    }

    private void updatePosDigit(){
        for (int i=0 ; i<7 ; i++){
            rectangles[i].setLocation(x[i] , y[i]);
        }
    }

    private void changeTailleDigit(){
        affecteWHRectangle();
        for (int i=0 ; i<7 ; i++){
            rectangles[i].setSize(longeurUnits[i] , hauteurUnits[i]);
        }
    }

    public void move(int x, int y ){
        this.topX = x;
        this.topY = y;
        positionneElement();
        updatePosDigit();
    }
    private void changeWeight(int weight){
        this.weight = weight;
        longUnit = (height)/2;
        positionneElement();
        affecteWHRectangle();
        changeTailleDigit();
        updatePosDigit();
    }

    public void changeSize(int width , int height){
        this.width = width;
        this.height = height;
        changeWeight(this.weight);
    }

    private void dessineRectangle(int nRect, Graphics g){
        if (nRect < 0 || nRect > NDIGIT) return;
        g.setColor(this.color);
        g.fillRect(rectangles[nRect].x, rectangles[nRect].y , rectangles[nRect].width , rectangles[nRect].height);
    }

    private void affichRectangle(boolean r0, boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6, Graphics g){
        if (r0){
            dessineRectangle(0, g);
        }
        if (r1){
            dessineRectangle(1,g);
        }
        if (r2){
            dessineRectangle(2,g);
        }
        if (r3){
            dessineRectangle(3,g);
        }
        if (r4){
            dessineRectangle(4,g);
        }
        if (r5){
            dessineRectangle(5,g);
        }
        if (r6){
            dessineRectangle(6,g);
        }
    }

    private void affichNumber(int n, Graphics g){
        switch (n){
            case 1:
                affichRectangle(false,true,false,false,true,false,false,g);
                break;
            case 2:
                affichRectangle(true,true,true,false,false,true,true, g);
                break;
            case 3:
                affichRectangle(true , true , true , false , true, true, false, g);
                break;
            case 4:
                affichRectangle(false , true, true, true, true, false, false, g);
                break;
            case 5:
                affichRectangle(true , false , true , true , true, true, false , g);
                break;
            case 6:
                affichRectangle(true, false, true, true, true, true, true , g);
                break;
            case 7:
                affichRectangle(true,true, false, false, true, false, false , g);
                break;
            case 8:
                affichRectangle(true,true,true,true,true,true,true, g);
                break;
            case 9:
                affichRectangle(true,true, true, true, true, true, false, g);
                break;
            default:
                affichRectangle(true,true,false,true, true, true, true, g);
                break;
        }
        numberEncours = n;
    }

    public void render(Graphics g){
        if (numberEncours ==99) return;
        affichNumber(numberEncours, g);
    }
}
