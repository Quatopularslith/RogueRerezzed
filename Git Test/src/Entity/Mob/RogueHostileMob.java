package Entity.Mob;

import AI.AI;
import Core.MainMenu;
import Level.Level;
import Entity.Player;
import Entity.RogueEntity;
import java.util.Random;

/**
 * @author Torri
 */
public abstract class RogueHostileMob extends RogueEntity{
    AI a = new AI(this);
    Level l = MainMenu.l;
    public int maxAtt, drops;
    Random r = new Random();
    public int attack(){
        return r.nextInt(maxAtt);
    }
    public void turn(){
        Player p = l.getHostilePlayer();
        this.move(a.pointTowards(p));
    }
}
