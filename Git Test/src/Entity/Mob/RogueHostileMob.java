package Entity.Mob;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    @Override
    public abstract void tick();
    public abstract int attack();
}