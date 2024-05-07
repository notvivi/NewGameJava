package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    private Game game;
    public State(Game game){
        this.game = game;
    }

    public boolean isInButton(MouseEvent event, MenuButton menuButton){
        return menuButton.buttonHitBox.contains(event.getX(),event.getY());
    }

    public Game getGame() {
        return game;
    }
}
