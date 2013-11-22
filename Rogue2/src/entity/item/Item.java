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
    public static int numid = 5;
    public boolean cursed;
    public int id;
    public static final String[] modifiers = {"","Broken ","Ordinary ","Shattered ","Old ","Healthy ","Magical "};
    public static final String[] names = {"Empty","Wood Sword","Wood Axe","Wood Stick","Piece of Bark","Potion Bottle"};
    public String name;
    //stats in form of
    // Attack Defence Mana Health
    int[] stats;
    public Item(int id1,Level l1) {
        super(l1);
        id1=id;
        System.out.println(id1);
        if(id!=0){
            name = modifiers[rand.nextInt(modifiers.length)]+names[id];
            cursed = rand.nextBoolean();
        }else{
            name = names[0];
        }
    }
    public void drop(){
        l.addEntity(this);
    }
    @Override
    public void turn(){}
}
