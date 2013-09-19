package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,health,dir,armour;
    @Override
    public abstract void tick();
    @Override
    public abstract void turn();
    public abstract void getX();
    public abstract void getY();
    public abstract void getDir();
    public abstract void getHealth();
    public abstract void damage(int att);
}
