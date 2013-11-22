/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.item;

import dungeon.Level;
import entity.RogueEntity;


/**
 *
 * @author Torri
 */
public class Item extends RogueEntity{
    public boolean cursed;
    public int id;
    public String modifiers;
    //stats in form of
    // Attack Defence Mana Health
    int[] stats;
    public Item(int id1,Level l1) {
        super(l1);
    }
    public void drop(){
        
    }
}
