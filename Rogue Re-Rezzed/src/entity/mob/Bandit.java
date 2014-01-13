
package entity.mob;

import dungeon.Level;
import util.Operation;

/**
 * One of the only Humanoid beings in the game. The second to be added to this wonderful family of deadly monsters.
 * @author Mnenmenth
 */
public class Bandit extends RogueHostileEntity{
    public Bandit(int lvl,Level l1){
        super(lvl,l1,"Bandit",Operation.MULT,10f,Operation.MULT,1.5f,20);
    }
}
