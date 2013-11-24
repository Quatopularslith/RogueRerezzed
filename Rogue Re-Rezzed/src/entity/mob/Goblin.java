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
 * @author Torri
 */
public class Goblin extends RogueHostileEntity{
    public Goblin(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=25*(lvl);
        this.maxhealth=(int) this.health;
        maxAtt=lvl*3;
        sp = new Sprite("Goblin");
        inv = new Item[1];
        inv[0] = new Item(rand.nextInt(Item.numid),this,l);
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
}
