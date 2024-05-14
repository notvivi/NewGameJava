package entities;

import main.Game;
import utilz.SpecialHelpMethods;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.SpecialHelpMethods.*;

public abstract class Enemy extends Entity{

    protected int animationIndex;
    protected int enemyState;
    protected int enemyType;
    protected int animationTick = 25;
    protected int animationSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean enemyInAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.35f *  Game.SCALE;
    protected int walkingDirection = LEFT;
    protected int tileY;
    protected float attackRange = Game.TILES_SIZE;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        initHitBox(x,y,width,height);
        this.enemyType = enemyType;
    }

    protected void firstUpdateCheck(int[][] levelData){
        if(!isEntityOnMap(hitBox,levelData)){
            enemyInAir = true;
        }
        firstUpdate = false;
    }

    protected void updateEnemyInAir(int[][] levelData){
         if(canMoveThere(hitBox.x,hitBox.y + fallSpeed,hitBox.width,hitBox.height,levelData)){
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
         }else {
            enemyInAir = false;
            hitBox.y = getEntityYPositionInLevel(hitBox, fallSpeed);
            tileY = (int) (hitBox.y / Game.TILES_SIZE);                   
         }

    }

    protected void newState(int enemyState){
        this.enemyState = enemyState;
        animationTick = 0;
        animationIndex = 0;
    }

    protected void move(int[][] levelData){
        float xSpeed = 0;
        if(walkingDirection == LEFT){
            xSpeed = -walkSpeed;
        } else{
            xSpeed = walkSpeed;
        }
        if(canMoveThere(hitBox.x + xSpeed,hitBox.y,hitBox.width,hitBox.height,levelData)){
            if(isFloor(hitBox,xSpeed,levelData)){
                hitBox.x += xSpeed;
                return;
            }
        }

        changeWalkDirection();
    }

    protected void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= getSpriteAmount(enemyType,enemyState)){
                animationIndex = 0;
                if(enemyState == ATTACK){
                    enemyState = IDLE;
                }
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

    protected boolean canSeePlayer(int[][] levelData, Player player){
        int playerTileY = (int) (player.getHitBox().y / Game.TILES_SIZE);
        
       if(playerTileY == tileY){
           if(isPlayerInRange(player)){
                   if(SpecialHelpMethods.isSightClear(levelData,hitBox,player.hitBox,tileY)){
                    return true;
                   }
           }
       }
       return false;
    }
    protected void chasePlayer(Player player){
        if(player.hitBox.x > hitBox.x){
            walkingDirection = RIGHT;
        }else{
              walkingDirection = LEFT;
        }
    }
    protected boolean isPlayerCloseForAttack(Player player){
       int absoluteValue = (int) Math.abs(player.hitBox.x - hitBox.x);
       return absoluteValue <= attackRange;
    }


    protected boolean isPlayerInRange(Player player) {
        int absoluteValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absoluteValue <= attackRange * 5;
    }


    public int getAnimationIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
