package audio;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @author Torri
 */
public class LoadAudio {

    Clip clip;

    public synchronized void playSound(final String url) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ins = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(url));
            clip.open(ins);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public synchronized void stopSound(String url) {
        if (clip != null) {
            clip.stop();
        }
    }
}
