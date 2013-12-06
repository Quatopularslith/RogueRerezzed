package art;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @author Torri
 */
public class LoadArt {
    public Image createImage(String path,String description, int sizeX, int sizeY) {
        URL imgURL = getClass().getResource(path);
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
    public BufferedImage createBufferedImage(String path, int sx, int sy){
        URL imgurl = getClass().getResource(path);
        if(imgurl!=null){
            BufferedImage out = new BufferedImage(sx,sy,BufferedImage.TYPE_INT_ARGB);
            try {
                out = ImageIO.read(imgurl);
            } catch (IOException ex) {
                System.err.println("Could not find file1: "+path);
            }
            out.createGraphics().drawImage(out.getScaledInstance(sx, sy, 0), 0,0, null);
            out.createGraphics().dispose();
            return out;
        }else{
            System.err.println("Could not find file2: "+path);
            return null;
        }
    }
}
