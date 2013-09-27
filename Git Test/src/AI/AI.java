package AI;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public class AI {
    double pdir,x,y,ex,ey,m;
    boolean doatt;
    RogueEntity here;
    public AI(RogueEntity e){
        here=e;
    }
    public int pointTowards(RogueEntity e){
        x=here.x;
        y=here.y;
        ex = e.x;
        ey = e.y;
        m=(ey-y)/(ex-x);
        if(ex==x){
            if(ey>=y){
                pdir=90;
            }else{
                pdir=270;
            }
        }else{
            if(ex>x && ey>=y){//Quad 1
                pdir=Math.toDegrees(Math.atan(x));
            }else if(ex>x && ey<y){//Quad 2
                pdir=360+Math.toDegrees(Math.atan(x));
            }else if(ex<x && ey<y){//Quad 3
                pdir=180+Math.toDegrees(Math.atan(x));
            }else if(ex < x && ey>=y){//Quad 4
                pdir=180+Math.toDegrees(Math.atan(x));
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
