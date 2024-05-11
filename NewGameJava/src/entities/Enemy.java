package entities;

import main.Game;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
public abstract class Enemy extends Entity{

    protected int animationIndex;
    protected int enemyState;
    protected int enemyType;
    protected int animationTick = 25;
    protected int animationSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean enemyInAir =  false;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.5f *  Game.SCALE;
    protected int walkingDirection = LEFT;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        initHitBox(x,y,width,height);
        this.enemyType = enemyType;
    }
    protected void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= getSpriteAmount(enemyType,enemyState)){
                animationIndex = 0;
            }
        }
    }

    protected void changeWalkDirection() {
        if(walkingDirection == LEFT){
            walkingDirection = RIGHT;
        }else{
            walkingDirection = LEFT;
        }
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
