package objects;

import principal.Game;

import java.awt.*;

public class Competitor extends GameObject {

    public Competitor(float x, float y, ID id) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        width = 32;
        height = Game.HEIGHT/5;
        nBounds =0;
        setBounds();
        start();
    }

    @Override
    public void tick() {
        //x += velX;
        y += velY;
        if (x> Game.WIDTH-32){
            x = Game.WIDTH-32;
        }
        if (x < 0 ) x=0;
        if (y > Game.HEIGHT-40-128) y = Game.HEIGHT-40-128;
        if (y < 0) y = 0;
        updateBounds();
        capturePos();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x , (int)y , width , height);
        drawBounds(g);
    }

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
    public void start() {
        statut = ObjectStatut.started;
    }

    @Override
    protected void setBounds(){
        setABound((int)(x +width/4), (int)(y+height-height/2) , (int)(width/2) , (int)(height/2) ,ID_bounds.bottom);
        setABound((int)x , (int)y , (int)(width/4) , (int)height , ID_bounds.right);
        setABound((int)(x+width*3/4), (int)y , (int)(width/4), (int)height , ID_bounds.left);
        setABound((int)(x+width/4) , (int)y , (int)(width/2) , (int)(height/2) , ID_bounds.top);
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
        setVelX(0);
        setVelY(Game.HEIGHT/2-64);
        setX(0);
        setY(0);
    }
    public void resetPosVitDroite(int gd) {
        resetPosVit();
        setX(Game.WIDTH-width);
    }

}
