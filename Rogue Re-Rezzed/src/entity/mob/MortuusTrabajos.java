package entity.mob;

import dungeon.Level;
import entity.item.Gold;
import util.Operation;

/**
 * AKA Secret Mob 2. AKA DEAD PIXEL. Boss mob
 *
 * @author Mnenmenth
 */
public class MortuusTrabajos extends RogueHostileEntity {

    public MortuusTrabajos(int lvl, Level l1) {
        super(lvl, l1, "Mortuus_Trabajos", Operation.MULT, 50, Operation.MULT, 10, 5);
        this.inv[1] = new Gold(lvl * 100, this, l1);
    }
}
