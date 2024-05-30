package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Class that everyone in game is extending from.
 */
public abstract class Entity {

    protected float x;
    protected float y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    /**
     * Class constructor.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Entity(float x,float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox(x, y, width, height);
    }

    /**
     * Method that creates rectangle as a hitbox.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void initHitBox(float x, float y, int width, int height){
        hitBox = new Rectangle2D.Float(x, y,width,height);
    }

    /**
     * Method that draws hitbox.
     * @param g
     * @param xLevelOffSet
     */
    public void drawHitBox(Graphics g,int xLevelOffSet){
        g.setColor(Color.pink);
        g.drawRect( (int) (hitBox.x - xLevelOffSet), (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);

    }

    /**
     * Method that returns hitbox.
     * @return
     */
    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    /**
     * Method that returns y.
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Method that returns x.
     * @return
     */
    public float getX() {
        return x;
    }
}
