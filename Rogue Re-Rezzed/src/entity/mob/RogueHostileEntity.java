
package entity.mob;

import dungeon.Level;
import entity.RogueEntity;
import entity.item.Item;

/**
 *
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    /**
     * 
     * @param l1 
     */
    public RogueHostileEntity(Level l1) {
        super(l1);
    }
    @Override
    public void turn(){
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
