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
    public double maxAtt=0,maxDefence=0;
    public int x=0,y=0,uuid=0;
    public float health=0;
    public int maxhealth=0;
    public int lvl=0;
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
        if(d>360){
            dx=0;
            dy=0;
        }else if(d<=45 || d>315){//up
            dy--;
        }else if(d<=135 && d>45){//right
            dx++;
        }else if(d<=225 && d>135){//down
            dy++;
        }else if(d>=225 && d<=315){//left
            dx--;
        }
        for(RogueEntity re:l.getEntities()){
            if(re != null){
                if(re.x==x+dx && re.y==y+dy){
                    dx=0;
                    dy=0;
                }
            }
        }
        if(l.getPlayer().x==x+dx && l.getPlayer().y==y+dy){
            dx=0;
            dy=0;
        }
        if(l.board[dx+x][dy+y]==true){
            x+=dx;
            y+=dy;
        }
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
                if(re.x==x+dx && re.y==y+dy){
                    dx=0;
                    dy=0;
                }
            }
        }
        if(l.getPlayer().x==x+dx && l.getPlayer().y==y+dy){
            dx=0;
            dy=0;
        }
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
        double td = e.maxDefence*100;
        double ta = e.maxAtt*100;
        if(ta>0){
            ta = rand.nextInt((int) ta);
            ta /= 100;
        }else{
            ta=0;
        }
        if(td>0){
            td = rand.nextInt((int) td);
            td /= 100;
        }else{
            td=0;
        }
        ta -= td;
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
        for (Item inv1 : inv) {
            inv1.update();
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
        x=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][0];
        y=r.area[rand.nextInt(r.area.length)][rand.nextInt(r.area[0].length)][1];
        for(RogueEntity e:l.getEntities()){
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
        return Math.sqrt(((e.x-x)^2)+((e.y-y)^2));
    }
}
