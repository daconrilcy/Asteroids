package objects;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameObject {

    protected float x,y;
    protected float velX, velY;
    protected ID id;
    protected int height;
    protected int width;
    public LinkedList<Bound> bounds = new LinkedList<Bound>();
    protected ObjectStatut statut = ObjectStatut.stoped;
    protected int nBounds;
    protected float lastX,lastY, difX, difY;
    protected long timerPos;
    protected long lapsCaptPos = 100;
    protected GameObject player;
    public boolean isBound = false;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
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

    public void setId(ID id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void setBounds(LinkedList<Bound> bounds) {
        this.bounds = bounds;
    }

    public ObjectStatut getStatut() {
        return statut;
    }

    public void setStatut(ObjectStatut statut) {
        this.statut = statut;
    }

    public int getnBounds() {
        return nBounds;
    }

    public void setnBounds(int nBounds) {
        this.nBounds = nBounds;
    }

    public float getLastX() {
        return lastX;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }

    public float getDifX() {
        return difX;
    }

    public void setDifX(float difX) {
        this.difX = difX;
    }

    public float getDifY() {
        return difY;
    }

    public void setDifY(float difY) {
        this.difY = difY;
    }

    public GameObject getPlayer() {
        return player;
    }

    public void setPlayer(GameObject player) {
        this.player = player;
    }

    public GameObject(float x, float y , ID id){
        this.x = x;
        this.y = y;
        this.lastX=x;
        this.lastY=y;
        this.id = id;
        timerPos = System.currentTimeMillis();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void drawBounds(Graphics g);
    public abstract void start();
    protected abstract void setBounds();
    protected abstract void updateBounds();
    public abstract void resetPosVit();
    protected void capturePos(){
        if (System.currentTimeMillis()-timerPos > lapsCaptPos){
            difX = x-lastX;
            difY = y-lastY;
            timerPos = System.currentTimeMillis();
            lastX = x;
            lastY = y;
        }
    }
    protected void setABound(int x , int y , int width , int height , ID_bounds id){
        Bound bound = new Bound(x,y, width , height , id);
        this.bounds.add(bound);
        nBounds++;
    }

}
