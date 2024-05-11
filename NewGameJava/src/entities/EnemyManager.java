package entities;

import gamestates.Playing;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] crabbyArray;
    private ArrayList<Crabby> listOfCrabbies = new ArrayList<>();
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        listOfCrabbies = LoadSave.getCrabs();
        System.out.println("size of crabs: " + listOfCrabbies.size());
    }

    public void update(int[][] levelData){
        for(Crabby crabby: listOfCrabbies){
            crabby.update(levelData);
        }
    }

    public void draw(Graphics g, int xLevelOffSet){
        drawCrabs(g, xLevelOffSet);
    }

    private void drawCrabs(Graphics g, int xLevelOffSet) {
        for(Crabby crabby: listOfCrabbies){
            g.drawImage(crabbyArray[crabby.getEnemyState()][crabby.getAnimationIndex()],(int) crabby.getHitBox().x - xLevelOffSet,(int) crabby.getHitBox().y,Constants.EnemyConstants.CRABBY_WIDTH,Constants.EnemyConstants.CRABBY_HEIGHT,null);
        }
    }

    private void loadEnemyImages() {
        crabbyArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.ENEMY_CRAB_ATLAS);

        for(int i = 0; i < crabbyArray.length;i++){
            for(int j = 0; j < crabbyArray[i].length; j++){
                crabbyArray[i][j] = temp.getSubimage(j * Constants.EnemyConstants.CRABBY_WIDTH_DEFAULT,i * Constants.EnemyConstants.CRABBY_HEIGHT_DEFAULT, Constants.EnemyConstants.CRABBY_WIDTH_DEFAULT,Constants.EnemyConstants.CRABBY_HEIGHT_DEFAULT);

            }
        }
    }

}
