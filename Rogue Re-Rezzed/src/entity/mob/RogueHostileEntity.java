
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Item;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    /**
     * creates new entity
     * operations:
     * 0 add
     * 1 sub
     * 2 mult
     * 3 div
     * @param lvl
     * @param r
     * @param l1 
     * @param name 
     * @param op0 operation for attack
     * @param attmod attack modifier
     * @param op1 operation for health
     * @param hmod health modifier
     */
    public RogueHostileEntity(int lvl,Room r,Level l1,String name,int op0,int attmod,int op1,int hmod) {
        super(l1);
        l=l1;
        switch(op0){
            case 0:
                health=hmod+(lvl);
                break;
            case 1:
                health=hmod-(lvl);
                break;
            case 2:
                health=hmod*(lvl);
                break;
            case 3:
                health=hmod/(lvl);
        }
        this.maxhealth=(int) this.health;
        maxAtt=lvl*attmod;
        sp = new Sprite(name);
        inv = new Item[1];
        inv[0] = new Item(rand.nextInt(Item.numid),this,l);
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
            l.getPlayer().damage(rand.nextInt(maxAtt));
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
