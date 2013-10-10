package Entity.Mob;

public class Spider extends RogueHostileMob{
    public Spider(int spawnX, int spawnY, int lvl){
        health=10*(lvl/4);
        maxAtt=2*(lvl/2);
        armour=0;
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
}
