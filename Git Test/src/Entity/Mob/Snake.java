package Entity.Mob;
/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    public Snake(int spawnX, int spawnY){
        health=10;
        maxAtt=2;
        armour=2;
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
}
