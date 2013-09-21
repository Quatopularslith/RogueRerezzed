package Entity.Mob;

import AI.AI;
import Entity.RogueEntity;
import java.util.Random;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    AI a = new AI(this);
    public int maxAtt, drops;
    Random r = new Random();
    public int attack(){
        return r.nextInt(maxAtt);
    }
    @Override
    public void turn(){
        
    }
}
