
package entity.item;

import dungeon.Level;
import entity.RogueEntity;
import render.Sprite;
import render.SpriteSheet;

/**
 *
 * @author Creatorri
 */
public class Gold extends Item{
    public int am;
    public Gold(int am, RogueEntity parent1, Level l1) {
        super(am, parent1,1, l1);
        this.am=am;
        this.name=am+" Gold";
        for(double d:stats){
            d=0.0;
        }
        this.sp=new Sprite(SpriteSheet.GOLD,8);
    }
}
