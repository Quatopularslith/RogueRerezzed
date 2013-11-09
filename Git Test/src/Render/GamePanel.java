package Render;

import Assets.LoadArt;
import Core.MainMenu;
import Entity.RogueEntity;
import Level.Level;
import Level.Room;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author 1003749
 */
public class GamePanel extends JPanel{
    List<RogueEntity> current = MainMenu.l.getEntities();
    public void update(){
        for(int i=0;i<current.size();i++){
            current.get(i).turn();
        }
        System.out.println("GO");
        repaint();
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        LoadArt la = new LoadArt();
        Level curr=MainMenu.l;
        System.out.println("RENDERING");
        Graphics2D g2 = (Graphics2D) g;
        current = curr.getEntities();
        System.out.println(current.size());
        Room[] room = curr.getRooms();
        for (Room r1 : room) {
            for(int i=0;i<r1.rl;i++){
                g2.drawImage(la.createImageIcon("DungeonFloor1.png", "What is this?").getImage(), r1.render[i][0],r1.render[i][1], this);
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