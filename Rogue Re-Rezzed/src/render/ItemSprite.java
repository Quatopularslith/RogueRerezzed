package render;

import art.LoadArt;
import core.Rogue;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Torri
 */
public class ItemSprite {

    public BufferedImage img;

    public ItemSprite(ItemSheet i) {
        String imdir = "ItemSheet" + Rogue.renderlevel + ".png";
        BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
        int sx = spritesheet.getWidth() / 3;
        int sy = spritesheet.getHeight() / 3;
        int x, y;
        switch (i) {
            case EFFECT_BROKEN:
                x = 0;
                y = 0;
                break;
            case EFFECT_SHATTERED:
                x = 1;
                y = 0;
                break;
            case EFFECT_MAGICAL:
                x = 2;
                y = 0;
                break;
            case OUTLINE_SHIELD:
                x = 0;
                y = 1;
                break;
            case OUTLINE_SWORD:
                x = 1;
                y = 1;
                break;
            case TEMPLATE_SWORD:
                x = 2;
                y = 1;
                break;
            case TEMPLATE_AXE:
                x = 0;
                y = 2;
                break;
            case TEMPLATE_SHIELD:
                x = 1;
                y = 2;
                break;
            case INVENTORY:
                x = 2;
                y = 2;
                break;
            default:
                x = 2;
                y = 2;
                break;
        }
        BufferedImage temp = spritesheet.getSubimage(x * sx, y * sy, sx, sy);
        img.createGraphics().drawImage(temp.getScaledInstance(64, 64, Image.SCALE_SMOOTH), 0, 0, null);
        img.createGraphics().dispose();
    }

    public ItemSprite(ItemSheet i, int dim) {
    }
}
