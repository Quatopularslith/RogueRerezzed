
package entity.npc;

import dungeon.Level;
import dungeon.Room;
import entity.Direction;
import entity.item.Item;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import ui.GamePlay;

/**
 *
 * @author Creatorri
 */
public class Trader extends RogueNPC{
    private final String[] dialogue = {"Hello Adventurer!","Looking for something?","Need a hand?","Hey dawg whaddup?","Hello Stranger!","Mortuus Trabajos owning you? I got what you need!"};
    private final int[] prices;
    public BufferedImage img = new BufferedImage(256,256,BufferedImage.TYPE_4BYTE_ABGR);
    public Trader(Room r,Level l1) {
        super(r,l1);
        this.prices = new int[3];
        lvl=Level.numLevels;
        maxhealth=10000;
        health=maxhealth;
        inv=new Item[3];
        for(int i=0;i<inv.length;i++){
            inv[i]=new Item(rand.nextInt(Item.numid),this,rand.nextInt((lvl)*10),l1);
            prices[i]=inv[i].id+inv[i].lvl;
        }
        int say = rand.nextInt(dialogue.length);
        img=la.createBufferedImage("Dialogue"+Level.renderlevel+".png", 256, 256);
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256/2-width/2, 20);
        width = g.getFontMetrics().stringWidth("Here is what I have:");
        g.drawString("Here is what I have:", 256/2-width/2, 40);
        for(int i=0;i<inv.length;i++){
            width = g.getFontMetrics().stringWidth(inv[i].name+" for "+prices[i]+" gold");
            g.drawString(inv[i].name+" for "+prices[i]+" gold", 256/2-width/2, (i*20)+60);
        }
        g.dispose();
    }
    @Override
    public void action(){
        GamePlay.trade=true;
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
        if(distTo(l.getPlayer())>2){
            GamePlay.trade=false;
        }
        move(pdir);
    }
}
