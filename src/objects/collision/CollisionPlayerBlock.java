package objects.collision;


import objects.Bound;
import objects.GameObject;


public class CollisionPlayerBlock extends Collision {


    public CollisionPlayerBlock(GameObject objectA, GameObject objectB) {
        super(objectA, objectB);
    }


    @Override
    public void actionAfterCollision() {
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
