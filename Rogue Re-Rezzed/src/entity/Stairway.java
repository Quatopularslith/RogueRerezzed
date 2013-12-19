
package entity;

import core.Rogue;
import dungeon.Level;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class Stairway extends RogueEntity{
    public int room  = rand.nextInt(l.numRooms);
    private int numlevel = 0;
    public Stairway(Level l1) {
        super(l1);
        numlevel=Rogue.numLevels;
        room = rand.nextInt(l1.numRooms-2);
        this.spawn(l1.getRoom(room));
        this.sp = new Sprite("Stairway");
    }
    @Override
    public void turn(){
        if(Rogue.getCurrentLevel().getPlayer().x==x && Rogue.getCurrentLevel().getPlayer().y==y && Rogue.numLevels==numlevel){
            Rogue.setLevel(Rogue.getCurrentLevel().getMode(),Rogue.getCurrentLevel().getType(),Level.renderlevel);
            Rogue.mm.gp.update();
        }
    }
    @Override
    public void death(){}
}
