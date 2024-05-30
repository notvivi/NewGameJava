package inputs;

import gamestates.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Class that checks all mouse inputs.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    /**
     * Class constructor.
     * @param gamePanel
     */
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * Method that checks if user clicked on mouse.
     * @param e the event to be processed
     */
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    /**
     * Method that checks if user pressed mouse.
     * @param e the event to be processed
     */
    public void mousePressed(MouseEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().mousePressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case STORY:
                gamePanel.getGame().getStory().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    /**
     * Method that checks if user released mouse.
     * @param e the event to be processed
     */
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().mouseReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case STORY:
                gamePanel.getGame().getStory().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    /**
     * Nothing.
     * @param e the event to be processed
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Nothing.
     * @param e the event to be processed
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Nothing.
     * @param e the event to be processed
     */
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Method that checks if us moved with mouse.
     * @param e the event to be processed
     */
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().mouseMoved(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case STORY:
                gamePanel.getGame().getStory().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
