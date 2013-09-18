package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,health,dir;
    @Override
    public abstract void tick();
    public abstract void getX();
    public abstract void getY();
    public abstract void getDir();
}
