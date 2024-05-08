package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements MethodsForStates{

    private LevelManager levelManager;
    private Player player;

    private int xLevelOffSet = 0;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int levelTilesWide = LoadSave.getDataAboutLevel()[0].length;
    private int maxTilesOffSet = levelTilesWide - Game.TILES_IN_WIDTH;
    private int maxLevelOffSetX = maxTilesOffSet * Game.TILES_SIZE;


    public Playing(Game game) {
        super(game);
        initClasses(game);
    }

    private void initClasses(Game game) {
        levelManager = new LevelManager(game);
        player = new Player(200,200,(int)(64 * Game.SCALE),(int)(40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());

    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }
    public Player getPlayer(){
        return player;
    }


    @Override
    public void update() {
        levelManager.update();
        player.update();
        checkCloseToBorder();
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
        levelManager.draw(g,xLevelOffSet);
        player.render(g,xLevelOffSet);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){
            player.setAttack(true);
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

    @Override
    public void keyReleased(KeyEvent keyEvent) {
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
