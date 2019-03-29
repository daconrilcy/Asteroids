package Joystick;

import Joystick.Joystick;
import net.java.games.input.Component;
import objects.GameObject;
import objects.ID;
import objects.ObjectStatut;

public abstract class JoystickMove {
    GameObject object;
    Joystick joystick;
    Component bt;

    public  JoystickMove(GameObject object , Joystick joystick){
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
