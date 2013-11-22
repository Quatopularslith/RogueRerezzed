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
public class Fish extends RogueEntity{
    public Fish(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=5*(lvl);
        this.maxhealth=(int) this.health;
        sp = new Sprite("Fish");
        inv = new Item[1];
        inv[0] = new Item(rand.nextInt(Item.numid),this,l);
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(rand.nextInt(360));
    }
}
