package Entity.NPC;

import AI.AI;
import Level.Level;
import Entity.Player;
import Entity.RogueEntity;
import java.util.Random;

/**
 * @author Torri
 */
public abstract class NPC extends RogueEntity{
    AI a = new AI(this);
    Level l = new Level(5);
    Player p = l.getHostilePlayer();
    public int maxAtt, drops;
    Random r = new Random();
    public int attack(){
        return r.nextInt(maxAtt);
    }
    public void turn(){
        this.move(a.pointTowards(p));
    }
}
