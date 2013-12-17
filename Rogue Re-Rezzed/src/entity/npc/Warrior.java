
package entity.npc;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.mob.RogueHostileEntity;
import util.Direction;

/**
 *
 * @author Torri
 */
public class Warrior extends RogueNPC{
    RogueEntity follow;
    public Warrior(Room r,Level l1) {
        super(r,l1);
        maxAtt=10;
        maxhealth=1;
        health=1;
    }
    @Override
    public void turn(){
        for(RogueEntity re : l.getEntities()){
            if(re==null || re.equals(this) || !(re instanceof RogueHostileEntity)){
                continue;
            }
            if(follow==null){
                follow=re;
            }
            if(follow.health<=0){
                follow=re;
            }
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
        if(distTo(follow)<=1){
            follow.damage(this);
        }
    }
}
