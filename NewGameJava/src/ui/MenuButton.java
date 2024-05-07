package ui;

import gamestates.GameState;
import utilz.LoadSave;
import utilz.Constants.Ui.Buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    public MenuButton(int xPosition, int yPosition, int rowIndex, GameState gameState){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.gameState = gameState;
        loadImages();
        isInHitBox();
    }

    private void isInHitBox() {
        buttonHitBox = new Rectangle(xPosition - xOffSetCenter,yPosition,Buttons.BUTTON_WIDTH,Buttons.BUTTON_HEIGHT);
    }


    private void loadImages(){
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);
        for(int i = 0; i < images.length;i++){
            images[i] = temp.getSubimage(i * Buttons.BUTTON_WIDTH_DEFAULT,rowIndex * Buttons.BUTTON_HEIGHT_DEFAULT,Buttons.BUTTON_WIDTH_DEFAULT,Buttons.BUTTON_HEIGHT_DEFAULT);
        }

    }

    public void draw(Graphics g){
        update();
        g.drawImage(images[index],xPosition - xOffSetCenter,yPosition,Buttons.BUTTON_WIDTH,Buttons.BUTTON_HEIGHT,null);
    }
    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        } else if (mousePressed) {
           index = 2;
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
