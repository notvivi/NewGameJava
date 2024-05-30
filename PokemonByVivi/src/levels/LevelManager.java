package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that loads and draws level data.
 */
public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    /**
     * Class constructor.
     * @param game
     */
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.getDataAboutLevel());
    }

    /**
     * Method that loads level data.
     */
    private void importOutsideSprites() {
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for(int i = 0; i < 4;i++){
            for(int j = 0; j < 12;j++){
                int index =  i*12 + j;
                levelSprite[index] = image.getSubimage(j*32,i*32,32,32);
            }
        }
    }

    /**
     * Method that draws level data.
     * @param g
     * @param levelOffSet
     */
    public void draw(Graphics g, int levelOffSet){
        for(int i = 0; i < Game.TILES_IN_HEIGHT;i++){
            for(int j = 0; j < levelOne.getLevelData()[0].length;j++){
                int index = levelOne.getSpriteIndex(j,i);
                g.drawImage(levelSprite[index],j*Game.TILES_SIZE - levelOffSet,i* Game.TILES_SIZE,Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
        }
    }

    /**
     * Method that returns current level.
     * @return
     */
    public Level getCurrentLevel(){
        return levelOne;
    }

    /**
     * Nothing.
     */
    public void update() {
    }
}
