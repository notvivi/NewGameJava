package ui;

import gamestates.GameState;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that creates button.
 */
public class StartButton {
    private int xPosition;
    private int yPosition;
    private int rowIndex;
    private int index = 0;
    private int xOffSetCenter = Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH_DEFAULT;
    private boolean mouseOver;
    private boolean mousePressed;
    public Rectangle buttonHitBox;
    GameState gameState;
    private BufferedImage images[];

    /**
     * Class constructor.
     * @param xPosition
     * @param yPosition
     * @param rowIndex
     * @param gameState
     */
    public StartButton(int xPosition, int yPosition, int rowIndex, GameState gameState){
        loadImages();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        isInHitBox();
    }

    /**
     * Method that creates button hit box.
     */
    private void isInHitBox() {
        buttonHitBox = new Rectangle(xPosition - xOffSetCenter,yPosition, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT);
    }

    /**
     * Method that loads button images.
     */
    private void loadImages(){
        images = new BufferedImage[2];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.START_BUTTON);
        for(int i = 0; i < images.length;i++){
            images[i] = temp.getSubimage(i * Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH_DEFAULT,rowIndex * Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT_DEFAULT, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH_DEFAULT, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT_DEFAULT);
        }

    }

    /**
     * Method that draws button.
     * @param g
     */
    public void draw(Graphics g){
        g.drawImage(images[index],xPosition - xOffSetCenter,yPosition, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT,null);
    }

    /**
     * Method that updates button state.
     */
     public void updateButton(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
    }

    /**
     * Method that sets current game state.
     */
    public void setGameState(){
        GameState.state = gameState;
    }

    /**
     * Method that resets button.
     */
    public void resetBooleans(){
        mouseOver = false;
        mousePressed = false;
    }

    /**
     * Method that sets if mouseover.
     * @param mouseOver
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * Method that returns if mouse is over.
     * @return
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Method that returns if mouse is pressed.
     * @return
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * Method that sets mouse pressed.
     * @param mousePressed
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
