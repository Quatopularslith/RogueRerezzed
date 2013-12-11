
package entity.npc;

import dungeon.Level;
import dungeon.Room;
import entity.Direction;
import entity.item.Item;
import java.awt.Image;
import render.Sprite;

/**
 *
 * @author Creatorri
 */
public class Trader extends RogueNPC{
    private final String[] dialogue = {"Hello Adventurer!","Looking for something?","Need a hand?","Hey dawg whaddup?","Hello Stranger!","Mortuus Trabajos owning you? I got what you need!"};
    private int[] prices;
    public Image img;
    public boolean trade;
    public Trader(Room r,Level l1) {
        super(l1);
        this.prices = new int[3];
        spawn(r);
        lvl=Level.numLevels;
        maxhealth=10000;
        health=maxhealth;
        inv=new Item[3];
        for(int i=0;i<inv.length;i++){
            inv[i]=new Item(rand.nextInt(Item.numid),this,rand.nextInt(lvl),l1);
            prices[i]=inv[i].id+inv[i].lvl;
        }
        int say = rand.nextInt(dialogue.length);
        img=(new Sprite("Dialogue",256).i);
        int width = img.getGraphics().getFontMetrics().stringWidth(dialogue[say]);
        img.getGraphics().drawString(dialogue[say], 256/2-width/2, 20);
        width = img.getGraphics().getFontMetrics().stringWidth("Here is what I have:");
        img.getGraphics().drawString("Here is what I have:", 256/2-width/2, 40);
        for(int i=0;i<inv.length;i++){
            width = img.getGraphics().getFontMetrics().stringWidth(inv[i]+" for "+prices[i]+" gold");
            img.getGraphics().drawString(inv[i]+" for "+prices[i]+" gold", 256/2-width/2, (i*20)+60);
        }
        trade=false;
    }
    @Override
    public void action(){
        trade=true;
    }
    @Override
    public void turn(){
        trade=false;
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
