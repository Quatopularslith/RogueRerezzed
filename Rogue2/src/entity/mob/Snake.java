/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Snake extends RogueHostileEntity{
    public Snake(int lvl,Room r,Level l1){
        super(l1);
        health=10*(lvl/4);
        maxAtt=(lvl);
        sp = new Sprite("Snake");
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(pointTowards(l.getPlayer()));
    }
    public int pointTowards(RogueEntity e){
        double pdir=0;
        int ux=this.x;
        int uy=this.y;
        int ex = e.x;
        int ey = e.y;
        int m=(ey-uy)/(ex-ux);
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
}
