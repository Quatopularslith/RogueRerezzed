
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.Direction;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import render.Sprite;
import util.Operation;

/**
 * Parent class for all monsters
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public int followdist;
    private Direction last=Direction.DOWN;
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
            lvl=Item.numid/3;
        }
        inv[0] = new Item(rand.nextInt((lvl*3)+1),this,l);
        inv[1] = new Gold(rand.nextInt(lvl),this,l);
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    @Override
    public void turn(){
        for (Item inv1 : inv) {
            inv1.update();
        }
        this.move(pointTowards(this.l.getPlayer()));
        if(doatt(l.getPlayer()) && maxAtt>0){
            l.getPlayer().damage(rand.nextInt(Math.abs((int) maxAtt)));
        }
    }
    /**
     * basic tracer AI
     * @param e
     * @return 
     */
    public Direction pointTowards(RogueEntity e){
        Direction pdir=Direction.STOP;
        if(distTo(e)<followdist){
            try{
                int slope = (e.y-y)/(e.x-x);
                double ang = Math.toDegrees(Math.atan(slope))+90;
                if(ang<=45 || ang>315){//up
                    pdir=Direction.UP;
                }else if(ang<=135 && ang>45){//right
                    pdir=Direction.RIGHT;
                }else if(ang<=225 && ang>135){//down
                    pdir=Direction.DOWN;
                }else if(ang>=225 && ang<=315){//left
                    pdir=Direction.LEFT;
                }
            }catch(Exception j){
                if(y>e.y){
                    pdir=Direction.UP;
                }else{
                    pdir=Direction.DOWN;
                }
            }
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(359)+1;
            if(b){
                if(d<=45 || d>315){//up
                    pdir=Direction.UP;
                }else if(d<=135 && d>45){//right
                    pdir=Direction.RIGHT;
                }else if(d<=225 && d>135){//down
                    pdir=Direction.DOWN;
                }else if(d>=225 && d<=315){//left
                    pdir=Direction.LEFT;
                }
            }else{
                pdir=Direction.STOP;
            }
        }
        return pdir;
    }
    /**
     * asks if it can attack
     * @param e
     * @return 
     */
    public boolean doatt(RogueEntity e){
        int dx = Math.abs(x-e.x);
        int dy = Math.abs(y-e.y);
        boolean bx = dx==1 || dx==0;
        boolean by = dy==1 || dy==0;
        boolean out = (bx && by);
        return out;
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
