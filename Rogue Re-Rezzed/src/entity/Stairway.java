
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
    boolean menu = false;
    public Stairway(Level l1) {
        super(l1);
        numlevel=Rogue.numLevels;
        room = rand.nextInt(l1.numRooms-2);
        this.spawn(l1.getRoom(room));
        this.sp = new Sprite("Stairway");
    }
    @Override
    public void turn(){
        boolean next;
        if(menu){
            next=Rogue.mm.mmp.numlevels==numlevel;
        }else{
            next=Rogue.numLevels==numlevel;
        }
        if(l.getPlayer().x==x && l.getPlayer().y==y && Rogue.numLevels==numlevel){
            Rogue.setLevel(l.getMode(),l.getType(),Level.renderlevel);
            if(menu){
                Rogue.mm.mmp.update();
            }else{
                Rogue.mm.gp.update();
            }
        }
    }
    @Override
    public void death(){}
    public void isMenu(boolean b){
        menu=b;
    }
}
