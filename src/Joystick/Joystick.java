package Joystick;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Joystick {
    private Controller[] controllers;
    private Controller controller;
    private Component[] components;
    private Component component;
    private Component RX, RY, AX, AY, AZ;
    private Component b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    private int RXnullA, RXnullB, RXMax , RXMin , RXMid , RXLong;
    private int AXnullA, AXnullB, AXMax , AXMin , AXMid , AXLong;
    private int AYnullA, AYnullB, AYMax , AYMin , AYMid , AYLong;

    public Joystick(){
        init();
    }

    private void init(){
        controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers){
            if (c.getType() == Controller.Type.GAMEPAD){
                controller = c;
                components = controller.getComponents();
                RX = controller.getComponent(Component.Identifier.Axis.RX);
                RY = controller.getComponent(Component.Identifier.Axis.RY);
                AX = controller.getComponent(Component.Identifier.Axis.X);
                AY = controller.getComponent(Component.Identifier.Axis.Y);
                AZ = controller.getComponent(Component.Identifier.Axis.Z);
                b0 = controller.getComponent(Component.Identifier.Button._0);
                b1 = controller.getComponent(Component.Identifier.Button._1);
                b2 = controller.getComponent(Component.Identifier.Button._2);
            }
        }

        RXnullA = -4;
        RXnullB = 4;
        RXMax = 100;
        RXMin = -99;
        RXMid = (RXMax+RXMin)/2;
        RXLong = RXMax-RXMin;
        AXnullA = -3;
        AXnullB = 4;
        AXMax = 100;
        AXMin = -99;
        AXMid = (AXMax+AXMin)/2;
        AXLong = AXMax-AXMin;
        AYnullA = -1;
        AYnullB = 1;
        AYMax = 99;
        AYMin = -100;
        AYMid = (AXMax+AXMin)/2;
        AYLong = AXMax-AXMin;

    }

    public void tick(){
        controller.poll();
    }

    public int getRX() {
        if (controller == null ) return 0;
        if (RX == null) return 0;
        int val= (int)(RX.getPollData()*100);
        if (val >= RXnullA && val <= RXnullB){
            return 0;
        }
        return  (int)(RX.getPollData()*100-RXMid);
    }

    public int getRY(){
        if (controller == null ) return 0;
        if (RY == null) return 0;
        return  (int)(RY.getPollData()*100);
    }
    public int getAX(){
        if (controller == null ) return 0;
        if (AX == null) return 0;
        int val = (int)(AX.getPollData()*100-AXMid);
        if (val > AXnullA && val < AXnullB)return 0;
        val = (int)((AX.getPollData()*100-AXMid)/AXLong*200);
        return val;
    }
    public int getAY(){
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

    public Component getB1() {
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

    public double isButton1(){
        if (controller == null ) return 0;
        return b1.getPollData();
    }

}
