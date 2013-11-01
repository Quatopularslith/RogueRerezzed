package AI;
import Entity.RogueEntity;
/**
 * @author Torri
 */
public class AI {
    double pdir,ux,uy,ex,ey,m;
    boolean doatt;
    RogueEntity here;
    public AI(RogueEntity e){
        here=e;
    }
    public int pointTowards(RogueEntity e){
        System.out.println(e.tx);
        System.out.println(e.ty);
        ux=here.tx;
        uy=here.ty;
        ex = e.tx;
        ey = e.ty;
        m=(ey-uy)/(ex-ux);
        if(ex==ux){
            if(ey>=uy){
                pdir=90;
            }else{
                pdir=270;
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
    public boolean doAttack(boolean ranged, RogueEntity e){
        if(ranged==false && here.distTo(e)<2){
            doatt=true;
        }else{
            doatt=false;
        }
        return doatt;
    }
}
