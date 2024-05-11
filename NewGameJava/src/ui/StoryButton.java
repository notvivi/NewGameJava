package ui;

import gamestates.GameState;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StoryButton {
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

    public StoryButton(int xPosition, int yPosition, int rowIndex, GameState gameState){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        loadImages();
        isInHitBox();
    }
    private void isInHitBox() {
        buttonHitBox = new Rectangle(xPosition - xOffSetCenter,yPosition, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT);
    }


    private void loadImages(){
        images = new BufferedImage[2];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.START_BUTTON);
        for(int i = 0; i < images.length;i++){
            images[i] = temp.getSubimage(i * Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH_DEFAULT,rowIndex * Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT_DEFAULT, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH_DEFAULT, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT_DEFAULT);
        }

    }

    public void draw(Graphics g){
        update();
        g.drawImage(images[index],xPosition - xOffSetCenter,yPosition, Constants.Ui.Buttons.CIRCLE_BUTTON_WIDTH, Constants.Ui.Buttons.CIRCLE_BUTTON_HEIGHT,null);
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
    }

    public void setGameState(){
        GameState.state = gameState;
    }
    public void resetBooleans(){
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
