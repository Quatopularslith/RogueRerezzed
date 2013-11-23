/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        double m = 0;
        if(ex!=ux){
            m =(ey-uy)/(ex-ux);
        }else{
            ex+=0.1;
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
