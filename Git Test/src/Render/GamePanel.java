package Render;

import Core.Game;
import Entity.RogueEntity;
import Level.Level;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @author 1003749
 */
public class GamePanel extends JPanel{
    Level curr;
    public GamePanel(){
        curr=Game.getCurrentLevel();
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for (RogueEntity ent : curr.getEntities()) {
            g2.drawImage(ent.sp.getImg(), ent.x, ent.y, null);
        }
    }
}