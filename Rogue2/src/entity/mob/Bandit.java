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
public class Bandit extends RogueHostileEntity{
    public Bandit(int lvl,Room r,Level l1){
        super(l1);
        l=l1;
        health=20*(lvl);
        this.maxhealth=(int) this.health;
        maxAtt=lvl+6;
        sp = new Sprite("Bandit");
        inv = new Item[1];
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(this.pointTowards(this.l.getPlayer()));
        if((l.getPlayer().x-this.x<=1 && l.getPlayer().y-this.y<=1)&&(-l.getPlayer().x+this.x<=1 && -l.getPlayer().y+this.y<=1)){
            l.getPlayer().damage(rand.nextInt(maxAtt));
        }
    }
}
