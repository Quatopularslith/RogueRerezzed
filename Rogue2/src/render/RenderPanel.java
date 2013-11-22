
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
    boolean death=false;
    private Level l = Rogue.getLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    Room[] room = l.getRooms();
    private final Image img = la.createImage("DungeonFloor116.png","What",64,64);
    private final Image deimg = la.createImage("RogueLogo.png", "yuo", 64, 200);
    private List<RogueEntity> current = l.getEntities();
    public RenderPanel(){
        l=Rogue.getLevel();
    }
    /**
     * The core updater
     */
    public void update(){
        l.getStairWay().turn();
        l=Rogue.getLevel();
        for(int i=0;i<l.getEntities().size();i++){
            if(current.get(i) instanceof Player && current.get(i).health<=0){
                death=true;
            }
            if(current.get(i).health<=0){
                current.get(i).death();
            }else if(current.get(i).health<current.get(i).maxhealth){
                current.get(i).turn();
            }else{
                current.get(i).turn();
            }
        }
        Rogue.mm.d.invp.update();
        Rogue.mm.d.s.update();
        Rogue.mm.d.mapp.update();
        repaint();
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*64;
        offy=(getHeight()/2)-e.y*64;
    }
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
        current = l.getEntities();
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    g2.drawImage(img, area1[0]*64+offx,area1[1]*64+offy, this);
                }
            }
        }
        for (int i=0;i<current.size();i++) {
            g2.setColor(Color.RED);
            g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-10, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
            g2.setColor(Color.BLACK);
            g2.drawString("Health:"+(int)current.get(i).health, current.get(i).x*64+offx+3, current.get(i).y*64+offy+4);
            g2.drawImage(current.get(i).sp.i, current.get(i).x*64+offx, current.get(i).y*64+offy, this);
        }
        if(death==true){
            g2.drawImage(deimg, getWidth()/2-50, getHeight()/2-64, this);
        }
        g2.drawImage(l.getStairWay().sp.i, l.getStairWay().x*64+offx,l.getStairWay().y*64+offy, this);
    }
}
