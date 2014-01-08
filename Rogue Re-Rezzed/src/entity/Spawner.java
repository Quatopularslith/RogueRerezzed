
package entity;

import dungeon.Level;
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
     * @param l 
     */

    public static void spawner(int am,int lvl,Level l){
        RogueEntity e = new RogueEntity(l);
        Random rand = new Random();
        int type;
        int elvl;
        for(int i=0;i<am;i++){
            elvl = rand.nextInt(lvl+1)+1;
            type = rand.nextInt(4);
            if(type==0){
                e = new entity.mob.Snake(elvl,l);
            }else if(type==1){
                e = new entity.mob.Bandit(elvl,l);
            }else if(type==2){
                e = new entity.mob.Fish(elvl,l);
            }else if(type==3){
                e = new entity.mob.Goblin(elvl,l);
            }
            l.addEntity(e);
        }
    }
}
