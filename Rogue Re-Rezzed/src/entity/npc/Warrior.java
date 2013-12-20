
package entity.npc;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.mob.RogueHostileEntity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Direction;

/**
 *
 * @author Torri
 */
public class Warrior extends RogueNPC{
    RogueEntity follow;
    private final String[] dialogue = {"Need a hand?","I'll help you. For a price.","DEATH, DEATH EVERYWHERE"};
    public BufferedImage img = new BufferedImage(256,256,BufferedImage.TYPE_4BYTE_ABGR);
    public int cost;
    public int buttons[] = new int[2];
    public boolean hireD = false;
    public boolean hire = false;
    public Warrior(Room r,Level l1) {
        super(r,l1);
        maxAtt=lvl*5;
        maxhealth=lvl;
        health=lvl;
        name="Warrior";
        cost=lvl*15;
        refreshHire();
    }
    @Override
    public void turn(){
        for(RogueEntity re : l.getEntities()){
            if(re==null || re.equals(this) || !(re instanceof RogueHostileEntity)){
                continue;
            }
            if(follow==null){
                follow=re;
            }
            if(follow.health<=0){
                follow=re;
            }
            if(distTo(re)<distTo(follow)){
                follow=re;
            }
        }
        if(distTo(l.getPlayer())>1){
            hireD=false;
        }
        if(hire) follow=l.getPlayer();
        Direction pdir=Direction.STOP;
        if(x<follow.x)pdir = Direction.RIGHT;
        if(x>follow.x)pdir = Direction.LEFT;
        if(y<follow.y)pdir = Direction.DOWN;
        if(y>follow.y)pdir =Direction.UP;
        move(pdir);
        if(distTo(follow)<=1){
            follow.damage(this);
        }
    }
    @Override
    public void action(){
        hireD=true;
    }
    public void refreshHire(){
        if(hire) cost=0;
        int say = rand.nextInt(dialogue.length);
        img=la.createBufferedImage("Dialogue"+Level.renderlevel+".png", 256, 256);
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256/2-width/2, 40);
        g.drawString("Hire me for: "+cost+" Gold", 0, 60);
        buttons[0]=2;
        buttons[1]=100;
        g.dispose();
    }
}
