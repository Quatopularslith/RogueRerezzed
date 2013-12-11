
package entity.npc;

import dungeon.Level;
import entity.Direction;
import entity.RogueEntity;
import render.Sprite;

/**
 * Master for all new NPCs
 * @author Torri
 */
public class RogueNPC extends RogueEntity{
    public boolean hostile;
    /**
     * Creates NPC
     * @param l1 
     */
    public RogueNPC(Level l1) {
        super(l1);
        sp=new Sprite("Player");
    }
    public void action(){
        
    }
    @Override
    public void turn(){
        Direction pdir;
        boolean b = rand.nextBoolean();
        int d = rand.nextInt(3);
        if(b){
            switch (d){
                case 0:
                    pdir=Direction.UP;
                    break;
                case 1:
                    pdir=Direction.DOWN;
                    break;
                case 2:
                    pdir=Direction.LEFT;
                    break;
                case 3:
                    pdir=Direction.RIGHT;
                    break;
                default:
                    pdir=Direction.STOP;
                    break;
            }
        }else{
            pdir=Direction.STOP;
        }
        move(pdir);
    }
}
