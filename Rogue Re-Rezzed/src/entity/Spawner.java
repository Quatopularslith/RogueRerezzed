
package entity;

import core.Rogue;
import dungeon.Room;
import java.util.Random;

/**
 *
 * @author Torri
 */
public class Spawner {      
    
    /**
     * spawns stuff
     * @param am
     * @param lvl
     * @param r 
     */

    public static void spawner(int am,int lvl,Room r){

        RogueEntity e = new RogueEntity(r.l);
        Random rand = new Random();
        int type;
        int elvl;
        for(int i=0;i<am;i++){
            elvl = rand.nextInt(lvl+1)+1;
            type = rand.nextInt(4);
            if(type==0){
                e = new entity.mob.Snake(elvl,r,r.l);
            }else if(type==1){
                e = new entity.mob.Bandit(elvl,r,r.l);
            }else if(type==2){
                e = new entity.mob.Fish(elvl,r,r.l);
            }else if(type==3){
                e = new entity.mob.Goblin(elvl, r, r.l);
            }
            r.l.addEntity(e);
        }
    }
}
