package gamestates;

import main.Game;
import ui.MenuButton;
import ui.StartButton;
import ui.StoryButton;

import java.awt.event.MouseEvent;

public class State {
    private Game game;
    public State(Game game){
        this.game = game;
    }

    public boolean isInButtonMenu(MouseEvent event, MenuButton menuButton){
        return menuButton.buttonHitBox.contains(event.getX(),event.getY());
    }
    public boolean isInButtonStart(MouseEvent event, StartButton startButton){
        return startButton.buttonHitBox.contains(event.getX(),event.getY());
    }
    public boolean isInButtonStory(MouseEvent event, StoryButton storyButton){
        return storyButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    public Game getGame() {
        return game;
    }
}
