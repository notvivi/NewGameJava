package entities;

import static utilz.Constants.EnemyConstants.*;
public abstract class Enemy extends Entity{

    private int animationIndex;
    private int enemyState;
    private int enemyType;
    private int animationTick = 25;
    private int animationSpeed = 25;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        initHitBox(x,y,width,height);
        this.enemyType = enemyType;
    }
    private void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= getSpriteAmount(enemyType,enemyState)){
                animationIndex = 0;
            }
        }
    }
    public void update(){
        updateAnimationTick();
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
