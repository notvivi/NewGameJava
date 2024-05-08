package inputs;

import gamestates.GameState;
import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener  {

    private GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public void keyTyped(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;

        }

    }
    public void keyPressed(KeyEvent e) {
        switch (GameState.state){
            case START:
                gamePanel.getGame().getStart().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;

        }
    }

}
