package entities;

import gamestates.Playing;
import static utilz.Constants.EnemyConstants.*;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that adds enemies to game.
 */
public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] pikachuArray;
    private ArrayList<Pikachu> listOfPikachu = new ArrayList<>();

    /**
     * Class constructor.
     * @param playing
     */
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
    }

    /**
     * Method that loads enemies to arraylist.
     */
    private void addEnemies() {
        listOfPikachu = LoadSave.getPikachus();
    }

    /**
     * Method that updates enemies.
     * @param levelData
     * @param player
     */
    public void update(int[][] levelData, Player player){
        for(Pikachu pikachu : listOfPikachu){
            pikachu.update(levelData, player);
        }
    }

    /**
     * Method that draws enemies.
     * @param g
     * @param xLevelOffSet
     */
    public void draw(Graphics g, int xLevelOffSet){
        drawPikachu(g, xLevelOffSet);
    }

    /**
     * Method that draws pikachu.
     * @param g
     * @param xLevelOffSet
     */
    private void drawPikachu(Graphics g, int xLevelOffSet) {
        for(Pikachu pikachu : listOfPikachu){
            if(pikachu.isActive()){
                g.drawImage(pikachuArray[pikachu.getEnemyState()][pikachu.getAnimationIndex()],(int) pikachu.getHitBox().x - xLevelOffSet - PIKACHU_OFFSET_X + pikachu.flipX(),(int) pikachu.getHitBox().y - PIKACHU_OFFSET_Y,PIKACHU_WIDTH * pikachu.flipW(),PIKACHU_HEIGHT,null);
            }
        }
    }

    /**
     *  Method that resets every enemy in game.
     */
    public void resetEverything(){
        for(Pikachu pikachu: listOfPikachu){
            pikachu.resetEnemy();
        }
    }

    /**
     * Method that checks if player hit enemy.
     * @param attackRangeBox
     */
    public void checkEnemyHit(Rectangle2D.Float attackRangeBox){
        for(Pikachu pikachu: listOfPikachu){
            if(pikachu.isActive()){
                if(attackRangeBox.intersects(pikachu.hitBox)){
                    pikachu.hurt(10);
                    return;
                }
            }
        }
    }

    /**
     * Method that loads enemy images.
     */
    private void loadEnemyImages() {
        pikachuArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.ENEMY_PIKACHU_ATLAS);

        for(int i = 0; i < pikachuArray.length; i++){
            for(int j = 0; j < pikachuArray[i].length; j++){
                pikachuArray[i][j] = temp.getSubimage(j * PIKACHU_WIDTH_DEFAULT,i * PIKACHU_HEIGHT_DEFAULT, PIKACHU_WIDTH_DEFAULT,PIKACHU_HEIGHT_DEFAULT);

            }
        }
    }

}
