package Joystick;

import objects.GameObject;
import objects.ObjectStatut;

public class JoyClickBall extends JoystickMove {


    public JoyClickBall(GameObject object, Joystick joystick) {
        super(object, joystick);
    }

    @Override
    void moveObject() {

    }
    void btClicked(){
        if (joystick == null) return;
        if(joystick.isButton1() > 0){
            bt = joystick.getB1();
            ifB1Clicked();
        }
    }

    private void ifB1Clicked() {
        System.out.println(object.getStatut());
        if (object.getStatut() == ObjectStatut.stoped) {
            object.start();
        }
    }

}
