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
        tx=spawnX;
        ty=spawnY;
    }
    @Override
    public void turn(){
        if(MainMenu.key.up){
            ty++;
        }
        if(MainMenu.key.down){
            ty--;
        }
        if(MainMenu.key.left){
            tx--;
        }
        if(MainMenu.key.right){
            tx++;
        }
    }
}