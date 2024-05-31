package tests;

import gamestates.GameState;
import org.junit.jupiter.api.Test;
import ui.MenuButton;

import static org.junit.jupiter.api.Assertions.*;

class MenuButtonTest {
    GameState gameState = GameState.MENU;
    MenuButton menuButton = new MenuButton(50,50,2,gameState);

    /**
     * Method that tests update method.
     */
    @Test
    void update() {
        int index = 1;
        menuButton.setMouseOver(true);
        menuButton.update();
        assertEquals(index, menuButton.getIndex());
    }
}