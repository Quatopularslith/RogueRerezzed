package entity.mob;

import dungeon.Level;
import util.Operation;

/**
 * Newest in the family of bad guys.
 *
 * @author Torri
 */
public class Goblin extends RogueHostileEntity {

    public Goblin(int lvl, Level l1) {
        super(lvl, l1, "Goblin", Operation.MULT, 10.5f, Operation.MULT, 1.8f, 15);
    }
}
