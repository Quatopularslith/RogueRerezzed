/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MortuusTrabajos extends RogueHostileEntity{
    public MortuusTrabajos(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=100*(lvl);
        this.maxhealth=(int) this.health;
        maxAtt=lvl*50;
        sp = new Sprite("MortuusTrabajos");
        inv = new Item[1];
        inv[0] = new Item(4,l);
        System.out.println("MortuusTrabajos "+inv[0].id);
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(pointTowards(this.l.getPlayer()));
        if((l.getPlayer().x-this.x<=1 && l.getPlayer().y-this.y<=1)&&(-l.getPlayer().x+this.x<=1 && -l.getPlayer().y+this.y<=1)){
            l.getPlayer().damage(rand.nextInt(maxAtt));
        }
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
