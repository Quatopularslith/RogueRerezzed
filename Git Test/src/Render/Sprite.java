package Render;

import Assets.LoadArt;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * @author Torri
 */
public class Sprite {
    ImageIcon ii;
    public Sprite(String imdir){
        LoadArt load = new LoadArt();
        ii=load.createImageIcon(imdir, "Sprite of "+this.toString());
    }
    public Image getImg(){
        return ii.getImage();
    }
    public BufferedImage getBufferedImage(){
        BufferedImage bi = new BufferedImage(ii.getIconWidth(),ii.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        ii.paintIcon(null, g, 0, 0);
        g.dispose();
        BufferedImage re = new BufferedImage(32,32,bi.getType());
        Graphics2D g2 = re.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        return bi;
    }
}
