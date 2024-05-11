package gamestates;

import main.Game;
import ui.StoryButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Story extends State implements MethodsForStates {
    private StoryButton[] storyButtons = new StoryButton[1];
    private BufferedImage backgroundMenuImage;
    private final BufferedImage backgroundPikachuImage;
    private final BufferedImage pokemonTitleImage;
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;

    public Story(Game game) {
        super(game);
        loadButtons();
        loadMenuBackground();
        backgroundPikachuImage = LoadSave.getSpriteAtlas(LoadSave.PIKACHU_MENU_BACKGROUND);
        pokemonTitleImage = LoadSave.getSpriteAtlas(LoadSave.POKEMON_TITLE);
    }
    private void loadMenuBackground() {
        backgroundMenuImage = LoadSave.getSpriteAtlas(LoadSave.MENU_POKEDEX_2);
        menuWidth = (int) (backgroundMenuImage.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundMenuImage.getHeight() * Game.SCALE);
        menuX = (int) ((int) (Game.GAME_WIDTH / 2) - menuWidth / 2);
        menuY = (int) (45 * Game.SCALE);

    }
    private void loadButtons() {
        storyButtons[0] = new StoryButton((int) (Game.GAME_WIDTH / 2),(int) (150 * Game.SCALE), 0, GameState.PLAYING);
    }


    @Override
    public void update() {
        for(StoryButton sb: storyButtons){
            sb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundPikachuImage,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.drawImage(pokemonTitleImage,0,0,Game.GAME_WIDTH,200,null);
        g.drawImage(backgroundMenuImage, menuX,menuY,menuWidth,menuHeight,null);
        for(StoryButton sb: storyButtons){
            sb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for(StoryButton sb: storyButtons){
            if(isInButtonStory(mouseEvent,sb)){
                sb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for(StoryButton sb: storyButtons){
            if(isInButtonStory(mouseEvent,sb)){
                if(sb.isMousePressed()){
                    sb.setGameState();
                    break;
                }
            }
        }
        resetButtons();
    }
    private void resetButtons() {
        for(StoryButton sb: storyButtons){
            sb.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        for(StoryButton sb: storyButtons){
            sb.setMouseOver(false);
        }
        for(StoryButton sb: storyButtons){
            if(isInButtonStory(mouseEvent,sb)){
                sb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
