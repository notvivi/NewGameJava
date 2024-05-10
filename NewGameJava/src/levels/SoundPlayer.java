package levels;

import javax.sound.sampled.*;
import java.net.URL;

public class SoundPlayer {
    Clip clip;
    URL soundURL;
    public SoundPlayer(){
        soundURL = getClass().getResource("/res/sound/main song.wav");
    }

    public void setFile(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }
    public void play(){
       clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}


