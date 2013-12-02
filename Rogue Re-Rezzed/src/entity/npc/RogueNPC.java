
package entity.npc;

import dungeon.Level;
import entity.RogueEntity;

/**
 * Master for all new NPCs
 * @author Torri
 */
public class RogueNPC extends RogueEntity{
    public boolean hostile;
    /**
     * Creates NPC
     * @param l1 
     */
    public RogueNPC(Level l1) {
        super(l1);
    }
}
