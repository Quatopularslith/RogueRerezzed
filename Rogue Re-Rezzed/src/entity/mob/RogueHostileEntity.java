
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Item;
import render.Sprite;
import util.Operation;

/**
 * Parent class for all monsters
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
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
     */
    public RogueHostileEntity(int lvl,Room r,Level l1,String name,Operation op0,int hmod,Operation op1,int attmod) {
        super(l1);
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
        inv = new Item[1];
        if(lvl>Item.numid/3){
            lvl=Item.numid/3;
        }
        inv[0] = new Item(rand.nextInt((lvl*3)+1),this,l);
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
        if(doatt(l.getPlayer())){
            l.getPlayer().damage(rand.nextInt((int) maxAtt));
        }
    }
    /**
     * basic tracer AI
     * @param e
     * @return 
     */
    public int pointTowards(RogueEntity e){
        double pdir=0;
        double ux=this.x;
        double uy=this.y;
        double ex = e.x;
        double ey = e.y;
        if(ex==ux){
            if(ey>uy){
                pdir=180;
            }else{
                pdir=0;
            }
        }else{
            if(ex>ux && ey>=uy){//Quad 1
                pdir=Math.toDegrees(Math.atan(ux));
            }else if(ex>ux && ey<uy){//Quad 2
                pdir=360+Math.toDegrees(Math.atan(ux));
            }else if(ex<ux && ey<uy){//Quad 3
                pdir=180+Math.toDegrees(Math.atan(ux));
            }else if(ex < ux && ey>=uy){//Quad 4
                pdir=180+Math.toDegrees(Math.atan(ux));
            }
        }
        return (int) pdir;
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
