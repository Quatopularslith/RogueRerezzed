package audio;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
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
