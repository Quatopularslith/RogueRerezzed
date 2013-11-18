package entity;

import dungeon.Level;
import dungeon.Room;
import entity.item.Item;
import java.util.Random;
import render.Sprite;

/**
 * @author Torri
 */
public class RogueEntity {
    public int x,y,uuid,rx,ry;
    public float health;
    public Item[] inv;
    public Sprite sp;
    public Random rand = new Random();
    private Level l;
    public RogueEntity(Level l1){
        l=l1;
    }
    /**
     * Moves Entity
     * @param d Integer representing the angle of which to move
     *        0
     *        | 
     * 270----|---- 90
     *        |
     *       180
     */
    public void move(int d){
        if(d<=45 && d>315){
            y++;
        }else if(d<=135 && d>45){
            x++;
        }else if(d<=225 && d>135){
            y--;
        }else if(d<=225 && d>=315){
            x--;
        }
        System.out.println(x);
    }
    /**
     * Take damage
     * @param att 
     */
    public void damage(int att){
        health-=att;
    }
    /**
     * Updates Entity
     */
    public void turn(){
        if(health<=0){
            death();
        }else{
            health++;
        }
        rx=x*Level.renderlevel;
        ry=y*Level.renderlevel;
    }
    /**
     * What to do when this entity dies
     */
    public void death(){
        for(Item i:inv){
            i.drop();
        }
        l.removeEntity(this);
    }
    /**
     * What to do when this entity is initialized
     * @param r
     */
    public void spawn(Room r){
        x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][0];
        x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][1];
    }
}
