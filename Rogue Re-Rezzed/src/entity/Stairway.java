/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import core.Rogue;
import dungeon.Level;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Stairway extends RogueEntity{
    public int room  = rand.nextInt(l.numRooms);
    public Stairway(Level l1) {
        super(l1);
        room = rand.nextInt(l1.numRooms-2);
        this.spawn(l1.getRoom(room));
        this.sp = new Sprite("Stairway");
    }
    @Override
    public void turn(){
        if(l.getPlayer().x==x&&l.getPlayer().y==y){
            Rogue.setLevel(new Level());
        }
    }
    @Override
    public void death(){}
}
