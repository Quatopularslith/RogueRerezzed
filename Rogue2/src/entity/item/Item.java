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
    public static final String[] names = {"Empty","Stone Sword","Stone Axe","Stick","Wood Shield","Potion"};
    public String name;
    //stats in form of
    // Attack,Defence,Mana,Health
    public int[] stats = new int[4];
    public Item(int id1,RogueEntity parent1,Level l1) {
        super(l1);
        health=1;
        maxhealth=1;
        parent=parent1;
        id=id1;
        sp=new Sprite("Bag",16);
        modifierid=rand.nextInt(modifiers.length);
        if(id!=0){
            name = "Lvl "+Level.numLevels+" "+modifiers[modifierid]+names[id];
            cursed = rand.nextBoolean();
            switch(id){
                case 1:
                    stats[0]=(int) (5*(0.4*Level.numLevels));
                    stats[1]=0;
                    stats[2]=0;
                    stats[3]=0;
                case 2:
                    stats[0]=(int) (2*(0.4*Level.numLevels));
                    stats[1]=0;
                    stats[2]=0;
                    stats[3]=0;
                case 3:
                    stats[0]=(int) (1*(0.4*Level.numLevels));
                    stats[1]=0;
                    stats[2]=0;
                    stats[3]=0;
                case 4:
                    stats[0]=0;
                    stats[1]=(int) (5*(0.4*Level.numLevels));
                    stats[2]=0;
                    stats[3]=0;
            }
            switch(modifierid){
                case 1:
                    stats[0]-=(int) (1*(0.4*Level.numLevels));
                case 3:
                    stats[1]-=(int) (1*(0.4*Level.numLevels));
                case 5:
                    stats[3]+=(int) (1*(1.4*Level.numLevels));
                case 6:
                    stats[2]+=(int) (1*(0.4*Level.numLevels));
                case 7:
                    stats[0]+=(int) (5*(0.4*Level.numLevels));
            }
        }else{
            name = names[0];
            for(int ind:stats){
                ind=0;
            }
        }
        this.x=parent.x;
        this.y=parent.y;
    }
    public void drop(){
        this.x=parent.x;
        this.y=parent.y;
        if(this.id!=0){
            System.out.println("Dropped: "+name+" @ ("+x+","+y+")");
            l.addItem(this);
        }
    }
    public void update(){
        this.x=parent.x;
        this.y=parent.y;
    }
    @Override
    public void death(){
        l.removeItem(this);
    }
    @Override
    public void turn(){}
}
