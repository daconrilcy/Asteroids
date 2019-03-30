package Joystick;

import objects.GameObject;
import objects.Handler;
import objects.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        //System.out.println(key);
        for(int i =0 ; i < handler.objects.size() ; i++){
            GameObject o = handler.objects.get(i);
            if (o.getId() == ID.Player){
                switch (key){
                    case 81:
                        o.setVelX(-5);
                        break;
                    case 68:
                        o.setVelX(+5);
                        break;
                    case 90:
                        o.setVelY(-5);
                        break;
                    case 83:
                        o.setVelY(5);
                        break;
                }
            }
        }
        if (key == 27){
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        //System.out.println(key);
        for(int i =0 ; i < handler.objects.size() ; i++){
            GameObject o = handler.objects.get(i);
            if (o.getId() == ID.Player){
                switch (key){
                    case 81:
                        o.setVelX(0);
                        break;
                    case 68:
                        o.setVelX(0);
                        break;
                    case 90:
                        o.setVelY(0);
                        break;
                    case 83:
                        o.setVelY(0);
                        break;
                }
            }
        }

    }
}
