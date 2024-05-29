package main;

import utilz.LoadSave;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

/**
 * Method that creates window for the game.
 */
public class GameWindow extends JFrame {
    private JFrame jframe;
    private BufferedImage iconImage;

    /**
     * Class constructor.
     * @param gamePanel
     */
    public GameWindow(GamePanel gamePanel){
       load();
       jframe = new JFrame();

       jframe.setTitle("Pokemon by Vivi");
       jframe.setIconImage(iconImage);
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jframe.add(gamePanel);
       jframe.setResizable(false);
       jframe.pack();
       jframe.setVisible(true);
       jframe.setLocationRelativeTo(null);

       jframe.addWindowFocusListener(new WindowFocusListener() {
           @Override
           public void windowGainedFocus(WindowEvent e) {

           }

           @Override
           public void windowLostFocus(WindowEvent e) {
               gamePanel.getGame().windowFocusLost();
           }
       });
    }

    /**
     * Method that loads icon image.
     */
    public void load(){
        iconImage = LoadSave.getSpriteAtlas(LoadSave.ICON);
    }
}
