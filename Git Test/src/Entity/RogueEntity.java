package Entity;

import Core.Game;

/**
 * @author Torri
 */
public abstract class RogueEntity extends Game{
    public int x,y,health,dir,armour,potion;
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDir(){
        return dir;
    }
    public int getHealth(){
        return health;
    }
    public void damage(int att){
        att -= armour;
        armour -= att;
        health -= att;
    }
    public int distTo(RogueEntity e){
        return (int) Math.sqrt((e.x-x)^2+(e.y-y)^2);
    }
}
