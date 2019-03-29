package objects;

import java.awt.*;

public class Bound {
    protected ID_bounds ID;
    protected Rectangle rectangle;

    Bound(int x , int y , int width , int height , ID_bounds id){
        this.rectangle = new Rectangle(x,y, width, height);
        this.ID = id;
    }

    public ID_bounds getID() {
        return ID;
    }

    public void setID(ID_bounds ID) {
        this.ID = ID;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void updatePosRectangle(int x, int y ){
        this.rectangle.x = x;
        this.rectangle.y = y;
    }
}
