
package render;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.player.Player;
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
//    public RenderPanel(){
//        l=Rogue.getLevel();
//    }
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
        Image im = la.createImage("DungeonFloor116.png", "hi").getScaledInstance(32, 32, 0);
        for (Room r1 : room) {
            for (int[][] area : r1.area) {
                for (int[] area1 : area) {
                    g2.drawImage(im, area1[0]*32+offx,area1[1]*32+offy, this);
                }
            }
        }
        for (int i=0;i<current.size()-1;i++) {
            g2.drawImage(current.get(i).sp.i, current.get(i).x*32+offx, current.get(i).y*32+offy, this);
        }
        for(RogueEntity re:current){
            if(re instanceof Player){
                g2.drawImage(re.sp.i, re.x*32+offx, re.y*32+offy, this);
                setReletiveTo(re);
            }
        }
    }
}
