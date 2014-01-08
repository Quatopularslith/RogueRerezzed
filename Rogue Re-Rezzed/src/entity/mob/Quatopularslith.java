
package entity.mob;

import dungeon.Level;
import util.Operation;

/**
 * AKA Secret mob 1. This is a boss mob
 * @author Mnenmenth
 */
public class Quatopularslith extends RogueHostileEntity{
    public Quatopularslith(int lvl,Level l1){
        super(lvl,l1,"Quatopularslith",Operation.MULT,100,Operation.DIV,lvl,10000);
    }
}
