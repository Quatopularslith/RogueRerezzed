package art;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Torri
 */
public class LoadArt {

    public BufferedImage createBufferedImage(String path, int sx, int sy) {
        URL imgurl = getClass().getResource(path);
        if (imgurl != null) {
            BufferedImage temp = new BufferedImage(sx, sy, BufferedImage.TYPE_INT_ARGB);
            BufferedImage out = new BufferedImage(sx, sy, BufferedImage.TYPE_INT_ARGB);
            try {
                temp = ImageIO.read(imgurl);
            } catch (IOException ex) {
                System.err.println("Could not find file1: " + path);
            }
            out.createGraphics().drawImage(temp.getScaledInstance(sx, sy, Image.SCALE_SMOOTH), 0, 0, null);
            out.createGraphics().dispose();
            return out;
        } else {
            System.err.println("Could not find file2: " + path);
            return null;
        }
    }

    public BufferedImage createBufferedImage(String path) {
        URL imgurl = getClass().getResource(path);
        if (imgurl != null) {
            try {
                return ImageIO.read(imgurl);
            } catch (IOException ex) {
                System.err.println("Could not find file1: " + path);
            }
        } else {
            System.err.println("Could not find file2: " + path);
            return null;
        }
        return null;
    }
}
