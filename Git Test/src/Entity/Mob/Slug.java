package Entity.Mob;

public class Slug extends RogueHostileMob{
    public Slug(int spawnX, int spawnY, int lvl){
        health=5*(lvl/4);
        maxAtt=(lvl/2);
        armour=0;
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
}
