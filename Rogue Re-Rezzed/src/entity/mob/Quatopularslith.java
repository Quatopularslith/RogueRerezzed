/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;
import util.Operation;

/**
 * AKA Secret mob 1. This is a boss mob
 * @author Mnenmenth
 */
public class Quatopularslith extends RogueHostileEntity{
    public Quatopularslith(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Quatopularslith",Operation.MULT,100,Operation.MULT,1,100000);
    }
}
