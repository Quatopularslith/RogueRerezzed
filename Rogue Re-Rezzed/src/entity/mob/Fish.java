
package entity.mob;

import dungeon.Level;
import dungeon.Room;

/**
 *
 * @author Mnenmenth
 */
public class Fish extends RogueHostileEntity{
    public Fish(int lvl,Room r,Level l1){
        super(lvl,r,l1,"Fish",3,5,3,0);
    }
    @Override
    public void turn(){
        this.move(rand.nextInt(360));
    }
}
