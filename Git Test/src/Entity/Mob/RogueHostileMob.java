package Entity.Mob;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    @Override
    public abstract void tick();
    @Override
    public abstract void render();
    public abstract void attack();
}