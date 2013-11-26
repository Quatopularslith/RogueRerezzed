/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import dungeon.Room;

/**
 *
 * @author Torri
 */
public class Goblin extends RogueHostileEntity{
    public Goblin(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Goblin",3,25,3,3);
    }
}
