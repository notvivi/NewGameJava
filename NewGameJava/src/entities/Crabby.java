package entities;

import main.Game;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.SpecialHelpMethods.*;

public class Crabby extends Enemy {


    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY );
        initHitBox(x,y,(int)(22 * Game.SCALE), (int) (27 * Game.SCALE));
    }
    private void updateMoving(int [][] levelData){
        if(firstUpdate){
            if(!isEntityOnMap(hitBox,levelData)){
                enemyInAir = true;
                firstUpdate = false;
            }
        }

        if(enemyInAir){
            if(canMoveThere(hitBox.x,hitBox.y + fallSpeed,hitBox.width,hitBox.height,levelData)){
                hitBox.y += fallSpeed;
                fallSpeed += gravity;
            }else {
                enemyInAir = false;
                hitBox.y = getEntityYPositionInLevel(hitBox, fallSpeed);
            }
        }else{
            switch (enemyState){
                case IDLE:
                    enemyState = RUNNING;
                    break;
                case RUNNING:
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
                    break;
            }
        }
    }
    public void update(int [][] levelData){
        updateMoving(levelData);
        updateAnimationTick();

    }

}
