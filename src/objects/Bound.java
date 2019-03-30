package objects;

import java.awt.*;

public class Bound {
    private ID_bounds ID;
    private Rectangle rectangle;

    Bound(int x , int y , int width , int height , ID_bounds id){
        this.rectangle = new Rectangle(x,y, width, height);
        this.ID = id;
    }

    public ID_bounds getID() {
        return ID;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    void updatePosRectangle(int x, int y){
        this.rectangle.x = x;
        this.rectangle.y = y;
    }
}
