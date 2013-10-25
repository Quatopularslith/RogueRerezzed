package Render;

import Assets.LoadArt;
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
    public void draw(int x, int y){
        
    }
}
