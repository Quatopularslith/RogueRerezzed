package render;

import art.LoadArt;
import dungeon.Level;
import java.awt.Image;

/**
 *
 * @author Torri
 */
public class Sprite {
    public Image i;
    /**
     * Creates a sprite
     * @param imdir is the name of the image (- the extention)
     */
    public Sprite(String imdir){
//        imdir+=Level.renderlevel+".png";
        imdir+=16+".png";
        LoadArt load = new LoadArt();
        i=load.createImage(imdir, "Sprite of "+this.toString());
    }
}
