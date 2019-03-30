package board;

import objects.Rock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameDrawingPanel extends JComponent {

    private ArrayList<Rock> rocks = new ArrayList<>();

    GameDrawingPanel(){
        int NROCKS = 50;
        for (int i = 0; i < NROCKS; i++ ){

            int randormStartXPos = (int)(Math.random()*(Board.width-40)+5);
            int randormStartYPos = (int)(Math.random()*(Board.heigth-40)+1);
            this.setBackground(Color.BLACK);
            rocks.add(new Rock(Rock.getsPolyXArray(randormStartXPos), Rock.getsPolyYArray(randormStartYPos),13 , randormStartXPos , randormStartYPos));
        }

    }

    public void paint(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.BLACK);
        //g2d.fillRect(0,0,getWidth() , getHeight());

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.setPaint(Color.RED);

        for (Rock rock : rocks){
            rock.move();
            g2d.draw(rock);
        }

        g2d.dispose();

    }
}
