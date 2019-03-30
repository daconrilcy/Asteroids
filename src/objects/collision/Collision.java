package objects.collision;

import objects.Bound;
import objects.GameObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Collision {

    protected GameObject objectA;
    protected GameObject objectB;
    protected LinkedList<Bound> boundsObjectA;
    protected LinkedList<Bound> boundsObjectB;
    protected boolean isCollided = false;
    protected Bound collidedA, collidedB;

    public Collision(@NotNull GameObject objectA , @NotNull GameObject objectB){
        this.objectA = objectA;
        this.objectB = objectB;
        this.boundsObjectA = objectA.getBounds();
        this.boundsObjectB = objectB.getBounds();
    }

    public boolean isCollided() {
        return isCollided;
    }

    public void tick(){
       collision();
       actionAfterCollision();
    }

    public void collision() {
        if (objectA == null) return;
        if (objectB == null) return;
        for (int i=0 ; i < boundsObjectA.size(); i++){
            if (boundsObjectA.get(i) != null) {
                for (int t=0 ; t < boundsObjectB.size(); t++){
                    if (boundsObjectB.get(t) != null) {
                        if (boundsObjectA.get(i).getRectangle().intersects(boundsObjectB.get(t).getRectangle())){
                            isCollided = true;
                            collidedA = boundsObjectA.get(i);
                            collidedB = boundsObjectB.get(t);
                            return;
                        }
                    }
                }
            }
        }
        isCollided = false;
        collidedA = null;
        collidedB = null;
    }



    public abstract void actionAfterCollision();

}
