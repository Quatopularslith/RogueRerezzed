
package music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * music src: http://freemusicarchive.org/music/Souls_of_Nephilims/
 * @author 1003749
 */
public class LoadMusic {
    public static synchronized void playSound(final String url) {
        InputStream is = null;
        try {
            is = new FileInputStream(url);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
                clip.open(inputStream);
                clip.start();
              } catch (Exception e) {
                System.err.println(e.getMessage());
              }
            }
       }).start();
    }
}
