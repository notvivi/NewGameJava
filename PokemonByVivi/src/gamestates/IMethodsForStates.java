package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interface with methods for states.
 */
public interface IMethodsForStates {
    void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent mouseEvent);
    public void mousePressed(MouseEvent mouseEvent);
    public void mouseReleased(MouseEvent mouseEvent);
    public void mouseMoved(MouseEvent mouseEvent);
    public void keyPressed(KeyEvent keyEvent);
    public void keyReleased(KeyEvent keyEvent);
}
