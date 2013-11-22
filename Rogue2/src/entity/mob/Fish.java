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
 * @author Mnenmenth
 */
public class Fish extends RogueHostileEntity{
    public Fish(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=5*(lvl);
        this.maxhealth=(int) this.health;
        maxAtt=lvl+2;
        sp = new Sprite("Fish");
        inv = new Item[1];
        inv[0] = new Item(rand.nextInt(Item.numid),l);
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(pointTowards(this.l.getPlayer()));
        if((l.getPlayer().x-this.x<=1 && l.getPlayer().y-this.y<=1)&&(-l.getPlayer().x+this.x<=1 && -l.getPlayer().y+this.y<=1)){
            l.getPlayer().damage(rand.nextInt(maxAtt));
        }
    }
}
