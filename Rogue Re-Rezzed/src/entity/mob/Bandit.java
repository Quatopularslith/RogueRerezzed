
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import util.Operation;

/**
 * One of the only Humanoid beings in the game. The second to be added to this wonderful family of deadly monsters.
 * @author Mnenmenth
 */
public class Bandit extends RogueHostileEntity{
    public Bandit(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Bandit",Operation.MULT,18,Operation.ADD,2,20);
    }
}
