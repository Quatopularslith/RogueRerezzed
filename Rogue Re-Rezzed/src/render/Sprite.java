package render;

import art.LoadArt;
import core.Rogue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * This allows images to be made the correct size and pick the correct one
 * @author Torri
 */
public class Sprite {
    /**
     * Sprite image
     */
    public BufferedImage i = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
    public String dir;
    /**
     * Creates a sprite
     * @param s
     */
    public Sprite(SpriteSheet s){
        String imdir = "spritesheet"+Rogue.renderlevel+".png";
        BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
        int sx = spritesheet.getWidth()/4;
        int sy = spritesheet.getHeight()/3;
        int x,y;
        switch(s){
            case BAG:
                x=0;
                y=0;
                break;
            case BANDIT:
                x=1;
                y=0;
                break;
            case DIALOGUE:
                x=2;
                y=0;
                break;
            case FLOOR:
                x=3;
                y=0;
                break;
            case FISH:
                x=0;
                y=1;
                break;
            case GOBLIN:
                x=1;
                y=1;
                break;
            case GOLD:
                x=2;
                y=1;
                break;
            case MORTUUS_TRABAJOS:
                x=3;
                y=1;
                break;
            case PLAYER:
                x=0;
                y=2;
                break;
            case QUATOPULARSLITH:
                x=1;
                y=2;
                break;
            case SNAKE:
                x=2;
                y=2;
                break;
            case STAIRWAY:
                x=3;
                y=2;
                break;
            case NPC:
                x=0;
                y=2;
                break;
            default:
                x=2;
                y=0;
                break;
        }
        BufferedImage temp = spritesheet.getSubimage(x*sx, y*sy, sx, sy);
        if(s==SpriteSheet.NPC) temp = colorImage(temp);
        i.createGraphics().drawImage(temp.getScaledInstance(64, 64, Image.SCALE_SMOOTH), 0,0, null);
        i.createGraphics().dispose();
    }
    public Sprite(SpriteSheet s, int size){
        i = new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);
        String imdir = "spritesheet"+Rogue.renderlevel+".png";
        BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
        int sx = spritesheet.getWidth()/4;
        int sy = spritesheet.getHeight()/3;
        int x,y;
        switch(s){
            case BAG:
                x=0;
                y=0;
                break;
            case BANDIT:
                x=1;
                y=0;
                break;
            case DIALOGUE:
                x=2;
                y=0;
                break;
            case FLOOR:
                x=3;
                y=0;
                break;
            case FISH:
                x=0;
                y=1;
                break;
            case GOBLIN:
                x=1;
                y=1;
                break;
            case GOLD:
                x=2;
                y=1;
                break;
            case MORTUUS_TRABAJOS:
                x=3;
                y=1;
                break;
            case PLAYER:
                x=0;
                y=2;
                break;
            case QUATOPULARSLITH:
                x=1;
                y=2;
                break;
            case SNAKE:
                x=2;
                y=2;
                break;
            case STAIRWAY:
                x=3;
                y=2;
                break;
            case NPC:
                x=0;
                y=2;
                break;
            default:
                x=2;
                y=0;
                break;
        }
        BufferedImage temp = spritesheet.getSubimage(x*sx, y*sy, sx, sy);
        if(s==SpriteSheet.NPC) temp = colorImage(temp);
        i.createGraphics().drawImage(temp.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0,0, null);
        i.createGraphics().dispose();
    }
    private BufferedImage colorImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(pixels[2]+100<=255) pixels[2] += 100;
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
}
