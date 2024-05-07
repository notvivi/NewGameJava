package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "res/player_sprites.png";
    public static final String LEVEL_ATLAS = "res/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
    public static final String MENU_BUTTON_ATLAS = "res/button_atlas.png";
    public static final String MENU_BACKGROUND = "res/menu_background.png";

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

    public static int[][] getDataAboutLevel(){
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
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
