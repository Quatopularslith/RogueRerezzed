
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import render.Sprite;
import util.AI;
import util.Operation;

/**
 * Parent class for all monsters
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public int followdist;
    /**
     * creates new entity
     * @param lvl1
     * @param r
     * @param l1 
     * @param name1 
     * @param op0 operation for health
     * @param attmod attack modifier
     * @param op1 operation for attack
     * @param hmod health modifier
     * @param followdist1 follow distance
     */
    public RogueHostileEntity(int lvl1,Room r,Level l1,String name1,Operation op0,int hmod,Operation op1,int attmod,int followdist1) {
        super(l1);
        followdist=followdist1;
        l=l1;
        maxDefence=0;
        ai=new AI(this,followdist);
        this.lvl=lvl1;
        if(name1==null){
            name=this.getClass().toString();
        }else{
            name=name1;
        }
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
        this.maxhealth=this.health;
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
    }
    @Override
    public void turn(){
        ai=new AI(this,followdist);
        if(distTo(l.getPlayer())<=1 && maxAtt>0){
            l.getPlayer().damage(this);
        }
        move(ai.pointTowards(l.getPlayer()));
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
