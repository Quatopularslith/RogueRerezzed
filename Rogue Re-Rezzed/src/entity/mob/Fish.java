package entity.mob;

import dungeon.Level;
import util.Operation;

/**
 * Comparable to Magicarp of Pokemon. Wanders randomly across the world.
 *
 * @author Mnenmenth
 */
public class Fish extends RogueHostileEntity {

    public Fish(int lvl, Level l1) {
        super(lvl, l1, "Fish", Operation.MULT, 5f, Operation.MULT, 0f, -1);
    }
}
