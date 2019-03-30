package Joystick;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Joystick {
    private Controller controller;
    private Component RX, RY, AX, AY, AZ;
    private Component b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    private int RXnullA;
    private int RXnullB;
    private int RXMid;
    private int AXnullA;
    private int AXnullB;
    private int AXMid;
    private int AXLong;
    private int AYnullA;
    private int AYnullB;
    private int AYMid;
    private int AYLong;

    public Joystick(){
        init();
    }

    private void init(){
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers){
            if (c.getType() == Controller.Type.GAMEPAD){
                controller = c;
                RX = controller.getComponent(Component.Identifier.Axis.RX);
                RY = controller.getComponent(Component.Identifier.Axis.RY);
                AX = controller.getComponent(Component.Identifier.Axis.X);
                AY = controller.getComponent(Component.Identifier.Axis.Y);
                AZ = controller.getComponent(Component.Identifier.Axis.Z);
                b0 = controller.getComponent(Component.Identifier.Button._0);
                b1 = controller.getComponent(Component.Identifier.Button._1);
                b2 = controller.getComponent(Component.Identifier.Button._2);
                b3 = controller.getComponent(Component.Identifier.Button._3);
                b4 = controller.getComponent(Component.Identifier.Button._4);
                b5 = controller.getComponent(Component.Identifier.Button._5);
                b6 = controller.getComponent(Component.Identifier.Button._6);
                b7 = controller.getComponent(Component.Identifier.Button._7);
                b8 = controller.getComponent(Component.Identifier.Button._8);
                b9 = controller.getComponent(Component.Identifier.Button._9);
                b10 = controller.getComponent(Component.Identifier.Button._10);
            }
        }

        RXnullA = -4;
        RXnullB = 4;
        int RXMax = 100;
        int RXMin = -99;
        RXMid = (RXMax + RXMin)/2;
        int RXLong = RXMax - RXMin;
        AXnullA = -3;
        AXnullB = 4;
        int AXMax = 100;
        int AXMin = -99;
        AXMid = (AXMax + AXMin)/2;
        AXLong = AXMax - AXMin;
        AYnullA = -1;
        AYnullB = 1;
        int AYMax = 99;
        int AYMin = -100;
        AYMid = (AXMax + AXMin)/2;
        AYLong = AXMax - AXMin;

    }

    public void tick(){
        controller.poll();
    }


    int getAX(){
        if (controller == null ) return 0;
        if (AX == null) return 0;
        int val = (int)(AX.getPollData()*100-AXMid);
        if (val > AXnullA && val < AXnullB)return 0;
        val = (int)((AX.getPollData()*100-AXMid)/AXLong*200);
        return val;
    }
    int getAY(){
        if (controller == null ) return 0;
        if (AY == null) return 0;
        int val = (int)(AX.getPollData()*100-AXMid);
        if (val > AYnullA && val < AYnullB)return 0;
        val = (int)((AY.getPollData()*100-AYMid)/AYLong*200);
        return val;
    }

    public Component getB0() {
        return b0;
    }

    Component getB1() {
        return b1;
    }

    public Component getB2() {
        return b2;
    }

    public Component getB3() {
        return b3;
    }

    public Component getB4() {
        return b4;
    }

    public Component getB5() {
        return b5;
    }

    public Component getB6() {
        return b6;
    }

    public Component getB7() {
        return b7;
    }

    public Component getB8() {
        return b8;
    }

    public Component getB9() {
        return b9;
    }

    public Component getB10() {
        return b10;
    }

    public int getAZ(){
        if (controller == null ) return 0;
        if (AZ == null) return 0;
        return  (int)(AZ.getPollData()*100);
    }

    double isButton1(){
        if (controller == null ) return 0;
        return b1.getPollData();
    }

}
