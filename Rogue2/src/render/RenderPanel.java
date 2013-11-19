
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
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Torrj
 */
public class RenderPanel extends JPanel{
    private Level l = Rogue.getLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    Room[] room = l.getRooms();
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
    int k=0;
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        setReletiveTo(l.getPlayer());
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        room = l.getRooms();
        List<RogueEntity> current = l.getEntities();
        Image im = la.createImage("DungeonFloor116.png", "hi").getScaledInstance(32, 32, 0);
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    k++;
                    if(k==j){
                        System.out.println("yup thats right");
                    }
                    g2.drawImage(im, area1[0]*32+offx,area1[1]*32+offy, this);
                }
            }
        }
        for (int i=0;i<current.size();i++) {
            g2.drawImage(current.get(i).sp.i, current.get(i).x*32+offx, current.get(i).y*32+offy, this);
        }
    }
}
