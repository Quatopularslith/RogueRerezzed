package Entity.Mob;

public class Slug extends RogueHostileMob{
    public Slug(int spawnX, int spawnY, int lvl){
        health=5*(lvl/4);
        maxAtt=(lvl/2);
        armour=0;
        tx = spawnX;
        ty = spawnY;
        dir = 0;
    }
}
