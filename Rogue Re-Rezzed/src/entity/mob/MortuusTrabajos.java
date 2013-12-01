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
 * AKA Secret Mob 2. Boss mob
 * @author Mnenmenth
 */
public class MortuusTrabajos extends RogueHostileEntity{
    public MortuusTrabajos(int lvl,Room r,Level l1){
        super(lvl,r,l1,"MortuusTrabajos",Operation.MULT,50,Operation.MULT,25);
    }
}
