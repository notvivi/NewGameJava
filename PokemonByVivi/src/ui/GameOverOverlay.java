package ui;

import gamestates.GameState;
import gamestates.Playing;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * CLass that creates game over layer when player dies.
 */
public class GameOverOverlay {
    private Font monospacedBold = new Font(Font.MONOSPACED, Font.BOLD, 25);
    private Playing playing;

    /**
     * Class constructor.
     * @param playing
     */
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    /**
     * Method that draws text and rectangle.
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, Game.GAME_WIDTH,Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.setFont(monospacedBold);
        g.drawString("Game Over", (int) (Game.GAME_WIDTH /2.2), 150);
        g.drawString("Press esc to enter Main menu", (int) (Game.GAME_WIDTH/2.6), 300);
    }

    /**
     * Method that checks if user presses escape.
     * @param event
     */
    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
            playing.resetAll();
            GameState.state = GameState.START;
        }
    }

}
