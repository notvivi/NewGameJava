package main;

import gamestates.*;
import gamestates.Menu;
import levels.SoundPlayer;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing playing;
    private Menu menu;
    private Start start;
    private Story story;
    private SoundPlayer soundPlayer = new SoundPlayer();

    public final static int TILE_SIZE_DEFAULT = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILE_SIZE_DEFAULT * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE* TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE* TILES_IN_HEIGHT;

    public Game(){
       // playSong();
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        start = new Start(this);
        menu = new Menu(this);
        story = new Story(this);
        playing = new Playing(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (GameState.state){
            case START:
                start.update();
                break;
            case MENU:
                menu.update();
                break;
            case STORY:
                story.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public void render(Graphics g){
        switch (GameState.state){
            case START:
                start.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            case STORY:
                story.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }

    }

    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) /timePerUpdate;
            deltaF += (currentTime - previousTime) /timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }

        }
    }
    public void windowFocusLost() {
        if((GameState.state == GameState.PLAYING)){
            playing.getPlayer().resetDirectionBooleans();
        }
    }

    public void playSong(){
        soundPlayer.setFile();
        soundPlayer.play();
        soundPlayer.loop();
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public Start getStart() {
        return start;
    }
    public Story getStory(){
        return story;
    }
}
