
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import util.Operation;

/**
 * Comparable to Magicarp of Pokemon. Wanders randomly across the world.
 * @author Mnenmenth
 */
public class Fish extends RogueHostileEntity{
    public Fish(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Fish",Operation.MULT,5,Operation.MULT,0,-1);
    }
}
