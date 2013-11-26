package render;

import art.LoadArt;
import dungeon.Level;
import java.awt.Image;

/**
 *
 * @author Torri
 */
public class Sprite {
    /**
     * Sprite image
     */
    public Image i;
    public String dir;
    /**
     * Creates a sprite
     * @param imdir is the name of the image (- the extention)
     */
    public Sprite(String imdir){
        imdir+=Level.renderlevel+".png";
        dir=imdir;
        LoadArt load = new LoadArt();
        i=load.createImage(imdir, "Sprite",64,64);
    }
    /**
     * Creates a sprite
     * @param imdir directory
     * @param x size
     */
    public Sprite(String imdir,int x){
        imdir+=Level.renderlevel+".png";
        dir=imdir;
        LoadArt load = new LoadArt();
        i=load.createImage(imdir, "Sprite",x,x);
    }
}
