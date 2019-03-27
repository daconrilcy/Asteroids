import objects.Handler;
import objects.ID;
import objects.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 640;
    public String title = "Zombie Game";
    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;
    private KeyAdapter keyAdapter;


    public Game(){
        new Window(WIDTH , HEIGHT ,title , this);
        start();
        init();
    }

    private void init(){
        handler = new Handler();
        handler.addObject(new Player(100,100,ID.Player));
       this.addKeyListener(new KeyInput(handler));
    }

    private synchronized void start(){
        if(isRunning) return;
        thread = new Thread(this);
        thread.start();
        isRunning = true;

    }

    private synchronized void stop(){
        if (!isRunning) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        isRunning = false;
    }

    @Override
    public void run() {
        int frames = 0 , updates =0;
        long timer = System.currentTimeMillis();
        this.requestFocus();
        long lasTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while (isRunning){
            long now = System.nanoTime();
            delta += (now-lasTime)/ns;
            lasTime = now;
            while (delta >= 1){
                tick();
                delta--;
                updates++;
            }
            render();
            frames ++;
           // System.out.println(frames);
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS : " + frames +" UPDATE : " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void tick(){
        //update the game
        handler.tick();
    }

    private void render(){
        //render the game
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0,0,WIDTH , HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();


    }

}
