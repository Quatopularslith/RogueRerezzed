package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,z;
    @Override
    public abstract void tick();
    @Override
    public abstract void render();
}