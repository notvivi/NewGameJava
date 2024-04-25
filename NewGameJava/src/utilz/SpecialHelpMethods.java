package utilz;

import main.Game;

public class SpecialHelpMethods {
    public static boolean CanMoveThere(float x, float y, int width, int height, int[][] levelData){
       if(!IsTile(x,y,levelData)){
           if(!IsTile(x + width,y + height,levelData)){
               if(!IsTile(x + width,y,levelData)){
                   if(!IsTile(x,y + height,levelData)){
                        return true;
                   }
               }
           }
       }
       return false;
    }

    public static boolean IsTile(float x, float y, int[][]levelData){
        if(x < 0 || x >= Game.GAME_WIDTH){
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
}
