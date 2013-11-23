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
    public int maxAtt=0,defence=0;
    public int x=0,y=0,uuid=0;
    public float health=0;
    public int maxhealth=0;
    public Item[] inv;
    public Sprite sp = new Sprite("Bag");
    public Random rand = new Random();
    public Level l;
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
        int dx=0;
        int dy=0;
        if(d<=45 || d>315){//up
            dy--;
        }else if(d<=135 && d>45){//right
            dx++;
        }else if(d<=225 && d>135){//down
            dy++;
        }else if(d>=225 && d<=315){//left
            dx--;
        }
        if(l.board[dx+x][dy+y]==true){
            x+=dx;
            y+=dy;
        }
    }
    /**
     * Take damage
     * @param att 
     */
    public void damage(int att){
        if(defence<=att){
            att-=defence;
        }else{
            att=0;
        }
        health-=att;
    }
    /**
     * Updates Entity
     */
    public void turn(){
        if(health<=0){
            death();
        }else if(health<maxhealth){
            health++;
        }
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    /**
     * What to do when this entity dies
     */
    public void death(){
//        l.removeEntity(this);
    }
    /**
     * What to do when this entity is initialized
     * @param r
     */
    public void spawn(Room r){
        x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][0];
        y=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][1];
    }
}
