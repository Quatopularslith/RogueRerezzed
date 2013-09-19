package AI;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public class AI {
    int direction; // 0=down 1=downleft 2=left 3=upleft 4=up 5=upright 6=right 7=downright
    boolean doatt;
    public int pointTowards(RogueEntity e){
        return direction;
    }
    public boolean doAttack(boolean ranged){
        return doatt;
    }
}