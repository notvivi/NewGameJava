package gamestates;

import inputs.TextReader;
import main.Game;
import ui.StoryButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Class that creates story state in game.
 */
public class Story extends State implements IMethodsForStates {
    private TextReader textReader = new TextReader();
    private StoryButton[] storyButtons = new StoryButton[1];
    private final BufferedImage storyBackground;
    private Font monospacedBold = new Font(Font.MONOSPACED, Font.BOLD, 25);

    /**
     * Class constructor.
     * @param game
     */
    public Story(Game game) {
        super(game);
        loadButtons();
        loadArraylist();
        storyBackground = LoadSave.getSpriteAtlas(LoadSave.STORY_BACKGROUND);
    }

    /**
     * Method that loads buttons.
     */
    private void loadButtons() {
        storyButtons[0] = new StoryButton((int) (Game.GAME_WIDTH / 1.94),(int) (300 * Game.SCALE), 0, GameState.PLAYING);
    }

    /**
     * Method that updates buttons.
     */
    @Override
    public void update() {
        for(StoryButton sb: storyButtons){
            sb.update();
        }
    }

    /**
     * Method that loads story file.
     */
    public void loadArraylist(){
        textReader.read(textReader.getSTORY());
    }

    /**
     * Method that draws everything in this class.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(storyBackground,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.setFont(monospacedBold);
        FontMetrics fm = g.getFontMetrics(g.getFont());

        for(int i = 0; i < textReader.getSentences().size();i++){
            g.drawString(textReader.getSentences().get(i), 30, i*(fm.getAscent()+fm.getDescent()) + 100);
        }

        for(StoryButton sb: storyButtons){
            sb.draw(g);
        }
    }

    /**
     * Nothing.
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    /**
     * Method that checks if user pressed button.
     * @param mouseEvent
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        for(StoryButton sb: storyButtons){
            if(isInButtonStory(mouseEvent,sb)){
                sb.setMousePressed(true);
                break;
            }
        }
    }

    /**
     * Method that checks if user released button.
     * @param mouseEvent
     */
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

    /**
     * Method that resets button.
     */
    private void resetButtons() {
        for(StoryButton sb: storyButtons){
            sb.resetBooleans();
        }
    }

    /**
     * Method that checks if player moved with mouse on button.
     * @param mouseEvent
     */
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

    /**
     * Method that checks if player pressed backspace.
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            GameState.state = GameState.MENU;
        }
    }

    /**
     * Nothing.
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
