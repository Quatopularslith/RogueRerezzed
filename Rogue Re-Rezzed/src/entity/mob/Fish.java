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
 * @author Mnenmenth
 */
public class Fish extends RogueHostileEntity{
    public Fish(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Fish",3,5,3,0);
    }
    @Override
    public void turn(){
        this.move(rand.nextInt(360));
    }
}
