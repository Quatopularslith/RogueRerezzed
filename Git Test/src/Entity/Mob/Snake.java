package Entity.Mob;

import Render.Sprite;

/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    public Snake(int spawnX, int spawnY, int lvl){
        health=10*(lvl/4);
        maxAtt=2*(lvl/2);
        armour=0;
        tx = spawnX;
        ty = spawnY;
        dir = 0;
        sp = new Sprite("Snake.png");
    }
}