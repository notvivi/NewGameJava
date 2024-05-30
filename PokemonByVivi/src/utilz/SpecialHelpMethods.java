package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

/**
 * Class that stores static methods.
 */
public class SpecialHelpMethods {
    /**
     * Method that checks if player can move there.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param levelData
     * @return
     */
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

    /**
     * Method that checks if entity is walking on something.
     * @param x
     * @param y
     * @param levelData
     * @return
     */
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

        return isTileSolid((int) xIndex, (int) yIndex,levelData);

    }

    /**
     * Method that checks if tile is solid.
     * @param xTile
     * @param yTile
     * @param levelData
     * @return
     */
    public static boolean isTileSolid(int xTile, int yTile, int[][] levelData){
        int value = levelData[yTile][xTile];
        if(value >= 48 || value < 0 || value != 11){
            return true;
        }
        return false;

    }

    /**
     * Method that gets start and end, it checks if it is tile.
     * @param xStart
     * @param xEnd
     * @param y
     * @param levelData
     * @return
     */
    public static boolean isAllTiles(int xStart, int xEnd, int y, int[][] levelData){
        for(int i = 0; i < (xEnd - xStart);i++){
            if(isTileSolid(xStart + i,y,levelData)){
                return false;
            }
            if(!isTileSolid(xStart + i,y + 1,levelData)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if player is in sight.
     * @param levelData
     * @param firstHitBox
     * @param secondHitBox
     * @param tileY
     * @return
     */
    public static boolean isSightClear(int[][] levelData, Rectangle2D.Float firstHitBox, Rectangle2D.Float secondHitBox, int tileY) {
        int firstXTile = (int) (firstHitBox.x / Game.TILES_SIZE);
        int secondXTile = (int) (secondHitBox.x / Game.TILES_SIZE);

        if(firstXTile > secondXTile){
             return isAllTiles(secondXTile,firstXTile,tileY,levelData);
        }else{
            return isAllTiles(firstXTile,secondXTile,tileY,levelData);
        }
    }

    /**
     * Method that checks where currently is player or enemy.
     * @param hitBox
     * @param xSpeed
     * @return
     */
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

    /**
     * Method that checks where currently is player or enemy.
     * @param hitBox
     * @param airSpeed
     * @return
     */
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

    /**
     * Method that checks if player is on tile.
     * @param hitBox
     * @param levelData
     * @return
     */
    public static boolean isEntityOnMap(Rectangle2D.Float hitBox, int[][]levelData){
        if(!isTile(hitBox.x,hitBox.y + hitBox.height + 1, levelData)){
            if(!isTile(hitBox.x + hitBox.width,hitBox.y + hitBox.height + 1, levelData)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if player is in the map.
     * @param hitBox
     * @param xSpeed
     * @param levelData
     * @return
     */
    public static boolean isFloor(Rectangle2D.Float hitBox, float xSpeed, int[][] levelData){
       return isTile(hitBox.x + xSpeed, hitBox.y + hitBox.height + 1, levelData);
    }
}
