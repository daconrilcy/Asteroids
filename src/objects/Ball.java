package objects;

import principal.Game;

import java.awt.*;

public class Ball extends GameObject {

    public Ball(GameObject player) {
        super(0, 0, ID.ball);
        this.player = player;
        if (player.getX() > Game.WIDTH/2){
            this.setX(player.getX() - width);
        }else {
            this.setX(player.getX() + player.getWidth());
        }
        this.setY(player.getY() + player.getHeight()/2f-height/2f);
        init();
    }

    private void init(){
        width = 32;
        height = 32;
        velY = 0;
        velX = 0;
        setBounds();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y<1 || y> Game.HEIGHT-40-height){
            velY = -velY;
            isBound = true;
        }else {
            isBound = false;
        }

        if (statut == ObjectStatut.stoped && player != null){
            if (player.getX() > Game.WIDTH/2){
                this.setX(player.getX() - width);
            }else {
                this.setX(player.getX() + player.getWidth());
            }
            this.setY(player.getY() + player.getHeight()/2f-height/2f);
        }

        if (isBound){
            velY *=1.1;
        }


        updateBounds();
        capturePos();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x , (int)y , width , height);
        drawBounds(g);
    }

    @Override
    public void drawBounds(Graphics g){

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.black);
        for (Bound b : bounds){
            if (b !=null){
                g2D.draw(b.getRectangle());
            }

        }
    }

    @Override
    protected void setBounds() {
        setABound((int)(x +width/4), (int)(y+height-height/2) , (width/2), height/2 ,ID_bounds.bottom);
        setABound((int)x , (int)y , (width/4) , height , ID_bounds.right);
        setABound((int)(x+width*3/4), (int)y , (width/4), height , ID_bounds.left);
        setABound((int)(x+width/4) , (int)y , (width/2) , (height/2) , ID_bounds.top);
    }

    public void start(){
        if (player == null) return;
        double coef = 1;
        if (player.getX() > Game.WIDTH/2){
            coef = -1;
        }
        double angle = Math.random()*Math.PI/2-Math.PI/4;
        int roundA =  (int)(angle * 180/Math.PI*5)/5;
        boolean correctAngle = false;
        while (!correctAngle){
            if (roundA == 180 || roundA == 90 || roundA == 0 ||roundA == -90 || roundA == 270){
                correctAngle = false;
                angle = Math.random()*Math.PI/2-Math.PI/4;
                roundA = (int)(angle * 180/Math.PI*5)/5;
            }else {
                correctAngle = true;
            }
        }
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);
        System.out.println(cosA +" - "+sinA);
        velX = (float)(coef*3 * cosA);
        velY = (float)(3 * sinA);
        System.out.println("x : "+ player.getX()+" - Velx :" + velX +" - "+velY);
        if (player.getX() > Game.WIDTH/2){
            this.setX(player.getX() - width);
        }else {
            this.setX(player.getX() + player.getWidth());
        }
        this.setY(player.getY() + (float)(player.getHeight()/2)-(float)(height/2));
        statut = ObjectStatut.started;
    }

    @Override
    protected void updateBounds() {
        if (bounds.get(0) != null){
            bounds.get(0).updatePosRectangle((int)(x +width/4), (int)(y+height-height/2));
        }
        if (bounds.get(1) != null){
            bounds.get(1).updatePosRectangle((int)x , (int)y );
        }
        if (bounds.get(2) != null){
            bounds.get(2).updatePosRectangle((int)(x+width*3/4), (int)y);
        }
        if (bounds.get(3) != null){
            bounds.get(3).updatePosRectangle((int)(x+width/4) , (int)y);
        }
    }

    @Override
    public void resetPosVit() {
     if (player != null){
         if (player.getX() > Game.WIDTH/2){
             this.setX(player.getX() - width);
         }else {
             this.setX(player.getX() + player.getWidth());
         }
         this.setY(player.getY() + (float)(player.getHeight()/2)-(float)(height/2));
     }else {
         setX((float)(Game.WIDTH/2));
         setY((float)(Game.HEIGHT/2));
     }
        setVelX(0);
        setVelY(0);
        this.statut = ObjectStatut.stoped;
    }
}
