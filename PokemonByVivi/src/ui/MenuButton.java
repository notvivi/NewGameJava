package ui;

import gamestates.GameState;
import utilz.LoadSave;
import utilz.Constants.Ui.Buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that creates button for menu.
 */
public class MenuButton {

    private int xPosition;
    private int yPosition;
    private int rowIndex;
    private int index = 0;
    private int xOffSetCenter = Buttons.BUTTON_WIDTH_DEFAULT / 2;
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
    public MenuButton(int xPosition, int yPosition, int rowIndex, GameState gameState){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        loadImages();
        isInHitBox();
    }

    /**
     * Method that creates button hitbox.
     */
    private void isInHitBox() {
        buttonHitBox = new Rectangle(xPosition - xOffSetCenter,yPosition,Buttons.BUTTON_WIDTH,Buttons.BUTTON_HEIGHT);
    }

    /**
     * Method that loads button images.
     */
    private void loadImages(){
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);
        for(int i = 0; i < images.length;i++){
            images[i] = temp.getSubimage(i * Buttons.BUTTON_WIDTH_DEFAULT,rowIndex * Buttons.BUTTON_HEIGHT_DEFAULT,Buttons.BUTTON_WIDTH_DEFAULT,Buttons.BUTTON_HEIGHT_DEFAULT);
        }

    }

    /**
     * Method that draws button.
     * @param g
     */
    public void draw(Graphics g){
        update();
        g.drawImage(images[index],xPosition - xOffSetCenter,yPosition,Buttons.BUTTON_WIDTH,Buttons.BUTTON_HEIGHT,null);
    }

    /**
     * Method that updates button.
     */
    public void update(){
        index = 0;
        if(mouseOver) {
            index = 1;
        }
        if(mousePressed){
            index = 2;
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
     * Method that sets if mouse over.
     * @param mouseOver
     */
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
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

    /**
     * Method that returns index.
     * @return
     */
    public int getIndex() {
        return index;
    }
}
