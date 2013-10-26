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
        curr=Game.l;
        System.out.println("BEGINNING Render");
    }
    @Override
    public void paint(Graphics g){
//        System.out.println("RENDERING");
//        Graphics2D g2 = (Graphics2D) g;
//        while(Game.go==true){
//            if(Game.key.turn()==true){
//                for (RogueEntity entitie : curr.getEntities()) {
//                    g2.drawImage(entitie.sp.getImg(), entitie.x, entitie.x, this);
//                }
//            }
//        }
    }
}