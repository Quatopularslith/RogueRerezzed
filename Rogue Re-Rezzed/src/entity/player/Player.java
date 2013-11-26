/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.player;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Item;
import entity.mob.RogueHostileEntity;
import render.RenderPanel;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Player extends RogueEntity{
    public static boolean dead=false;
    public int maxMana;//mana=magic points
    public int mana;
    public static int kills;
    boolean attack = false;
    int currinv = 0;
    public static Item[] pinv;
    public static int xp=0;
    public static int xplevels=1;
    public Player(Level l1){
        super(l1);
        currinv=0;
        if(pinv==null){
            pinv = new Item[10];
            for(int i=0;i<pinv.length;i++){
                pinv[i]=new Item(0,this,l);
            }
        }
        defence=(int) (0);
        maxAtt=2;
        mana=(int) (50);
        maxMana=(int) (100);
        health=(float) (100);
        maxhealth=(int) (100);
        for (Item inv1 : pinv) {
            this.maxhealth+=inv1.stats[3];
            this.maxMana+=inv1.stats[2];
            this.defence+=inv1.stats[1];
            this.maxAtt+=inv1.stats[0];
            inv1.update();
        }
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void death(){
        dead=true;
    }
    @Override
    public void turn(){
        l=Rogue.getLevel();
        if(health<=0){
            dead=true;
        }else if(health<maxhealth){
            health+=0.1;
        }
        if(xp%10*(xplevels)==0 && xp>1){
            xplevels++;
        }
        maxAtt=2;
        maxMana=100;
        maxhealth=100;
        defence=0;
        for (Item inv1 : pinv) {
            this.maxhealth+=inv1.stats[3];
            this.maxMana+=inv1.stats[2];
            this.defence+=inv1.stats[1];
            this.maxAtt+=inv1.stats[0];
            inv1.update();
        }
        l=Rogue.getLevel();
        for (int j=0;j<l.getItems().size();j++) {
            Item i = l.getItems().get(j);
            if(i.x==this.x && i.y==this.y && pinv[currinv]!=i){
                Player.pinv[currinv]=i;
                RenderPanel.pickup = i;
                if(currinv<pinv.length-1 && !pinv[currinv].name.equalsIgnoreCase("EMPTY")){
                    currinv++;
                }
                i.death();
            }
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(rand.nextInt(maxAtt));
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                this.move(0);
            }
        }else if(Rogue.mm.ki.keyBind[1]){//down
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    re.damage(rand.nextInt(maxAtt));
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                this.move(180);
            }
        }else if(Rogue.mm.ki.keyBind[2]){//right
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(rand.nextInt(maxAtt));
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                this.move(90);
            }
        }else if(Rogue.mm.ki.keyBind[3]){//left
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(rand.nextInt(maxAtt));
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                this.move(270);
            }
        }
    }
}
