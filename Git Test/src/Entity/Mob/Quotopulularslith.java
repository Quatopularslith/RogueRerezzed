package Entity.Mob;

import Render.Sprite;

public class Quotopulularslith extends RogueHostileMob{
    public Quotopulularslith(int spawnX, int spawnY, int lvl){
        health=20+(lvl);
        maxAtt=(lvl+10);
        armour=10+lvl;
        x = spawnX;
        y = spawnY;
        dir = 0;
        sp = new Sprite("Quotopularslith.png");
    }
}
