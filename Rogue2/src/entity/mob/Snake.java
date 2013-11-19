/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Snake extends RogueHostileEntity{
    public Snake(int lvl,Room r,Level l1){
        super(l1);
        health=10*(lvl/4);
        maxAtt=2*(lvl/2);
        sp = new Sprite("Snake");
        spawn(r);
    }
    @Override
    public void turn(){
        this.move(270);
    }
}
