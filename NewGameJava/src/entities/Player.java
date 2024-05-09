package entities;

import main.Game;
import utilz.LoadSave;
import utilz.SpecialHelpMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;

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


    private float speedInAir = 0f;
    private float speedInWater = 0f;
    private float earthGravity = 0.05f * Game.SCALE;
    private float jumpSpeed = -2.5f * Game.SCALE;
    private float fallSpeedAfterHit = 0.5f * Game.SCALE;
    private boolean PlayerInAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, (int) (20*Game.SCALE), (int) (27*Game.SCALE));
    }

    public void update() {
        updatePosition();
        updateAnimatonTick();
        setAnimation();
    }
    public void render(Graphics g, int levelOffSet){
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffSet) - levelOffSet, (int) (hitBox.y - yDrawOffSet),width,height,null);
     //   drawHitBox(g);

    }
    private void updateAnimatonTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        int startAni = playerAction;
        if(moving){
            playerAction = RUNNING;
        }else{
            playerAction = IDLE;
        }
        if(PlayerInAir){
            if(speedInAir < 0){
                playerAction = JUMP;
            }else{
                playerAction = FALLING;
            }
        }
        if(attacking){
            playerAction = ATTACK_1;
        }
        if(startAni != playerAction){
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePosition() {
        float xSpeed = 0;
        moving = false;
        if(jump){
            jump();
        }

        if(!PlayerInAir){
            if((!left && !right) || (right && left)){
                return;
            }
        }
        if (left) {
            xSpeed -= playerSpeed;
        }
        if(right){
            xSpeed += playerSpeed;
        }

        if(!PlayerInAir){
           if(!SpecialHelpMethods.isEntityOnMap(hitBox,levelData)){
                PlayerInAir = true;
           }
        }

        if(PlayerInAir){
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

    private void jump() {
        if(PlayerInAir){
            return;
        }
        PlayerInAir = true;
        speedInAir = jumpSpeed;
    }

    private void resetInAir() {
        PlayerInAir = false;
        speedInAir = 0;
    }

    private void updateXPosition(float xSpeed) {
        if(SpecialHelpMethods.canMoveThere(hitBox.x + xSpeed,hitBox.y, hitBox.width, hitBox.height, levelData)){
            this.hitBox.x += xSpeed;
        }else{
            hitBox.x = SpecialHelpMethods.getEntityXPositionNextToWall(hitBox,xSpeed);
        }
    }

    private void loadAnimations() {
            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[9][6];
            for (int j = 0;j < animations.length;j++){
                for(int i = 0; i < animations[j].length;i++){
                    animations[j][i] = img.getSubimage(i*64,j*40,64,40);
                }
            }

    }
    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
        if(!SpecialHelpMethods.isEntityOnMap(hitBox,levelData)){
            PlayerInAir = true;
        }
    }
    public void resetDirectionBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
