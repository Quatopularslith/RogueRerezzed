package Entity.Mob;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    @Override
    public abstract void turn();
    public abstract int attack();
}
