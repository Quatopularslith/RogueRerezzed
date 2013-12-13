
package entity.npc;

import dungeon.Level;
import entity.Direction;
import entity.RogueEntity;

/**
 *
 * @author Torri
 */
public class Warrior extends RogueNPC{
    RogueEntity follow;
    public Warrior(Level l1) {
        super(l1);
    }
    @Override
    public void turn(){
        for(RogueEntity re : l.getEntities()){
            if(distTo(re)<distTo(follow)){
                follow=re;
            }
        }
        Direction pdir=Direction.STOP;
        if(x<follow.x)pdir = Direction.RIGHT;
        if(x>follow.x)pdir = Direction.LEFT;
        if(y<follow.y)pdir = Direction.DOWN;
        if(y>follow.y)pdir =Direction.UP;
        move(pdir);
    }
}
