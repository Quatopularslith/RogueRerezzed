
package entity;

import dungeon.Room;
//import java.util.Random;

/**
 *
 * @author Torri
 */
public class Spawner {
    public static void spawner(int am,int lvl,Room r){
//        Random rand = new Random();
//        int type;
        for(int i=0;i<am;i++){
            RogueEntity e = new entity.mob.Snake(lvl,r,r.l);
            r.l.addEntity(e);
        }
        for(int i1=0;i1<am;i1++){
            RogueEntity e1 = new entity.mob.MortuusTrabajos(lvl,r,r.l);
            r.l.addEntity(e1); 
            }
    }
}
