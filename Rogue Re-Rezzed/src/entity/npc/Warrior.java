
package entity.npc;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.mob.RogueHostileEntity;
import entity.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import util.Astar;
import util.Direction;
import util.Node;
import util.Vector2i;

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
        lvl=l1.lvl;
        maxAtt=lvl*5;
        maxhealth=lvl;
        health=lvl;
        name="Warrior";
        cost=lvl*rand.nextInt(15)+1;
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
//        move(pathFind(follow));
        if (!(follow instanceof Player) && distTo(follow)<=1) {
            follow.damage(this);
        }else{
            for(RogueEntity re : l.getEntities()){
                if(re instanceof RogueHostileEntity && distTo(re)<=1){
                    re.damage(this);
                }
            }
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
    public Direction pathFind(RogueEntity e){
        Direction out;
        Astar a = new Astar();
        List<Node> path = a.findPath(new Vector2i(x,y), new Vector2i(e.x,e.y),l);
        if(path==null){
            System.out.println("Failure.");
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        out=Direction.UP;
                        break;
                    case 1:
                        out=Direction.DOWN;
                        break;
                    case 2:
                        out=Direction.LEFT;
                        break;
                    case 3:
                        out=Direction.RIGHT;
                        break;
                    default:
                        out=Direction.STOP;
                        break;
                }
            }else{
                out=Direction.STOP;
            }
        }else{
            System.out.println("Success! In "+path.size()+" easy steps!");
            try{
                out = pointTowards(path.get(1));
            }catch(Exception exc){
                out = pointTowards(path.get(0));
            }
        }
        return out;
    }
    public Direction pointTowards(Node e){
        Direction pdir=Direction.STOP;
        if(x<e.tile.getX())pdir = Direction.RIGHT;
        if(x>=e.tile.getX())pdir = Direction.LEFT;
        if(y<e.tile.getY())pdir = Direction.DOWN;
        if(y>=e.tile.getY())pdir =Direction.UP;
        return pdir;
    }
}
