
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.item.Gold;
import util.Operation;

/**
 * AKA Secret Mob 2. Boss mob
 * @author Mnenmenth
 */
public class MortuusTrabajos extends RogueHostileEntity{
    public MortuusTrabajos(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Mortuus_Trabajos",Operation.MULT,50,Operation.MULT,10,2);
        this.inv[1] = new Gold(lvl*100,this,l1);
    }
}
