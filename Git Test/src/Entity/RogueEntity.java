package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,dir;
    @Override
    public abstract void tick();
    @Override
    public abstract void render();
    public abstract void getX();
    public abstract void getY();
    public abstract void getDir();
}
