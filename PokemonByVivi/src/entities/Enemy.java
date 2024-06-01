package entities;

import main.Game;
import utilz.SpecialHelpMethods;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.SpecialHelpMethods.*;

/**
 * Class that enemies are extending from
 */
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
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackCheck;

    /**
     * Class constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param enemyType
     */
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        initHitBox(x,y,width,height);
        this.enemyType = enemyType;
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    /**
     * Method that checks if enemy is on the map.
     * @param levelData
     */
    protected void firstUpdateCheck(int[][] levelData){
        if(!isEntityOnMap(hitBox,levelData)){
            enemyInAir = true;
        }
        firstUpdate = false;
    }

    /**
     * Method that checks if enemy is in the air.
     * @param levelData
     */
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

    /**
     * Method that resets enemy state to zero.
     * @param enemyState
     */
    protected void newState(int enemyState){
        this.enemyState = enemyState;
        animationTick = 0;
        animationIndex = 0;
    }

    /**
     * Method that checks walking
     * @param levelData
     */
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

    /**
     * Method that checks if enemy got minus hp.
     * @param damage
     */
    protected void hurt(int damage){
        currentHealth -= damage;
        if(currentHealth <= 0){
            newState(DEAD);
        }else{
            newState(HIT);
        }
    }

    /**
     * Method that checks if player got hit by enemy.
     * @param attackRangeBox
     * @param player
     */
    protected void checkPlayerHit(Rectangle2D.Float attackRangeBox, Player player){
        if(attackRangeBox.intersects(player.hitBox)){
            player.updateHealth(-getEnemyDamage(enemyType));
        }
        attackCheck = true;
    }

    /**
     * Method that resets enemy after player dies.
     */
    protected void resetEnemy(){
        hitBox.x = x;
        hitBox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
        fallSpeed = 0;
    }

    /**
     * Method that loops through enemy image and changes enemy state.
     * Source: https://www.youtube.com/@KaarinGaming
     */
    protected void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= getSpriteAmount(enemyType,enemyState)){
                animationIndex = 0;
                switch (enemyState){
                    case ATTACK, HIT -> enemyState = IDLE;
                    case DEAD -> active = false;
                }
            }
        }
    }

    /**
     * Method that changes walking direction.
     */
    protected void changeWalkDirection() {
        if(walkingDirection == LEFT){
            walkingDirection = RIGHT;
        }else{
            walkingDirection = LEFT;
        }
    }

    /**
     * Method that checks if player is in range of an enemy.
     * @param levelData
     * @param player
     * @return
     */
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

    /**
     * Method that checks if player hitbox is in enemy hitbox.
     * @param player
     */
    protected void chasePlayer(Player player){
        if(player.hitBox.x > hitBox.x){
            walkingDirection = RIGHT;
        }else{
              walkingDirection = LEFT;
        }
    }

    /**
     * Method that checks if player is in range for an attack.
     * @param player
     * @return
     */
    protected boolean isPlayerCloseForAttack(Player player){
       int absoluteValue = (int) Math.abs(player.hitBox.x - hitBox.x);
       return absoluteValue <= attackRange;
    }

    /**
     * Method that checks if player is in range.
     * @param player
     * @return
     */
    protected boolean isPlayerInRange(Player player) {
        int absoluteValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absoluteValue <= attackRange * 5;
    }

    /**
     * Method that returns animation index.
     * @return
     */
    public int getAnimationIndex() {
        return animationIndex;
    }

    /**
     * Method that returns enemy state.
     * @return
     */
    public int getEnemyState() {
        return enemyState;
    }

    /**
     * Method that returns if enemy is alive.
     * @return
     */
    public boolean isActive() {
        return active;
    }
}
