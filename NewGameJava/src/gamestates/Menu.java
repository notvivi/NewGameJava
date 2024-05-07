package gamestates;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements MethodsForStates {

    private MenuButton[] menuButtons = new MenuButton[3];

    private BufferedImage backgroundMenuImage;
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;


    public Menu(Game game) {
        super(game);
        loadButtons();
        loadMenuBackground();
    }

    private void loadMenuBackground() {
        backgroundMenuImage = LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundMenuImage.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundMenuImage.getHeight() * Game.SCALE);
        menuX = (int) ((int) (Game.GAME_WIDTH / 2) - menuWidth / 2);
        menuY = (int) (45 * Game.SCALE);

    }

    private void loadButtons() {
        menuButtons[0] = new MenuButton((int) (Game.GAME_WIDTH / 2.2),(int) (150 * Game.SCALE), 0, GameState.PLAYING);
        menuButtons[1] = new MenuButton((int) (Game.GAME_WIDTH / 2.2),(int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        menuButtons[2] = new MenuButton((int) (Game.GAME_WIDTH / 2.2),(int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb: menuButtons){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundMenuImage, menuX,menuY,menuWidth,menuHeight,null);
        for(MenuButton mb: menuButtons){
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for(MenuButton menuButtons: menuButtons){
            if(isInButton(mouseEvent,menuButtons)){
                menuButtons.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for(MenuButton menuBtn: menuButtons){
            if(isInButton(mouseEvent,menuBtn)){
                if(menuBtn.isMousePressed()){
                    menuBtn.setGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton menuBtn: menuButtons){
            menuBtn.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        for(MenuButton menuBtn: menuButtons){
            menuBtn.setMouseOver(false);
        }
        for(MenuButton menuBtn: menuButtons){
            if(isInButton(mouseEvent,menuBtn)){
                menuBtn.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            GameState.state = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
