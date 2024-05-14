package gamestates;

import inputs.TextReader;
import main.Game;
import ui.StoryButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Story extends State implements MethodsForStates {
    private TextReader textReader = new TextReader();
    private StoryButton[] storyButtons = new StoryButton[1];
    private final BufferedImage storyBackground;
    private Font monospacedBold = new Font(Font.MONOSPACED, Font.BOLD, 25);

    public Story(Game game) {
        super(game);
        loadButtons();
        loadArraylist();
        storyBackground = LoadSave.getSpriteAtlas(LoadSave.STORY_BACKGROUND);
    }

    private void loadButtons() {
        storyButtons[0] = new StoryButton((int) (Game.GAME_WIDTH / 1.94),(int) (300 * Game.SCALE), 0, GameState.PLAYING);
    }



    @Override
    public void update() {
        for(StoryButton sb: storyButtons){
            sb.update();
        }
    }

    public void loadArraylist(){
        textReader.read(textReader.getSTORY());
    }


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
