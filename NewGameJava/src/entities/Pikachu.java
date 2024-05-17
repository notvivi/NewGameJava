package entities;

import main.Game;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;

public class Pikachu extends Enemy {

    private Rectangle2D.Float attackRangeBox;
    private int attackRangeBoxOffSetX;

    public Pikachu(float x, float y) {
        super(x, y, PIKACHU_WIDTH, PIKACHU_HEIGHT, PIKACHU);
        initHitBox(x,y,(int)(22 * Game.SCALE), (int) (19 * Game.SCALE));
        initAttackRangeBox();
    }

    public void update(int [][] levelData, Player player){
        updateBehavior(levelData, player);
        updateAnimationTick();
        updateAttackRangeBox();

    }

    public void drawAttackBox(Graphics g, int xLevelOffSet){
        g.setColor(ColorUIResource.red);
        g.drawRect((int) (attackRangeBox.x - xLevelOffSet), (int) attackRangeBox.y, (int) attackRangeBox.width, (int) attackRangeBox.height);
    }

    private void initAttackRangeBox() {
        attackRangeBox = new Rectangle2D.Float(x,y,(int)(82 * Game.SCALE),(int)(19 * Game.SCALE));
        attackRangeBoxOffSetX = (int)(Game.SCALE * 30);
    }

    private void updateBehavior(int [][] levelData, Player player){
        if(firstUpdate){
            firstUpdateCheck(levelData);
        }
        if(enemyInAir){
            updateEnemyInAir(levelData);
        }else{
            switch (enemyState){
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if(canSeePlayer(levelData,player)){
                        chasePlayer(player);
                    }
                    if (isPlayerCloseForAttack(player)) {
                       newState(ATTACK);
                    }
                   move(levelData);
                   break;
                case ATTACK:
                    if(animationIndex == 0){
                        attackCheck = false;
                    }
                    if(animationIndex == 3 && !attackCheck){
                        checkPlayerHit(attackRangeBox,player);
                    }
                    break;
                case HIT:
                    break;

            }
        }
    }

    private void updateAttackRangeBox() {
        attackRangeBox.x = hitBox.x - attackRangeBoxOffSetX;
        attackRangeBox.y = hitBox.y;
    }

    public int flipX(){
        if(walkingDirection == RIGHT){
            return width;
        }else{
            return 0;
        }
    }
    public int flipW(){
        if(walkingDirection == RIGHT){
            return -1;
        }else{
            return 1;
        }
    }

}
