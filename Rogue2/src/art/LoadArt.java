package art;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Torri
 */
public class LoadArt {
    public Image createImage(String path,String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return (new ImageIcon(imgURL, description)).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
