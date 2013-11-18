
package render;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class RenderPanel extends JPanel{
    private Level l;
    private int offx,offy;
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
        offx=(getWidth()/2)+e.rx;
        offy=(getHeight()/2)+e.ry;
    }
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        setReletiveTo(l.getPlayer());
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        LoadArt la = new LoadArt();
        Graphics2D g2 = (Graphics2D) g;
        List<RogueEntity> current = l.getEntities();
        Room[] room = l.getRooms();
        int err=0;
        for (Room r1 : room) {
            if(r1==null){
                System.out.println(err++);
                continue;
            }
            for (int[][] area : r1.area) {
                for (int[] area1 : area) {
                    g2.drawImage(la.createImage("DungeonFloor116.png", "What is this?"), area1[0]*16+offx,area1[1]*16+offy, this);
                }
            }
        }
        for (int i=0;i<current.size()-1;i++) {
            g2.drawImage(current.get(i).sp.i, current.get(i).x*16+offx, current.get(i).y*16+offy, this);
        }
    }
}
