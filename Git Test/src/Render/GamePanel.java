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
        Room[] room = curr.getRooms();
        for (Room r1 : room) {
            for(int i=0;i<r1.rl;i++){
                System.out.println(r1.render[i][i][0]);
                g2.drawImage(la.createImageIcon("DungeonFloor1", "What is this?").getImage(), r1.render[i][i][0],r1.render[i][i][1], this);
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