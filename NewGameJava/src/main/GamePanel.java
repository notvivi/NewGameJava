package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import javax.swing.*;
import java.awt.*;

/**
 * Class that creates panel for the game.
 */
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    /**
     * Class constructor.
     * @param game
     */
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Method that sets panel size.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Method that draws the game in the window.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Method that returns game.
     * @return
     */
    public Game getGame(){
        return game;
    }



}
