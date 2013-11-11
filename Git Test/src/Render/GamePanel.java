package Render;

import Assets.LoadArt;
import Core.MainMenu;
import Entity.RogueEntity;
import Level.Level;
import Level.Room;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author 1003749
 */
public class GamePanel extends JPanel{
    private List<RogueEntity> current = MainMenu.l.getEntities();
    private int offx=0,offy=0;
    public void update(){
        for(int i=0;i<current.size();i++){
            current.get(i).turn();
            if(current.get(i).death){
                current.remove(i);
            }
        }
        repaint();
    }
    public void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2+e.tx);
        offy=(getHeight()/2+e.ty);
    }
    @Override
    public void paint(Graphics g){
        BufferedImage bi;
        setReletiveTo(MainMenu.l.getHostilePlayer());
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        LoadArt la = new LoadArt();
        Level curr=MainMenu.l;
        Graphics2D g2 = (Graphics2D) g;
        current = curr.getEntities();
        Room[] room = curr.getRooms();
        for (Room r1 : room) {
            for (int[][] area : r1.area) {
                for (int[] area1 : area) {
                    g2.drawImage(la.createImageIcon("DungeonFloor1.png", "What is this?").getImage(), area1[0]*32-offx,area1[1]*32-offy, this);
                }
            }
        }
        for (RogueEntity entitie : current) {
//            bi=entitie.sp;
            g2.drawImage(entitie.sp.getImg(), entitie.tx*32-offx, entitie.ty*32-offy, this);
        }
    }
}