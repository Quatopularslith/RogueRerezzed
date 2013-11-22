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
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Player extends RogueEntity{
    public int maxMana;//mana=magic points
    public int mana;
    public int kills;
    boolean attack = false;
    int att = 5;
    int currinv = 0;
    public Player(Level l1){
        super(l1);
        inv = new Item[10];
        for(int i=0;i<inv.length;i++){
            inv[i]=new Item(0,l);
        }
        mana=50;
        maxMana=100;
        health=100;
        maxhealth=150;
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void turn(){
        l=Rogue.getLevel();
        if(health>=1 && health<maxhealth){
            health++;
        }else{
            
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(rand.nextInt(att));
                    if(re.health<=0){
                        re.death();
                    }
                    attack=true;
                }else if(re instanceof Item && re.x==this.x && re.y==this.y-1){
                    this.inv[currinv]=(Item) re;
                    currinv++;
                    re.death();
                }
            }
            if(!attack){
                this.move(0);
            }
        }else if(Rogue.mm.ki.keyBind[1]){//down
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    re.damage(rand.nextInt(att));
                    if(re.health<=0){
                        re.death();
                    }
                    attack=true;
                }else if(re instanceof Item && re.x==this.x && re.y==this.y+1){
                    this.inv[currinv]=(Item) re;
                    currinv++;
                    re.death();
                }
            }
            if(!attack){
                this.move(180);
            }
        }else if(Rogue.mm.ki.keyBind[2]){//right
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(rand.nextInt(att));
                    if(re.health<=0){
                        re.death();
                    }
                    attack=true;
                }else if(re instanceof Item && re.x==this.x+1 && re.y==this.y){
                    this.inv[currinv]=(Item) re;
                    currinv++;
                    re.death();
                }
            }
            if(!attack){
                this.move(90);
            }
        }else if(Rogue.mm.ki.keyBind[3]){//left
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(rand.nextInt(att));
                    if(re.health<=0){
                        re.death();
                    }
                    attack=true;
                }else if(re instanceof Item && re.x==this.x-1 && re.y==this.y){
                    this.inv[currinv]=(Item) re;
                    currinv++;
                    re.death();
                }
            }
            if(!attack){
                this.move(270);
            }
        }
    }
}
