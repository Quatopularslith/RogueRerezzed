/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import entity.RogueEntity;

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
        int ux=this.x;
        int uy=this.y;
        int ex = e.x;
        int ey = e.y;
        int m;
        if(ex!=ux){
            m =(ey-uy)/(ex-ux);
        }else{
            ex++;
            m =(ey-uy)/(ex-ux);
        }
        if(ex==ux){
            if(ey>=uy){
                pdir=90;
            }else{
                pdir=270;
            }
        }else if(ey==uy){
            pdir=180;
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
}
