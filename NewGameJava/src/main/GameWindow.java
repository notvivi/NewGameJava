package main;

import utilz.LoadSave;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

public class GameWindow {
    private JFrame jframe;
    private BufferedImage iconImage;

    public GameWindow(GamePanel gamePanel){
        load();
       jframe = new JFrame();

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

    public void load(){
        iconImage = LoadSave.getSpriteAtlas(LoadSave.ICON);
    }

}
