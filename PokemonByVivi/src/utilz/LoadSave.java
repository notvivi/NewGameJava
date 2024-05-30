package utilz;

import entities.Pikachu;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class that loads images.
 */
public class LoadSave {

    public static final String PLAYER_ATLAS = "res/player_sprites.png";
    public static final String LEVEL_ATLAS = "res/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data_long.png";
    public static final String MENU_BUTTON_ATLAS = "res/button_atlas.png";
    public static final String MENU_POKEDEX = "res/menu_background_pokedex.png";
    public static final String START_BUTTON = "res/play_button2x.png";
    public static final String PIKACHU_MENU_BACKGROUND = "res/pikachu_background.png";
    public static final String LEVEL_BACKGROUND = "res/playing_bg_img.png";
    public static final String BIG_CLOUDS = "res/big_clouds.png";
    public static final String SMALL_CLOUD = "res/small_clouds.png";
    public static final String ICON = "res/pikachu_icon.png";
    public static final String MENU_POKEDEX_2 = "res/menu_background_pokedex_2.png";
    public static final String POKEMON_TITLE = "res/pokemon_title.png";
    public static final String ENEMY_PIKACHU_ATLAS = "pikachu_sprite.png";
    public static final String STORY_BACKGROUND = "res/story_background.png";
    public static final String HEALTH_BAR = "res/health_power_bar.png";

    /**
     * Method that loads image.
     * @param fileName
     * @return
     */
    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage image = null;
        InputStream inputStream = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return image;
    }

    /**
     * Method that loads pikachu sprite.
     * @return
     */
    public static ArrayList<Pikachu> getPikachus(){
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Pikachu> crabbyList = new ArrayList<>();
        for(int i = 0; i < image.getHeight();i++){
            for(int j = 0;j < image.getWidth();j++){
                Color color = new Color(image.getRGB(j,i));
                int value = color.getGreen();
                if(value == Constants.EnemyConstants.PIKACHU){
                   crabbyList.add(new Pikachu(j * Game.TILES_SIZE, i * Game.TILES_SIZE));
                }
            }
        }
        return crabbyList;
    }

    /**
     * Method that loads level data.
     * @return
     */
    public static int[][] getDataAboutLevel(){
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        int[][] levelData = new int[image.getHeight()][image.getWidth()];
        for(int i = 0; i < image.getHeight();i++){
            for(int j = 0;j < image.getWidth();j++){
                Color color = new Color(image.getRGB(j,i));
                int value = color.getRed();
                if(value >= 48){
                    value = 0;
                }
                levelData[i][j] = value;
            }
        }
        return levelData;
    }

}
