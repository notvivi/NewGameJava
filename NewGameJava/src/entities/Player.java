package entities;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import utilz.SpecialHelpMethods;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;

/**
 * Class that creates whole player in game.
 */
public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private final float  playerSpeed = 2.5f;
    private int[][] levelData;
    private float xDrawOffSet = 21 * Game.SCALE;
    private float yDrawOffSet = 4 * Game.SCALE;

    private BufferedImage statusBarImage;

    private int statusBarWidth = (int)(192 * Game.SCALE);
    private int statusBarHeight = (int)(58 * Game.SCALE);
    private int statusBarX = (int)(10 * Game.SCALE);
    private int statusBarY = (int)(10 * Game.SCALE);

    private int healthBarWidth = (int)(150 * Game.SCALE);
    private int healthBarHeight = (int)(6 * Game.SCALE);
    private int healthBarXStart = (int)(34 * Game.SCALE);
    private int healthBarYStart = (int)(14 * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;
    private boolean attackCheck;

    private Rectangle2D.Float attackRangeBox;

    private int flipX = 0;
    private int flipW = 1;


    private float speedInAir = 0f;
    private float earthGravity = 0.05f * Game.SCALE;
    private float jumpSpeed = -2.5f * Game.SCALE;
    private float fallSpeedAfterHit = 0.5f * Game.SCALE;
    private boolean playerInAir = false;

    private Playing playing;

    /**
     * Class constructor.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param playing
     */
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, (int) (20*Game.SCALE), (int) (27*Game.SCALE));
        initAttackRangeBox();
        this.playing = playing;
    }

    /**
     * Method that updates players behavior in game.
     */
    public void update() {
        changeHealth();
        if(currentHealth <= 0){
            playing.setGameOver(true);
            return;
        }
        updateAttackRangeBox();

        if(attacking){
            checkAttack();
        }
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    /**
     * Method that resets everything after player dies.
     */
    public void resetAll(){
        resetDirectionBooleans();
        playerInAir = false;
        attacking = false;
        moving = false;
        playerAction = IDLE;
        currentHealth = maxHealth;

        hitBox.x = x;
        hitBox.y = y;

        if(!SpecialHelpMethods.isEntityOnMap(hitBox,levelData)){
            playerInAir = true;
        }
    }

    /**
     * Method that checks if player can attack enemy.
     */
    private void checkAttack() {
        if(attackCheck || aniIndex != 1){
            return;
        }
        attackCheck = true;
        playing.checkEnemyHit(attackRangeBox);
    }

    /**
     * Method that draws player.
     * @param g
     * @param levelOffSet
     */
    public void render(Graphics g, int levelOffSet){
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffSet) - levelOffSet + flipX, (int) (hitBox.y - yDrawOffSet) ,width * flipW,height,null);
        drawUI(g);
    }

    /**
     * Method that draws health bar.
     * @param g
     */
    private void drawUI(Graphics g) {
       g.drawImage(statusBarImage,statusBarX,statusBarY,statusBarWidth,statusBarHeight,null);
       Color hpGreen = new Color(50,201,56,255);
       g.setColor(hpGreen);
       g.fillRect(healthBarXStart + statusBarX,healthBarYStart + statusBarY,healthWidth,healthBarHeight);
    }

    /**
     * Method that changes health as enemies are hitting player.
     */
    private void changeHealth() {
        healthWidth = (int)((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    /**
     * Method that updates players range.
     */
    private void updateAttackRangeBox() {
        if(right){
            attackRangeBox.x = hitBox.x + hitBox.width + (int)(Game.SCALE * 10);
        } else if (left) {
            attackRangeBox.x = hitBox.x - hitBox.width - (int)(Game.SCALE * 10);
        }
        attackRangeBox.y = hitBox.y + (Game.SCALE * 10);
    }

    /**
     * Method that creates players range.
     */
    private void initAttackRangeBox() {
        attackRangeBox = new Rectangle2D.Float(x,y,(int)(20 * Game.SCALE), (int)(20 * Game.SCALE));
    }

    /**
     * Method that ticks through player sprites.
     */
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
                attackCheck = false;
            }
        }
    }

    /**
     * Method that sets animation of a player.
     */
    private void setAnimation() {

        int startAni = playerAction;
        if(moving){
            playerAction = RUNNING;
        }else{
            playerAction = IDLE;
        }
        if(playerInAir){
            if(speedInAir < 0){
                playerAction = JUMP;
            }else{
                playerAction = FALLING;
            }
        }
        if(attacking){
            playerAction = ATTACK;
            if(startAni != ATTACK){
                aniIndex = 1;
                aniTick = 0;
                return;
            }
        }
        if(startAni != playerAction){
            resetAnimationTick();
        }
    }

    /**
     * Method that resets players animation tick and index.
     */
    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Method that checks where is player and updates his gravity.
     */
    private void updatePosition() {
        float xSpeed = 0;
        moving = false;
        if(jump){
            jump();
        }

        if(!playerInAir){
            if((!left && !right) || (right && left)){
                return;
            }
        }
        if (left) {
            xSpeed -= playerSpeed;
            flipX = width;
            flipW = -1;
        }
        if(right){
            xSpeed += playerSpeed;
            flipX = 0;
            flipW = 1;
        }

        if(!playerInAir){
           if(!SpecialHelpMethods.isEntityOnMap(hitBox,levelData)){
                playerInAir = true;
           }
        }

        if(playerInAir){
            if(SpecialHelpMethods.canMoveThere(hitBox.x,hitBox.y + speedInAir, hitBox.width,hitBox.height,levelData)){
                hitBox.y += speedInAir;
                speedInAir += earthGravity;
                updateXPosition(xSpeed);
            }else{
                hitBox.y = SpecialHelpMethods.getEntityYPositionInLevel(hitBox,speedInAir);
                if(speedInAir > 0){
                    resetInAir();
                }else{
                    speedInAir = fallSpeedAfterHit;
                }
                updateXPosition(xSpeed);
            }
        }else{
            updateXPosition(xSpeed);
        }
        moving = true;
    }

    /**
     * Method that checks if player is jumping.
     */
    private void jump() {
        if(playerInAir){
            return;
        }
        playerInAir = true;
        speedInAir = jumpSpeed;
    }

    /**
     * Method that resets player in air.
     */
    private void resetInAir() {
        playerInAir = false;
        speedInAir = 0;
    }

    /**
     * Method that updates, where currently player is.
     * @param xSpeed
     */
    private void updateXPosition(float xSpeed) {
        if(SpecialHelpMethods.canMoveThere(hitBox.x + xSpeed,hitBox.y, hitBox.width, hitBox.height, levelData)){
            this.hitBox.x += xSpeed;
        }else{
            hitBox.x = SpecialHelpMethods.getEntityXPositionNextToWall(hitBox,xSpeed);
        }
    }

    /**
     * Method that updates players health.
     * @param lostHealth
     */
    public void updateHealth(int lostHealth){
        currentHealth += lostHealth;
        if(currentHealth <= 0){
            currentHealth = 0;
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    /**
     * Method that loads player animations and health bar.
     */
    private void loadAnimations() {
            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[7][8];
            for (int j = 0;j < animations.length;j++){
                for(int i = 0; i < animations[j].length;i++){
                    animations[j][i] = img.getSubimage(i*64,j*40,64,40);
                }
            }

            statusBarImage = LoadSave.getSpriteAtlas(LoadSave.HEALTH_BAR);


    }

    /**
     * Method that loads level data.
     * @param levelData
     */
    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
        if(!SpecialHelpMethods.isEntityOnMap(hitBox,levelData)){
            playerInAir = true;
        }
    }

    /**
     * Method that resets players direction.
     */
    public void resetDirectionBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    /**
     * Method that sets if player is attacking.
     * @param attacking
     */
    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    /**
     * Method that sets if player moves left.
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Method that returns if player is moving up.
     * @return
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Method that sets if player moves up.
     * @param up
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Method that sets if player moves right.
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Method that sets if player moves down
     * @param down
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Method that sets if player is jumping.
     * @param jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
