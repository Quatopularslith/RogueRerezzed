package Entity.Mob;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    public int maxAtt;
    public abstract int attack();
}
