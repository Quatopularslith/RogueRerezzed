package Entity;

import Core.MainMenu;
import Item.Item;
import Render.Sprite;

/**
 * @author Torri
 */
public class Player extends RogueEntity{
    public int taunt=1;
    public Player(int spawnX, int spawnY){
        sp = new Sprite("Player.png");
        health = 100;
        taunt = 3;
        armour = 0;
        dir = 0;
        inv = new Item[10];
        this.maxAtt=50;
        tx=spawnX;
        ty=spawnY;
    }
    @Override
    public void turn(){
        if(MainMenu.key.up){
            this.move(90);
        }
        if(MainMenu.key.down){
            this.move(270);
        }
        if(MainMenu.key.left){
            this.move(180);
        }
        if(MainMenu.key.right){
            this.move(0);
        }
        for(int i=0;i<MainMenu.l.getEntities().size();i++){
            if(MainMenu.l.getEntities().get(i).uuid!=this.uuid && this.distTo(MainMenu.l.getEntities().get(i))<=1){
                this.attack(MainMenu.l.getEntities().get(i));
            }
        }
    }
}