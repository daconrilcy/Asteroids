package Joystick;

import objects.GameObject;

public class JoyStickPlayer extends JoystickMove {

    public JoyStickPlayer(GameObject object, Joystick joystick) {
        super(object, joystick);
    }

    void moveObject(){
        if (joystick == null)return;
        float vx = (float) (joystick.getAX());
        float vy = (float)(joystick.getAY());

        if (object == null) return;
            object.setVelX(vx / 10);
            object.setVelY(vy / 10);
    }

    @Override
    void btClicked() {

    }
}
