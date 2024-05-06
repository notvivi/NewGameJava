package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface MethodsForStates {
    public void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent mouseEvent);
    public void mousePressed(MouseEvent mouseEvent);
    public void mouseReleased(MouseEvent mouseEvent);
    public void mouseMoved(MouseEvent mouseEvent);

    public void keyPressed(KeyEvent keyEvent);
    public void keyReleased(KeyEvent keyEvent);
}
