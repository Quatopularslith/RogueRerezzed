
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.Direction;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import java.util.List;
import render.Sprite;
import util.Astar;
import util.Node;
import util.Operation;
import util.Vector2i;

/**
 * Parent class for all monsters
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public int followdist;
    /**
     * creates new entity
     * @param lvl
     * @param r
     * @param l1 
     * @param name 
     * @param op0 operation for health
     * @param attmod attack modifier
     * @param op1 operation for attack
     * @param hmod health modifier
     * @param followdist1 follow distance
     */
    public RogueHostileEntity(int lvl,Room r,Level l1,String name,Operation op0,int hmod,Operation op1,int attmod,int followdist1) {
        super(l1);
        followdist=followdist1;
        l=l1;
        maxDefence=0;
        this.lvl=lvl;
        switch(op0){
            case ADD:
                health=hmod+(lvl);
                break;
            case SUB:
                health=hmod-(lvl);
                break;
            case MULT:
                health=hmod*(lvl);
                break;
            case DIV:
                health=hmod/(lvl);
                break;
        }
        switch(op1){
            case ADD:
                maxAtt=attmod+(lvl);
                break;
            case SUB:
                maxAtt=attmod-(lvl);
                break;
            case MULT:
                maxAtt=attmod*(lvl);
                break;
            case DIV:
                maxAtt=attmod/(lvl);
        }
        this.maxhealth=(int) this.health;
        sp = new Sprite(name);
        inv = new Item[2];
        if(lvl>Item.numid/3){
            inv[0] = new Item(rand.nextInt((Item.numid)+1),this,lvl,l);
            inv[1] = new Gold(rand.nextInt((Item.numid)+1),this,l);
        }else{
            inv[0] = new Item(rand.nextInt((lvl*3)+1),this,lvl,l);
            inv[1] = new Gold(rand.nextInt(lvl+1),this,l);
        }
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    @Override
    public void turn(){
//        move(pathFind(l.getPlayer()));
        move(pointTowards(l.getPlayer()));
        if(doatt(l.getPlayer()) && maxAtt>0){
            l.getPlayer().damage(this);
        }
    }
    /**
     * basic tracer AI
     * @param e
     * @return 
     */
    public Direction pointTowards(Node e){
        Direction pdir=Direction.STOP;
        if(distTo(e.tile.getEntity())<followdist){
            if(x<e.tile.getX())pdir = Direction.RIGHT;
            if(x>e.tile.getX())pdir = Direction.LEFT;
            if(y<e.tile.getY())pdir = Direction.DOWN;
            if(y>e.tile.getY())pdir =Direction.UP;
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        pdir=Direction.UP;
                        break;
                    case 1:
                        pdir=Direction.DOWN;
                        break;
                    case 2:
                        pdir=Direction.LEFT;
                        break;
                    case 3:
                        pdir=Direction.RIGHT;
                        break;
                    default:
                        pdir=Direction.STOP;
                        break;
                }
            }else{
                pdir=Direction.STOP;
            }
        }
        return pdir;
    }
    public Direction pointTowards(RogueEntity e){
        Direction pdir=Direction.STOP;
        if(distTo(e)<followdist){
            if(x<e.x)pdir = Direction.RIGHT;
            if(x>e.x)pdir = Direction.LEFT;
            if(y<e.y)pdir = Direction.DOWN;
            if(y>e.y)pdir =Direction.UP;
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        pdir=Direction.UP;
                        break;
                    case 1:
                        pdir=Direction.DOWN;
                        break;
                    case 2:
                        pdir=Direction.LEFT;
                        break;
                    case 3:
                        pdir=Direction.RIGHT;
                        break;
                    default:
                        pdir=Direction.STOP;
                        break;
                }
            }else{
                pdir=Direction.STOP;
            }
        }
        return pdir;
    }
    /**
     * FINALY A*
     * @param e 
     * @return  
     */
    public Direction pathFind(RogueEntity e){
        Direction out = Direction.STOP;
        if(distTo(e)<=followdist){
            List<Node> path = Astar.findPath(new Vector2i(x,y), new Vector2i(e.x,e.y));
            if(path==null)return Direction.STOP;
            out = pointTowards(path.get(path.size()-1));
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        out=Direction.UP;
                        break;
                    case 1:
                        out=Direction.DOWN;
                        break;
                    case 2:
                        out=Direction.LEFT;
                        break;
                    case 3:
                        out=Direction.RIGHT;
                        break;
                    default:
                        out=Direction.STOP;
                        break;
                }
            }else{
                out=Direction.STOP;
            }
        }
        return out;
    }
    /**
     * asks if it can attack
     * @param e
     * @return 
     */
    public boolean doatt(RogueEntity e){
//        return (e.x-x<=1 && e.x-x>=-1) && (e.y-y<=1 && e.y-y>=-1);
        return ((e.x-x)==0 && (e.y-y)>=-1 && (e.y-y)<=1) || ((e.y-y)==0 && (e.x-x)>=-1 && (e.x-x)<=1);
    }
    @Override
    public void death(){
        if(inv!=null){
            for (Item inv1 : inv) {
                inv1.drop();
            }
        }
        l.removeEntity(this);
    }
}
