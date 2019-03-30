package objects;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<>();

    public void tick(){
        for(GameObject gameObject : objects){
            gameObject.tick();
        }
    }

    public void render(Graphics g){

        for (GameObject gameObject : objects){
            gameObject.render(g);
        }

    }

    public void addObject(GameObject gameObject){
        objects.add(gameObject);
    }
}
