/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.player;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Player extends RogueEntity{
    public Player(Level l1){
        super(l1);
        health=100;
        maxhealth=150;
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void turn(){
        if(Rogue.mm.ki.keyBind[0]){//up
            this.move(0);
        }
        if(Rogue.mm.ki.keyBind[1]){//down
            this.move(180);
        }
        if(Rogue.mm.ki.keyBind[2]){//right
            this.move(90);
        }else if(Rogue.mm.ki.keyBind[3]){//left
            this.move(270);
        }
    }
}
