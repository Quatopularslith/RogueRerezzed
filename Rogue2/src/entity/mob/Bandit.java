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
public class Bandit extends RogueHostileEntity{
    public Bandit(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=20*(lvl);
        this.maxhealth=(int) this.health;
        maxAtt=lvl*2;
        sp = new Sprite("Bandit");
        inv = new Item[1];
        inv[0] = new Item(rand.nextInt(Item.numid),this,l);
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    @Override
    public void turn(){
        this.move(this.pointTowards(this.l.getPlayer()));
        if(doatt(l.getPlayer())){
            l.getPlayer().damage(rand.nextInt(maxAtt));
        }
    }
}
