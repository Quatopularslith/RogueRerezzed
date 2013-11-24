
package entity.mob;

import dungeon.Level;
import entity.RogueEntity;
import entity.item.Item;

/**
 *
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public RogueHostileEntity(Level l1) {
        super(l1);
    }
    public void att(RogueEntity e){
        e.damage(rand.nextInt(maxAtt));
    }
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
    public boolean doatt(RogueEntity e){
        return ((x-1==e.x || x+1==e.x) && (y-1==e.y || y+1==e.y));
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
