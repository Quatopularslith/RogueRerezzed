package Entity;

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
        inv = new int[10];
        tx=spawnX;
        ty=spawnY;
    }
}