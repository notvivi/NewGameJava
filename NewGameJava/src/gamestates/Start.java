package gamestates;

import main.Game;
import ui.StartButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Start extends State implements MethodsForStates{
    private StartButton[] startButton = new StartButton[1];
    private BufferedImage backgroundImage;
    private BufferedImage backgroundPikachuImage;
    private int startMenuX;
    private int startMenuY;
    private int startMenuWidth;
    private int startMenuHeight;

    public Start(Game game) {
        super(game);
        loadButton();
        loadStartMenuBackground();
        backgroundPikachuImage = LoadSave.getSpriteAtlas(LoadSave.PIKACHU_MENU_BACKGROUND);
    }

    private void loadStartMenuBackground() {
        backgroundImage = LoadSave.getSpriteAtlas(LoadSave.MENU_POKEDEX);
        startMenuWidth = (int) (backgroundImage.getWidth() * Game.SCALE);
        startMenuHeight = (int) (backgroundImage.getHeight() * Game.SCALE);
        startMenuX = (int) ((int) (Game.GAME_WIDTH / 2) - startMenuWidth / 2);
        startMenuY = (int) (45 * Game.SCALE);

    }

    private void loadButton() {
        startButton[0] = new StartButton((int) (Game.GAME_WIDTH / 2.2), (int) (220 * Game.SCALE),0,GameState.MENU);
    }

   @Override
     public void update() {
   //     startButton[0].update();
     }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundPikachuImage,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.drawImage(backgroundImage, startMenuX, startMenuY, startMenuWidth, startMenuHeight,null);
        startButton[0].draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(isInButtonStart(mouseEvent,startButton[0])){
            startButton[0].setMousePressed(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
            if(isInButtonStart(mouseEvent,startButton[0])){
                if(startButton[0].isMousePressed()){
                    startButton[0].setGameState();
                }
            }
        resetButtons();
    }
    private void resetButtons() {
       startButton[0].resetBooleans();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        startButton[0].setMouseOver(false);
        if(isInButtonStart(mouseEvent,startButton[0])){
            startButton[0].setMouseOver(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
