package principal;


import objects.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;
import Joystick.*;
import objects.collision.CollisionPlayerBlock;
import score.Digit;


public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 640;
    public String title = "Zombie principal.Game";
    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;
    private GameObject player , competitor, ball;
    private KeyAdapter keyAdapter;
    private Joystick joystick;
    private JoyStickPlayer joystickMove;
    private JoyClickBall joysticClick;
    private CollisionPlayerBlock collisionPlayerBlock , collisionCompetitorBlock;
    private int scoreP1, scoreP2;
    private Digit scoreAffichP1 , scoreAffichP2;


    public Game(){
        new Window(WIDTH , HEIGHT ,title , this);
        init();
        start();

    }

    private void init(){

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        joystick = new Joystick();
        player = new Player(0,HEIGHT/2-64, ID.Player);
        competitor = new Competitor(Game.WIDTH-48 ,HEIGHT/2-64 , ID.Competitor );
        ball = new Ball(player);
        handler.addObject(player);
        handler.addObject(competitor);
        handler.addObject(ball);
        joystickMove = new JoyStickPlayer(player , joystick);
        joysticClick = new JoyClickBall(ball , joystick);
        collisionPlayerBlock = new CollisionPlayerBlock(player , ball);
        collisionCompetitorBlock = new CollisionPlayerBlock(competitor, ball);
        scoreP1=0;
        scoreP2=0;
        scoreAffichP1 = new Digit(0);
        scoreAffichP2 = new Digit(0);
        setPositionScore();
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
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS : " + frames +" UPDATE : " + updates + "VAl : " + (int)(ball.getVelY()*100));
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void tick(){
        //update the game
        moveCompetitor();
        handler.tick();
        try {
            joystick.tick();
            joystickMove.tick();
            joysticClick.tick();
        }catch (Exception e){
        }
        try {
            collisionPlayerBlock.tick();
            collisionCompetitorBlock.tick();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        checkBallPos();
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

        fond(g);
        scoreAffichP1.render(g);
        scoreAffichP2.render(g);
        handler.render(g);


        g.dispose();
        bs.show();
    }

    void checkBallPos(){
        if (ball == null) return;
        if (player == null) return;
        if (ball.getX()<player.getX()+player.getWidth()/2){
            losePoint(1);
        }
        if (ball.getX()> competitor.getX()){
            losePoint(2);
        }
        return;
    }

    void losePoint(int i){
        if (i == 1){
            scoreP2++;
            scoreAffichP2.setNumberEncours(scoreP2);
            ball.setPlayer(player);
            ball.resetPosVit();
        }else {
            scoreP1++;
            scoreAffichP1.setNumberEncours(scoreP1);
            ball.setPlayer(competitor);
            ball.resetPosVit();
        }
    }

    void setPositionScore(){
        scoreAffichP1.changeSize(scoreAffichP1.getWidth() , 80);
        int x1 = this.getWidth()/2-scoreAffichP1.getWidth()-20;
        scoreAffichP1.move(x1, scoreAffichP1.getTopY());

        scoreAffichP2.changeSize(scoreAffichP2.getWidth() , scoreAffichP1.getHeight());
        int x2 = this.getWidth()/2+20;
        scoreAffichP2.move(x2, scoreAffichP1.getTopY());

    }
    void fond(Graphics g){
        int taille = 10;
        g.setColor(scoreAffichP1.getColor());
        g.fillRect((int)(this.getWidth()/2-taille/2) , scoreAffichP1.getHeight()/3-taille/2 ,taille ,taille);
        g.fillRect((int)(this.getWidth()/2-taille/2) , scoreAffichP1.getHeight()*2/3-taille/2 ,taille ,taille);

    }

    void moveCompetitor(){
        int centerBall = (int) (ball.getY()+ball.getHeight()/2);
        int centerCompetitor = (int)(competitor.getY()+competitor.getWidth()/2);
        int posXBall = (int)(ball.getX()/WIDTH);
        float alea =(float)( (Math.random()-0.5)/5);

        if (ball.getVelX()>0 && ball.getStatut() == ObjectStatut.started) {
            if (centerCompetitor + competitor.getHeight() / 2 > centerBall && competitor.getY() < ball.getY()) {
                competitor.setVelY(0);
            } else if (ball.getX() > WIDTH * (0.65+alea)) {
                if (centerBall > centerCompetitor) {
                    competitor.setVelY(2 * (1 + posXBall * 2));
                }
                if (centerBall < centerCompetitor) {
                    competitor.setVelY(-2 * (1 + posXBall * 2));
                }
            }

            if (competitor.getY() > HEIGHT - competitor.getHeight() - 40) {
                competitor.setVelY(0);
            }
        }else {
            competitor.setVelY(0);
        }
    }

}
