package Entity;

import Item.Item;
import Render.Sprite;

/**
 * @author Torri
 */
public abstract class RogueEntity{
    public int tx=10,ty=10,health,dir,armour,potion;
    public Item[] inv;
    public Sprite sp;
    public boolean death = false;
    public int getTx(){
        return tx;
    }
    public int getTy(){
        return ty;
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
        return (int) Math.sqrt((e.tx-tx)^2+(e.ty-ty)^2);
    }
    public void move(int d){
        if(d==90){//up
            ty=ty-1;
        }else if(d==180){//left
            tx=tx+1;
        }else if(d==270){//down
            ty=ty-1;
        }else if(d==0){//right
            tx=tx+1;
        }
    }
    public void turn(){
        if(health==0){
            death();
        }else{
            health++;
        }
    }
    public void death(){
        for(Item i1 : inv){
            dropItem(i1);
        }
        death=true;
    }
    public void dropItem(Item i){
        
    }
}
