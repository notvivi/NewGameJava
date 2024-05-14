package entities;

import gamestates.Playing;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] pikachuArray;
    private ArrayList<Pikachu> listOfPikachu = new ArrayList<>();
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        listOfPikachu = LoadSave.getCrabs();
    }

    public void update(int[][] levelData, Player player){
        for(Pikachu pikachu : listOfPikachu){
            pikachu.update(levelData, player);
        }
    }

    public void draw(Graphics g, int xLevelOffSet){
        drawPikachu(g, xLevelOffSet);
    }

    private void drawPikachu(Graphics g, int xLevelOffSet) {
        for(Pikachu pikachu : listOfPikachu){
            g.drawImage(pikachuArray[pikachu.getEnemyState()][pikachu.getAnimationIndex()],(int) pikachu.getHitBox().x - xLevelOffSet - Constants.EnemyConstants.PIKACHU_OFFSET_X,(int) pikachu.getHitBox().y - - Constants.EnemyConstants.PIKACHU_OFFSET_Y,Constants.EnemyConstants.PIKACHU_WIDTH,Constants.EnemyConstants.PIKACHU_HEIGHT,null);
           // pikachu.drawHitBox(g, xLevelOffSet);
        }
    }

    private void loadEnemyImages() {
        pikachuArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.ENEMY_PIKACHU_ATLAS);

        for(int i = 0; i < pikachuArray.length; i++){
            for(int j = 0; j < pikachuArray[i].length; j++){
                pikachuArray[i][j] = temp.getSubimage(j * Constants.EnemyConstants.PIKACHU_WIDTH_DEFAULT,i * Constants.EnemyConstants.PIKACHU_HEIGHT_DEFAULT, Constants.EnemyConstants.PIKACHU_WIDTH_DEFAULT,Constants.EnemyConstants.PIKACHU_HEIGHT_DEFAULT);

            }
        }
    }

}
