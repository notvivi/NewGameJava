package entities;

import java.awt.*;

public abstract class Entity {

    protected float x;
    protected float y;
    protected int width, height;
    protected Rectangle hitBox;
    public Entity(float x,float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        entityHitBox();
    }

    public void entityHitBox(){
        hitBox = new Rectangle((int) x, (int) y,width,height);
    }

    public void drawHitBox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(hitBox.x,hitBox.y, hitBox.width, hitBox.height);
    }

    public void updateHitBox(){
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
}
