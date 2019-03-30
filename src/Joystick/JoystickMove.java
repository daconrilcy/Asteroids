package Joystick;

import net.java.games.input.Component;
import objects.GameObject;

public abstract class JoystickMove {
    GameObject object;
    Joystick joystick;
    Component bt;

    JoystickMove(GameObject object, Joystick joystick){
        this.object = object;
        this.joystick = joystick;
    }

    public void tick(){
        moveObject();
        btClicked();
    }

    abstract void moveObject();
    abstract void btClicked();
}
