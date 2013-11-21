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
import entity.Stairway;
import entity.mob.RogueHostileEntity;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Player extends RogueEntity{
    boolean attack = false;
    int att = 5;
    public Player(Level l1){
        super(l1);
        health=100;
        maxhealth=150;
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void turn(){
        if(health>=1 && health<maxhealth){
            health++;
        }else{
            
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(rand.nextInt(att));
                    attack=true;
                }else if(re instanceof Stairway){
                    Rogue.setLevel(new Level());
                }
            }
            if(!attack){
                this.move(0);
            }
        }else if(Rogue.mm.ki.keyBind[1]){//down
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    re.damage(rand.nextInt(att));
                    attack=true;
                }else if(re instanceof Stairway){
                    Rogue.setLevel(new Level());
                }
            }
            if(!attack){
                this.move(180);
            }
        }else if(Rogue.mm.ki.keyBind[2]){//right
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(rand.nextInt(att));
                    attack=true;
                }else if(re instanceof Stairway){
                    Rogue.setLevel(new Level());
                }
            }
            if(!attack){
                this.move(90);
            }
        }else if(Rogue.mm.ki.keyBind[3]){//left
            for(RogueEntity re:l.getEntities()){
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(rand.nextInt(att));
                    attack=true;
                }else if(re instanceof Stairway){
                    Rogue.setLevel(new Level());
                }
            }
            if(!attack){
                this.move(270);
            }
        }
    }
}
