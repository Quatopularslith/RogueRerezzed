/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.item;

import dungeon.Level;
import entity.RogueEntity;
import render.Sprite;


/**
 *
 * @author Torri
 */
public class Item extends RogueEntity{
    private final RogueEntity parent;
    public int modifierid;
    public static int numid = 5;
    public boolean cursed;
    public int id;
    public static final String[] modifiers = {"","Broken ","Ordinary ","Shattered ","Old ","Healthy ","Magical ","Strong "};
    public static final String[] names = {"Empty","Wood Sword","Wood Axe","Stick","Wood Shield","Potion"};
    public String name;
    //stats in form of
    // Attack,Defence,Mana,Health
    int[] stats;
    public Item(int id1,RogueEntity parent1,Level l1) {
        super(l1);
        health=1;
        maxhealth=1;
        parent=parent1;
        id=id1;
        sp=new Sprite("Bag");
        modifierid=rand.nextInt(modifiers.length);
        if(id!=0){
            name = modifiers[modifierid]+names[id];
            cursed = rand.nextBoolean();
            if(id==1){
                if(modifierid==1){
//                    stats
                }
            }
        }else{
            name = names[0];
        }
        this.x=parent.x;
        this.y=parent.y;
    }
    public void drop(){
        this.x=parent.x;
        this.y=parent.y;
        System.out.println("Dropped: "+name+" @ ("+x+","+y+")");
        l.addEntity(this);
    }
    public void update(){
        this.x=parent.x;
        this.y=parent.y;
    }
    @Override
    public void turn(){}
    @Override
    public void death(){
        this.health=-10000;
    }
}
