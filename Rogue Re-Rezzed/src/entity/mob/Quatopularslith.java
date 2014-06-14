package entity.mob;

import dungeon.Level;
import entity.item.Gold;
import util.Operation;

/**
 * AKA Secret mob 1. This is a boss mob
 *
 * @author Mnenmenth
 */
public class Quatopularslith extends RogueHostileEntity {

    public Quatopularslith(int lvl, Level l1) {
        super(lvl, l1, "Quatopularslith", Operation.MULT, 100f, Operation.DIV, (float) lvl, 10000);
        this.inv[1] = new Gold(lvl * 10, this, l1);
    }
}
