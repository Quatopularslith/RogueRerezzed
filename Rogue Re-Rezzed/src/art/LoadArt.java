package art;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 
 * @author Torri
 */
public class LoadArt {
    public Image createImage(String path,String description, int sizeX, int sizeY) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon ii = new ImageIcon(imgURL, description);
            Image i = ii.getImage();
            i=i.getScaledInstance(sizeX, sizeY, 0);
            return i;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
