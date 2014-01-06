
package entity;

import core.Rogue;
import dungeon.Level;
import render.Sprite;
import render.SpriteSheet;

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
        this.sp = new Sprite(SpriteSheet.STAIRWAY);
    }
    @Override
    public void turn(){
        if(l.getPlayer().x==x && l.getPlayer().y==y && Rogue.numLevels==numlevel){
            Rogue.setLevel(l.getMode(),l.getType(),Level.renderlevel);
            Rogue.mm.gp.update();
        }
    }
    @Override
    public void death(){}
}
