package levels;

/**
 * Class for level.
 */
public class Level {
    private int[][] levelData;

    /**
     * Class constructor.
     * @param levelData
     */
    public Level(int[][] levelData){
        this.levelData = levelData;
    }

    /**
     * Method that returns sprite index.
     * @param x
     * @param y
     * @return
     */
    public int getSpriteIndex(int x, int y){
        return levelData[y][x];
    }

    /**
     * Method that returns level data.
     * @return
     */
    public int[][] getLevelData(){
        return levelData;
    }
}
