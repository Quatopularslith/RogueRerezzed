/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.item.Item;
import render.Sprite;

/**
 *
 * @author Mnenmenth
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
        inv[0] = new Item(rand.nextInt(Item.numid),this,l);
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    @Override
    public void turn(){
        this.move(pointTowards(this.l.getPlayer()));
        if(doatt(l.getPlayer())){
            l.getPlayer().damage(rand.nextInt(maxAtt));
        }
    }
}
