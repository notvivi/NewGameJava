package gamestates;

import main.Game;
import ui.MenuButton;
import ui.StartButton;
import ui.StoryButton;

import java.awt.event.MouseEvent;

/**
 * Class that every type of state is extending from.
 */
public class State {
    private Game game;

    /**
     * Class constructor.
     * @param game
     */
    public State(Game game){
        this.game = game;
    }

    /**
     * Method that returns if users mouse in on button.
     * @param event
     * @param menuButton
     * @return
     */
    public boolean isInButtonMenu(MouseEvent event, MenuButton menuButton){
        return menuButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    /**
     * Method that returns if users mouse is on button.
     * @param event
     * @param startButton
     * @return
     */
    public boolean isInButtonStart(MouseEvent event, StartButton startButton){
        return startButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    /**
     * Method that returns if users mouse in on button.
     * @param event
     * @param storyButton
     * @return
     */
    public boolean isInButtonStory(MouseEvent event, StoryButton storyButton){
        return storyButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    /**
     * Method that returns game.
     * @return
     */
    public Game getGame() {
        return game;
    }
}
