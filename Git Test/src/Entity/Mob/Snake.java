package Entity.Mob;
/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    public Snake(int spawnX, int spawnY, int lvl){
        health=10*(lvl/4);
        maxAtt=2*(lvl/2);
        armour=0;
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
}
