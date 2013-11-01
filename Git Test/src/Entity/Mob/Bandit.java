package Entity.Mob;

import Render.Sprite;

/**
 * @author Torri
 */
public class Bandit extends RogueHostileMob{
    public Bandit(int spawnX, int spawnY, int lvl){
        health=40*(lvl/4);
        maxAtt=5*(lvl/2);
        armour=2*(lvl);
        tx = spawnX;
        ty = spawnY;
        dir = 0;
        sp = new Sprite("Bandit.png");
    }
}
