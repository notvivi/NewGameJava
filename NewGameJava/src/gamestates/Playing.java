package gamestates;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.GameoverOverlay;
import utilz.LoadSave;
import utilz.Constants.Environment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Playing extends State implements MethodsForStates{

    Random random = new Random();

    private LevelManager levelManager;
    private Player player;
    private EnemyManager enemyManager;
    private GameoverOverlay gameoverOverlay;

    private int xLevelOffSet = 0;
    private final int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private final int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private final int levelTilesWide = LoadSave.getDataAboutLevel()[0].length;
    private final int maxTilesOffSet = levelTilesWide - Game.TILES_IN_WIDTH;
    private final int maxLevelOffSetX = maxTilesOffSet * Game.TILES_SIZE;

    private final BufferedImage backgroundLevel;
    private final BufferedImage bigClouds;
    private final BufferedImage smallCloud;
    private int[]smallCloudsPosition;

    private boolean gameOver = false;



    public Playing(Game game) {
        super(game);
        initClasses(game);

        backgroundLevel = LoadSave.getSpriteAtlas(LoadSave.LEVEL_BACKGROUND);
        bigClouds = LoadSave.getSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.getSpriteAtlas(LoadSave.SMALL_CLOUD);
        smallCloudsPosition = new int[8];
        for(int i = 0; i < smallCloudsPosition.length; i++){
            smallCloudsPosition[i] = (int) (90 * Game.SCALE) +random.nextInt((int) (100 * Game.SCALE));
        }

    }

    public void checkEnemyHit(Rectangle2D.Float attackRangeBox){
        enemyManager.checkEnemyHit(attackRangeBox);
    }

    private void initClasses(Game game) {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(200,200,(int)(64 * Game.SCALE),(int)(40 * Game.SCALE), this);
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        gameoverOverlay = new GameoverOverlay(this);

    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }
    public Player getPlayer(){
        return player;
    }


    @Override
    public void update() {
        if(!gameOver){
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            checkCloseToBorder();
        }
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitBox().x;
        int difference = playerX - xLevelOffSet;

        if(difference > rightBorder){
            xLevelOffSet += difference - rightBorder;
        } else if (difference < leftBorder) {
            xLevelOffSet += difference - leftBorder;
        }

        if(xLevelOffSet > maxLevelOffSetX){
            xLevelOffSet = maxLevelOffSetX;
            
        } else if (xLevelOffSet < 0) {
            xLevelOffSet = 0;
            
        }

    }

    @Override
    public void draw(Graphics g) {
        if(gameOver){
            gameoverOverlay.draw(g);
        }else{
            g.drawImage(backgroundLevel,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
            drawClouds(g);
            enemyManager.draw(g, xLevelOffSet);
            levelManager.draw(g,xLevelOffSet);
            player.render(g,xLevelOffSet);
        }
    }

    private void drawClouds(Graphics g) {
        for(int i = 0; i < 3;i++){
            g.drawImage(bigClouds, i * Environment.BIG_CLOUDS_WIDTH - (int) (xLevelOffSet * 0.3),(int) (204 * Game.SCALE), Environment.BIG_CLOUDS_WIDTH, Environment.BIG_CLOUDS_HEIGHT,null);
        }
        for(int i = 0; i < smallCloudsPosition.length; i++){
            g.drawImage(smallCloud,Environment.SMALL_CLOUDS_WIDTH * 4 * i - (int) (xLevelOffSet * 0.7),smallCloudsPosition[i],Environment.SMALL_CLOUDS_WIDTH,Environment.SMALL_CLOUDS_HEIGHT,null);
        }
    }

    public void resetAll(){
        gameOver = false;
        player.resetAll();
        enemyManager.resetEverything();
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(!gameOver){
            if(mouseEvent.getButton() == MouseEvent.BUTTON1){
                player.setAttack(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(gameOver){
            gameoverOverlay.keyPressed(keyEvent);
        }else{
            switch (keyEvent.getKeyCode()){
                case KeyEvent.VK_A:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_S:
                    player.setDown(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
                    break;
                case KeyEvent.VK_W:
                    player.setUp(true);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setJump(true);
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    GameState.state = GameState.MENU;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(!gameOver){
            switch (keyEvent.getKeyCode()){
                case KeyEvent.VK_A:
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_S:
                    player.setDown(false);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(false);
                    break;
                case KeyEvent.VK_W:
                    player.setUp(false);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setJump(false);
                    break;

            }
        }

    }
}
