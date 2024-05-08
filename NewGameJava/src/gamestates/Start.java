package gamestates;

import main.Game;
import ui.StartButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Start extends State implements MethodsForStates{
    private final StartButton[] startButtons = new StartButton[1];
    private BufferedImage backgroundImage;
    private final BufferedImage backgroundPikachuImage;
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
        startMenuX = (Game.GAME_WIDTH / 2) - startMenuWidth / 2;
        startMenuY = (int) (45 * Game.SCALE);

    }

    private void loadButton() {
        startButtons[0] = new StartButton((int) (Game.GAME_WIDTH / 1.99), (int) (219 * Game.SCALE),0,GameState.MENU);
    }

   @Override
     public void update() {
       for(StartButton sb: startButtons){
           sb.update();
       }

     }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundPikachuImage,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.drawImage(backgroundImage, startMenuX, startMenuY, startMenuWidth, startMenuHeight,null);
        for(StartButton sb: startButtons){
            sb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for(StartButton sb: startButtons){
            if(isInButtonStart(mouseEvent, startButtons[0])){
                sb.setMousePressed(true);
                break;
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for(StartButton sb: startButtons){
            if(isInButtonStart(mouseEvent, sb)){
                if(sb.isMousePressed()){
                    sb.setGameState();
                    break;
                }
            }
        }

        resetButtons();
    }
    private void resetButtons() {
        for(StartButton sb: startButtons){
            sb.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        for(StartButton sb: startButtons){
            sb.setMouseOver(false);
        }
        for(StartButton sb: startButtons){
            if(isInButtonStart(mouseEvent, sb)){
                sb.setMouseOver(true);
            }
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
