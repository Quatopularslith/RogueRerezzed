package Entity.Mob;

import AI.AI;
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
    @Override
    public void turn(){
        p = l.getHostilePlayer();
        a = new AI(this);
        int d = a.pointTowards(p);
        this.move(d);
    }
}