package levels;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Class that loads sound.
 */
public class SoundPlayer {
    Clip clip;
    URL soundURL;

    /**
     * Class constructor.
     */
    public SoundPlayer(){
        soundURL = getClass().getResource("/res/sound/main song.wav");
    }

    /**
     * Method that sets clip.
     */
    public void setFile(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }

    /**
     * Method that plays the clip.
     */
    public void play(){
       clip.start();
    }

    /**
     * Method that loops the clip.
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}


