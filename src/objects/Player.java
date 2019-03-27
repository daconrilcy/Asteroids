package objects;

import java.awt.*;

public class Player extends GameObject {


    public Player(float x, float y, ID id) {
        super(x, y, id);
        velX = 0;
        velY = 0;
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;
       // if x > Game.WIDTH x=0;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x , (int)y , 32 , 32);

    }
}
