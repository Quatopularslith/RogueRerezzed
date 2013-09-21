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
//        if((ey-y)==0){
//            ey+=.01;
//            System.out.println("oopsx");
//        }
//        if((ex-x)==0){
//            ex+=.01;
//            System.out.println("oopsy");
//        }
//        System.out.println((ey-y)/(ex-x));
//        pdir = Math.atan((ey-y)/(ex-x));
//        pdir = Math.toDegrees(pdir);
//        System.out.println(pdir);
//        direction = (int) (-1*(Math.round(pdir)+1));
//        return direction;
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
