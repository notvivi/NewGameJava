package gamestates;

import main.Game;
import ui.MenuButton;
import ui.StartButton;

import java.awt.event.MouseEvent;

public class State {
    private Game game;
    public State(Game game){
        this.game = game;
    }

    public boolean isInButton(MouseEvent event, MenuButton menuButton){
        return menuButton.buttonHitBox.contains(event.getX(),event.getY());
    }
    public boolean isInButtonStart(MouseEvent event, StartButton startButton){
        return startButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    public Game getGame() {
        return game;
    }
}
