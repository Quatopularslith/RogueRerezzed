package Entity;

/**
 * @author Torri
 */
public abstract class RogueEntity{
    public int x,y,health,dir,armour,potion;
    public int[] inv;
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
    public void move(int d){
        if(d==90){//up
            y+=1;
        }else if(d==180){//left
            x-=1;
        }else if(d==270){//down
            y-=1;
        }else if(d==0){//right
            x+=1;
        }
    }
}
