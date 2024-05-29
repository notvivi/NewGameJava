package entities;

import main.Game;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;

/**
 * Class that extends from Enemy and is in game as pikachu.
 */
public class Pikachu extends Enemy {

    private Rectangle2D.Float attackRangeBox;
    private int attackRangeBoxOffSetX;

    /**
     * Class constructor.
     * @param x
     * @param y
     */
    public Pikachu(float x, float y) {
        super(x, y, PIKACHU_WIDTH, PIKACHU_HEIGHT, PIKACHU);
        initHitBox(x,y,(int)(22 * Game.SCALE), (int) (19 * Game.SCALE));
        initAttackRangeBox();
    }

    /**
     *  Method that updates pikachu in game.
     * @param levelData
     * @param player
     */
    public void update(int [][] levelData, Player player){
        updateBehavior(levelData, player);
        updateAnimationTick();
        updateAttackRangeBox();

    }

    /**
     * Method that creates attack range for pikachu.
     */
    private void initAttackRangeBox() {
        attackRangeBox = new Rectangle2D.Float(x,y,(int)(82 * Game.SCALE),(int)(19 * Game.SCALE));
        attackRangeBoxOffSetX = (int)(Game.SCALE * 30);
    }

    /**
     * Method that updates what pikachu is doing in game.
     * @param levelData
     * @param player
     */
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

    /**
     * Method that updates range as pikachu walks.
     */
    private void updateAttackRangeBox() {
        attackRangeBox.x = hitBox.x - attackRangeBoxOffSetX;
        attackRangeBox.y = hitBox.y;
    }

    /**
     * Method that changes pikachus sprites as he changes directions.
     * @return
     */
    public int flipX(){
        if(walkingDirection == RIGHT){
            return width;
        }else{
            return 0;
        }
    }

    /**
     * Method that changes pikachus sprites as he changes directions.
     * @return
     */
    public int flipW(){
        if(walkingDirection == RIGHT){
            return -1;
        }else{
            return 1;
        }
    }

}
