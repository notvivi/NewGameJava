package entities;

import main.Game;

import static utilz.Constants.EnemyConstants.*;

public class Crabby extends Enemy {


    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY );
        initHitBox(x,y,(int)(22 * Game.SCALE), (int) (19 * Game.SCALE));
    }

    private void updateMoving(int [][] levelData, Player player){
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

            }
        }
    }

    public void update(int [][] levelData, Player player){
        updateMoving(levelData, player);
        updateAnimationTick();

    }

}
