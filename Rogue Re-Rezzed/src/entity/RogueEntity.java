package entity;

import art.LoadArt;
import dungeon.Level;
import dungeon.Room;
import entity.item.Item;
import java.util.Random;
import render.Sprite;
import util.AI;
import util.Direction;

/**
 * @author Torri
 */
public class RogueEntity {
    public double maxAtt=0,maxDefence=0;
    public int x=0,y=0,uuid=0;
    public float health=0;
    public float maxhealth=0;
    public int lvl=0;
    public Item[] inv;
    public Sprite sp = new Sprite("Bag");
    public Random rand = new Random();
    public Level l;
    public LoadArt la = new LoadArt();
    public String name;
    public AI ai;
    public RogueEntity(Level l1){
        l=l1;
    }
    /**
     * Moves Entity
     * @param d Direction
     */
    public void move(Direction d){
        int dx=0;
        int dy=0;
        switch(d){
            case UP:
                dy=-1;
                break;
            case DOWN:
                dy=1;
                break;
            case LEFT:
                dx=-1;
                break;
            case RIGHT:
                dx=1;
                break;
            default:
                dx=0;
                dy=0;
                break;
        }
        for(RogueEntity re:l.getEntities()){
            if(re != null){
                if(re.x==x+dx && re.y==y+dy) return;
            }
        }
        if(l.getPlayer().x==x+dx && l.getPlayer().y==y+dy) return;
        if(dx+x>=l.board.length || dy+y>=l.board[0].length) return;
        if(dx+x>=0 && dy+y>=0){
            if(l.board[dx+x][dy+y]==true){
                x+=dx;
                y+=dy;
            }
        }
    }
    /**
     * Take damage from
     * @param e 
     */
    public void damage(RogueEntity e){
        double td = e.maxDefence*10;
        double ta = e.maxAtt*10;
        if(ta!=0){
            ta = rand.nextInt((int) ta);
            ta /= 10;
        }else{
            ta=0;
        }
        if(td!=0){
            td = rand.nextInt((int) td);
            td /= 10;
        }else{
            td=0;
        }
        ta -= td;
        if(ta<0){
            ta=0;
        }
        if(ta>=0){
            health-=ta;
        }
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
    }
    /**
     * What to do when this entity dies
     */
    public void death(){
    }
    /**
     * What to do when this entity is initialized
     * @param r
     */
    public void spawn(Room r){
        if(r==null) l.removeEntity(this);
        if(r==null) return;
        x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][0];
        y=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][1];
        for(RogueEntity e:l.getEntities()){
            if(e==null || e==this){
                continue;
            }
            if(x==e.x && y==e.y){
                x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][0];
                y=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][1];
            }
        }
    }
    /**
     * Gets the distance to an entity
     * @param e
     * @return
     */
    public double distTo(RogueEntity e){
        return Math.sqrt(((x-e.x)*(x-e.x))+((y-e.y)*(y-e.y)));
    }
}
