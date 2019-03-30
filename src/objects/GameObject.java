package objects;


import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    float x,y;
    float velX, velY;
    private ID id;
    int height;
    protected int width;
    LinkedList<Bound> bounds = new LinkedList<>();
    ObjectStatut statut = ObjectStatut.stoped;
    int nBounds;
    private float lastY;
    private float difY;
    private long timerPos;
    private long LAPS;
    GameObject player;
    public boolean isBound = false;

    public float getX() {
        return x;
    }

    void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public LinkedList<Bound> getBounds() {
        return bounds;
    }

    public ObjectStatut getStatut() {
        return statut;
    }

    public float getDifY() {
        return difY;
    }

    public void setPlayer(GameObject player) {
        this.player = player;
    }

    public GameObject(float x, float y , ID id){
        this.x = x;
        this.y = y;
        this.lastY=y;
        this.id = id;
        timerPos = System.currentTimeMillis();
        LAPS = 100;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void drawBounds(Graphics g);
    public abstract void start();
    protected abstract void setBounds();
    protected abstract void updateBounds();
    public abstract void resetPosVit();
    void capturePos(){
        if (System.currentTimeMillis()-timerPos > LAPS){
            difY = y-lastY;
            timerPos = System.currentTimeMillis();
            lastY = y;
        }
    }
    void setABound(int x , int y , int width , int height , ID_bounds id){
        Bound bound = new Bound(x,y, width , height , id);
        this.bounds.add(bound);
        nBounds++;
    }

}
