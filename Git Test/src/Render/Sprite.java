package Render;

import Assets.LoadArt;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Torri
 */
public class Sprite {
    ImageIcon ii;
    public Sprite(String imdir){
        LoadArt load = new LoadArt();
        ii=load.createImageIcon(imdir, "Sprite");
    }
    public Image getImg(){
        return ii.getImage();
    }
}
