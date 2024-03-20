package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void updateGame(){

    }
    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }



}
