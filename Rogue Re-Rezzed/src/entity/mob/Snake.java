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
 * A snake. An evil and spasmodic foe which spawns and fights in great numbers.
 * @author Torri
 */
public class Snake extends RogueHostileEntity{
    public Snake(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Snake",Operation.MULT,5,Operation.ADD,5,10);
    }
}
