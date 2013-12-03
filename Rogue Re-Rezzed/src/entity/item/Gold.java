
package entity.item;

import dungeon.Level;
import entity.RogueEntity;
import render.Sprite;

/**
 *
 * @author Creatorri
 */
public class Gold extends Item{
    public Gold(int am, RogueEntity parent1, Level l1) {
        super(am, parent1, l1);
        this.name=am+" Gold";
        for(double d:stats){
            d=0.0;
        }
        this.sp=new Sprite("Gold",8);
    }
}
