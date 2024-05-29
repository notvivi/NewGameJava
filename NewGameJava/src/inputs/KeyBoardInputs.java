package inputs;

import gamestates.GameState;
import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that checks all keyboard inputs.
 */
public class KeyBoardInputs implements KeyListener  {

    private GamePanel gamePanel;

    /**
     * Class constructor.
     * @param gamePanel
     */
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * Nothing.
     * @param e the event to be processed
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method that sets if key is released.
     * @param e the event to be processed
     */
    public void keyReleased(KeyEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case STORY:
                gamePanel.getGame().getStory().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;

        }

    }

    /**
     * Method that sets if key is pressed.
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case STORY:
                gamePanel.getGame().getStory().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;

        }
    }

}
