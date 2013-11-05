package Render;

import Assets.LoadArt;
import Core.MainMenu;
import Entity.RogueEntity;
import Level.Level;
import Level.Room;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author 1003749
 */
public class GamePanel extends JPanel{
    List<RogueEntity> current;
    public void update(){
        repaint();
        for(int i=0;i<current.size();i++){
            current.get(i).turn();
        }
    }
    @Override
    public void paint(Graphics g){
        LoadArt la = new LoadArt();
        Level curr=MainMenu.l;
        System.out.println("RENDERING");
        Graphics2D g2 = (Graphics2D) g;
        current = curr.getEntities();
        System.out.println(current.size());
        Room[] r = curr.getRooms();
        for (Room r1 : r) {
            if (r1 == null) {
                continue;
            }
            for (int[] area : r1.area) {
                g2.drawImage(la.createImageIcon("DungeonFloor1.png", "floor").getImage(),area[0],area[1], this);
            }
        }
        for (RogueEntity entitie : current) {
            if(entitie==null){
                continue;
            }
            g2.drawImage(entitie.sp.getImg(), entitie.tx, entitie.ty, this);
        }
    }
}