
package entity.mob;

import dungeon.Level;
import util.Operation;

/**
 * A snake. An evil and spasmodic foe which spawns and fights in great numbers.
 * "I hate snakes!" -Indiana Jones
 * @author Torri
 */
public class Snake extends RogueHostileEntity{
    public Snake(int lvl,Level l1){
        super(lvl,l1,"Snake",Operation.MULT,5,Operation.MULT,1,10);
    }
}
