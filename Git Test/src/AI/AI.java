package AI;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public class AI {
    double pdir,x,y,ex,ey;
    int direction,round;
    boolean doatt;
    RogueEntity here;
    public AI(RogueEntity e){
        here=e;
        x=e.x;
        y=e.y;
    }
    public int pointTowards(RogueEntity e){
        x=here.x;
        y=here.y;
        ex = e.x;
        ey = e.y;
        pdir = Math.atan((ey-y)/(ex-x));
        pdir = Math.toDegrees(pdir);
        if((ex-x)>=0 && (ey-y)>0){//Q1
        }else if((ex-x)<=0 && (ey-y)>=0){//Q2
            pdir += 270;
        }else if((ex-x)<=0 && (ey-y)<=0){//Q3
            pdir += 180;
        }else if((ex-x)>=0 && (ey-y)<=0){//Q4
            pdir += 90;
        }
        round = (int) pdir%90;
        direction = 90*Math.round(round);
        return direction;
    }
    public boolean doAttack(boolean ranged, RogueEntity e){
        if(ranged==false){
            e.distTo(here);
        }else{
            
        }
        return doatt;
    }
}
