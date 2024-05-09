package levels;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SoundPlayer {
    Clip clip;


   /* public SoundPlayer(){
        playSong();
    }*/

   /* public void playSong(){
        getClip("main_song");
        clip.start();
    }*/

   /* private Clip getClip(String name){
       URL url = getClass().getResource("res/" + name + ".wav");
       AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }*/


}


