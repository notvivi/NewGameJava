package gamestates;

import inputs.TextReader;
import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements MethodsForStates {

    private TextReader textReader = new TextReader();
    private MenuButton[] menuButtons = new MenuButton[2];
    private Font monospacedBold = new Font(Font.MONOSPACED, Font.BOLD, 25);

    private BufferedImage backgroundMenuImage;
    private final BufferedImage backgroundPikachuImage;
    private final BufferedImage pokemonTitleImage;
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;


    public Menu(Game game) {
        super(game);
        loadButtons();
        loadMenuBackground();
        loadArraylist();
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
        menuButtons[0] = new MenuButton((int) (Game.GAME_WIDTH / 2.2),(int) (150 * Game.SCALE), 0, GameState.STORY);
        menuButtons[1] = new MenuButton((int) (Game.GAME_WIDTH / 2.2),(int) (220 * Game.SCALE), 1, GameState.OPTIONS);
    }

    @Override
    public void update() {
        for(MenuButton mb: menuButtons){
            mb.update();
        }
    }
    public void loadArraylist(){
        textReader.read(textReader.getFUTURE_PLANS());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundPikachuImage,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.drawImage(pokemonTitleImage,0,0,Game.GAME_WIDTH,200,null);
        g.drawImage(backgroundMenuImage, menuX,menuY,menuWidth,menuHeight,null);

        g.setFont(monospacedBold);
        FontMetrics fm = g.getFontMetrics(g.getFont());

        for(int i = 0; i < textReader.getFuturePlans().size();i++){
            g.drawString(textReader.getFuturePlans().get(i), 30, i*(fm.getAscent()+fm.getDescent()) + 400);
        }

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
            if(isInButtonMenu(mouseEvent,menuButtons)){
                menuButtons.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        for(MenuButton menuBtn: menuButtons){
            if(isInButtonMenu(mouseEvent,menuBtn)){
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
            if(isInButtonMenu(mouseEvent,menuBtn)){
                menuBtn.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            GameState.state = GameState.START;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
