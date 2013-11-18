package Entity;

import Item.Item;
import Render.Sprite;
import java.util.Random;

/**
 * @author Torri
 */
public abstract class RogueEntity{
    public int tx=10,ty=10,health,dir,armour,potion,maxAtt;
    public Item[] inv;
    public Sprite sp;
    public int uuid;
    public boolean move;
    public boolean death = false;
    public Random r = new Random();
    public int getDir(){
        return dir;
    }
    public int getHealth(){
        return health;
    }
    public void damage(int att){
        att -= armour;
        armour -= att/4;
        health -= att;
    }
    public void attack(RogueEntity e){
        e.damage(r.nextInt(maxAtt));
        System.out.println(e.health);
    }
    public int distTo(RogueEntity e){
        return (int) Math.sqrt((e.tx-tx)^2+(e.ty-ty)^2);
    }
    public void move(int d){
        int dx=0,dy=0;
        if(d==90){//up
            dy=-1;
        }else if(d==180){//left
            dx=-1;
        }else if(d==270){//down
            dy=1;
        }else if(d==0){//right
            dx=1;
        }else{
            dx=1;
            dy=1;
        }
        tx+=dx;
        ty+=dy;
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
