
package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Torri
 */
public class LoadAudio {
//    Clip clip;
    public synchronized void playSound(final String url) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ins = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(url));
            clip.open(ins);
            clip.start();
        } catch (Exception ex) {}
    }
//    public synchronized void stopSound(String url){
//        if(clip!=null){
//            clip.stop();
//        }
//    }
}
