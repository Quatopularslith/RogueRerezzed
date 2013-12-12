
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import util.Operation;

/**
 * A snake. An evil and spasmodic foe which spawns and fights in great numbers.
 * @author Torri
 */
public class Snake extends RogueHostileEntity{
    public Snake(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Snake",Operation.MULT,5,Operation.ADD,1,10);
    }
}
