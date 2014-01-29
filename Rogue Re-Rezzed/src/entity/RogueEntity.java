package entity;

import art.LoadArt;
import dungeon.Level;
import entity.item.Item;
import entity.player.Player;
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
    public Sprite sp;
    public Random rand = new Random();
    public Level l;
    public LoadArt la = new LoadArt();
    public String name;
    public AI ai;
    public RogueEntity(Level l1){
        l=l1;
        name=this.getClass().getSimpleName();
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
    public void death(){}
    /**
     * What to do when this entity is initialized
     * @param l
     */
    public void spawn(Level l){
        x=rand.nextInt(l.board.length);
        y=rand.nextInt(l.board[0].length);
        while(!l.board[x][y] || !((this instanceof Player || this instanceof Stairway) ? true : distTo(l.getPlayer())>40)){
            if(l.board[x][y] && ((this instanceof Player || this instanceof Stairway) ? true : distTo(l.getPlayer())>40)) break;
            x=rand.nextInt(l.board.length);
            y=rand.nextInt(l.board[0].length);
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
    public static void spawner(int am,int lvl,Level l){
        RogueEntity e = new RogueEntity(l);
        Random rand = new Random();
        int type;
        int elvl;
        for(int i=0;i<am;i++){
            elvl = rand.nextInt(lvl+1)+1;
            type = rand.nextInt(4);
            if(type==0){
                e = new entity.mob.Snake(elvl,l);
            }else if(type==1){
                e = new entity.mob.Bandit(elvl,l);
            }else if(type==2){
                e = new entity.mob.Fish(elvl,l);
            }else if(type==3){
                e = new entity.mob.Goblin(elvl,l);
            }
            l.addEntity(e);
        }
    }
}
