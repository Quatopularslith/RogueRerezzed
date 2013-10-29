package Entity.Mob;

import AI.AI;
import Core.MainMenu;
import Entity.Player;
import Level.Level;
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
    Player p = new Player(0,0);
    public int attack(){
        return r.nextInt(maxAtt);
    }
    @Override
    public void turn(){
        p = l.getHostilePlayer();
        a = new AI(this);
        int d = a.pointTowards(p);
        this.move(d);
    }
}
