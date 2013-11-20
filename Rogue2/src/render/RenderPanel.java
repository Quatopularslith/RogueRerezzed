
package render;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class RenderPanel extends JPanel{
    private Level l = Rogue.getLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    Room[] room = l.getRooms();
    private final Image img = la.createImage("DungeonFloor116.png","What",32,32);
    private List<RogueEntity> current = l.getEntities();
    public RenderPanel(){
        l=Rogue.getLevel();
    }
    /**
     * The core updater
     */
    public void update(){
        l=Rogue.getLevel();
        for(int i=0;i<l.getEntities().size();i++){
            l.getEntity(i).turn();
        }
        repaint();
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*32;
        offy=(getHeight()/2)-e.y*32;
    }
    int j=0;
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        long timer = System.currentTimeMillis();
        Graphics2D g2 = (Graphics2D) g;
        setReletiveTo(l.getPlayer());
        System.out.println(j++);
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        room = l.getRooms();
        current = l.getEntities();
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    g2.drawImage(img, area1[0]*32+offx,area1[1]*32+offy, this);
                }
            }
        }
        for (int i=0;i<current.size();i++) {
            g2.drawImage(current.get(i).sp.i, current.get(i).x*32+offx, current.get(i).y*32+offy, this);
        }
        timer=System.currentTimeMillis()-timer;
        System.out.println((timer/1000)+" Seconds");
    }
}
