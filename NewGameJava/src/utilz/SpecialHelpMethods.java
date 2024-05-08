package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class SpecialHelpMethods {
    public static boolean canMoveThere(float x, float y, float width, float height, int[][] levelData){
       if(!isTile(x,y,levelData)){
           if(!isTile(x + width,y + height,levelData)){
               if(!isTile(x + width,y,levelData)){
                   if(!isTile(x,y + height,levelData)){
                        return true;
                   }
               }
           }
       }
       return false;
    }

    public static boolean isTile(float x, float y, int[][]levelData){
        int maxWidth = levelData[0].length * Game.TILES_SIZE;
        if(x < 0 || x >= maxWidth){
            return true;
        }
        if(y < 0 || y >= Game.GAME_HEIGHT){
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        if(value >= 48 || value < 0 || value != 11){
            return true;
        }
        return false;

    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile = (int)(hitBox.x / Game.TILES_SIZE);
        if( xSpeed > 0){
            int tileXPosition = currentTile * Game.TILES_SIZE;
            int xOffSet = (int)(Game.TILES_SIZE - hitBox.width);
            return tileXPosition + xOffSet -1;
        }else{
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPositionInLevel(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int)(hitBox.y / Game.TILES_SIZE);
        if( airSpeed > 0){
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int yOffSet = (int)(Game.TILES_SIZE - hitBox.height);
            return tileYPosition + yOffSet -1;
        }else{
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean isEntityOnMap(Rectangle2D.Float hitBox, int[][]levelData){
        if(!isTile(hitBox.x,hitBox.y + hitBox.height + 1, levelData)){
            if(!isTile(hitBox.x,hitBox.y + hitBox.width + 1, levelData)){
                return false;
            }
        }
        return true;
    }
}
