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
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void turn(){
        for(int i=0;i<Rogue.mm.ki.keyBind.length;i++){
            if(Rogue.mm.ki.keyBind[i]){
                this.move(i*90);
            }
        }
    }
}
