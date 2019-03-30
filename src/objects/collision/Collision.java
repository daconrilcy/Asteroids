package objects.collision;

import objects.Bound;
import objects.GameObject;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedList;

public abstract class Collision {

     private GameObject objectA;
     private GameObject objectB;
     private LinkedList<Bound> boundsObjectA;
     private LinkedList<Bound> boundsObjectB;
     private boolean isCollided = false;
     private Bound collidedB;

    Collision(@NotNull GameObject objectA, @NotNull GameObject objectB){
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

    private void collision() {
        if (objectA == null) return;
        if (objectB == null) return;

        for (Bound bound : boundsObjectA) {
            if (bound != null) {
                for (Bound bound1 : boundsObjectB) {
                    if (bound1 != null) {
                        if (bound.getRectangle().intersects(bound1.getRectangle())) {
                            isCollided = true;
                            collidedB = bound1;
                            return;
                        }
                    }
                }
            }
        }
        isCollided = false;
        collidedB = null;
    }



    private void actionAfterCollision(){
            if (!isCollided) return;
            switch (collidedB.getID()){
                case bottom: case top:
                    objectB.setVelY(-objectB.getVelY());
                    objectB.setVelX(objectB.getVelX()*2f);
                    break;
                case right: case left:
                    objectB.setVelX(-objectB.getVelX());
                    float velY = objectB.getVelY()-objectA.getDifY()/10;
                    objectB.setVelY(velY);
                    break;
            }
    }

}
