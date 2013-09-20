package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,health,dir,armour;
    public abstract int getX();
    public abstract int getY();
    public abstract int getDir();
    public abstract int getHealth();
    public abstract void damage(int att);
    public int distTo(RogueEntity e){
        return (int) Math.sqrt((e.x-x)^2+(e.y-y)^2);
    }
}
