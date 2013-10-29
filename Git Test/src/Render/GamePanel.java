package Render;

import Core.MainMenu;
import Entity.RogueEntity;
import Level.Level;
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
        Level curr=MainMenu.l;
        System.out.println("RENDERING");
        Graphics2D g2 = (Graphics2D) g;
        current = curr.getEntities();
        for (RogueEntity entitie : current) {
            if(entitie==null){
                continue;
            }
            g2.drawImage(entitie.sp.getImg(), entitie.x, entitie.x, this);
        }
    }
}