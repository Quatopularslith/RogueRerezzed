package Entity.Mob;
/**
 * @author Torri
 */
public class Bandit extends RogueHostileMob{
    public Bandit(int spawnX, int spawnY, int lvl){
        health=40*(lvl/4);
        maxAtt=5*(lvl/2);
        armour=2*(lvl);
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
}
