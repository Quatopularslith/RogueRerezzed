
package ui;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import entity.player.Player;
import input.MButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import render.Sprite;

/**
 *
 * @author Torri
 */
public class GamePlay extends JPanel{
    private Level l = Rogue.getLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    Room[] room;
    public static Item pickup;
    public static Sprite fsp = new Sprite("DungeonFloor");
    public static Sprite dialogue = new Sprite("Dialogue",144);
    private List<RogueEntity> current;
    MButton amb = new MButton(getWidth()/2-16,getHeight()/2,100,20,"Pick Up",this);
    MButton dmb = new MButton(getWidth()/2-16,getHeight()/2+32,100,20,"Leave It",this);
    int moffx=0,moffy=0;
    public static Sprite floorimg =new Sprite("DungeonFloor",8);
    public static Sprite pimg =new Sprite("Player",8);
    public static Sprite stimg =new Sprite("Stairway",8);
    public GamePlay(){
        l=Rogue.getLevel();
        if(l!=null){
            room = l.getRooms();
            current = l.getEntities();
        }
        amb = new MButton(getWidth()/2-16,getHeight()/2,100,20,"Pick Up",this);
        dmb = new MButton(getWidth()/2-16,getHeight()/2+32,100,20,"Leave It",this);
    }
    /**
     * The core updater
     */
    public void update(){
        l=Rogue.getLevel();
        l.getStairWay().turn();
        room=l.getRooms();
        current=l.getEntities();
        pickup=null;
        l.getPlayer().turn();
        if(pickup instanceof Gold){
            Player.gold+=pickup.id;
            pickup.death();
            pickup=null;
        }
        for(int i=0;i<l.getEntities().size();i++){
            if(l.getEntities().get(i) != null){
                if(l.getEntities().get(i).health<=0){
                    l.getEntities().get(i).death();
                }else{
                    l.getEntities().get(i).turn();
                }
            }
        }
        repaint();
        if(Player.dead){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(false);
            this.setVisible(false);
            Rogue.mm.sm.setVisible(true);
            Level.numLevels=0;
        }
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*64;
        offy=(getHeight()/2)-e.y*64;
        moffx=getWidth()/8+getWidth()-getWidth()/4-10-e.x*8;
        moffy=(int) (10+(0.3515625*getHeight())/2-e.y*8);
    }
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        setReletiveTo(l.getPlayer());
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        room = l.getRooms();
        current = l.getEntities();
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    g2.drawImage(fsp.i, area1[0]*64+offx,area1[1]*64+offy, this);
                }
            }
        }
        g2.drawImage(l.getStairWay().sp.i, l.getStairWay().x*64+offx,l.getStairWay().y*64+offy, this);
        for(Item i:l.getItems()){
            g2.drawImage(i.sp.i, i.x*64+offx+i.ofx,i.y*64+offy+i.ofy, this);
        }
        for (int i=0;i<current.size();i++) {
            if(current.get(i)!=null){
                g2.setColor(Color.RED);
                g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-10, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
                g2.setColor(Color.WHITE);
                g2.drawString("Health:"+(int)current.get(i).health, current.get(i).x*64+offx+3, current.get(i).y*64+offy+4);
                g2.drawImage(current.get(i).sp.i, current.get(i).x*64+offx, current.get(i).y*64+offy, this);
            }
        }
        g2.setColor(Color.RED);
        g2.fillRect(l.getPlayer().x*64+offx+2, l.getPlayer().y*64+offy-10, (int) ((l.getPlayer().health/l.getPlayer().maxhealth)*61), 20);
        g2.setColor(Color.WHITE);
        g2.drawString("Health:"+(int)l.getPlayer().health, l.getPlayer().x*64+offx+3, l.getPlayer().y*64+offy+4);
        g2.drawImage(l.getPlayer().sp.i,l.getPlayer().x*64+offx,l.getPlayer().y*64+offy,this);
        if(pickup==null){
            pickup = new Item(0,Rogue.getLevel().getPlayer(),Rogue.getLevel());
        }
        if(pickup.name.equalsIgnoreCase("Empty")==false){
            amb.setParent(this);
            dmb.setParent(this);
            g2.setColor(Color.BLACK);
            g2.drawImage(dialogue.i, getWidth()/2-36, getHeight()/2-36, this);
            g2.drawString(pickup.name, getWidth()/2-32,getHeight()/2-10);
            amb.setPos(getWidth()/2-16,getHeight()/2,100,20);
            dmb.setPos(getWidth()/2-16,getHeight()/2+32,100,20);
            g2.drawImage(amb.img, amb.x,amb.y, this);
            g2.drawImage(dmb.img, dmb.x,dmb.y, this);
            amb.addListener(Rogue.mm.mbi);
            dmb.addListener(Rogue.mm.mbi);
        }else{
            amb.hide();
            dmb.hide();
        }
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, 10, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
//                    System.out.println("Room spot: "+area1[0]*8);
//                    System.out.println("Is less than: "+(getWidth()-(int) (0.25*getWidth())-10));
                    if(area1[0]*8<=getWidth()-(int) (0.25*getWidth())-10 /*&& area1[0]*8>=getWidth()-10 */&& area1[1]*8<=10 /*&& area1[0]*8>=10+(int) (0.3515625*getHeight())*/){
                        g2.drawImage(floorimg.i, area1[0]*8+moffx,area1[1]*8+moffy, this);
                    }
//                    g2.drawImage(floorimg.i, area1[0]*8+moffx,area1[1]*8+moffy, this);
                }
            }
        }
//        if(l.getStairWay().x*8>=getWidth()-(int) (0.25*getWidth())-10 && l.getStairWay().x*8<=getWidth()-10 && l.getStairWay().y*8>=10 && l.getStairWay().x*8<=10+(int) (0.3515625*getHeight())){
//            g2.drawImage(stimg.i, l.getStairWay().x*8+moffx,l.getStairWay().y*8+moffy, this);
//        }
        g2.drawImage(stimg.i, l.getStairWay().x*8+moffx,l.getStairWay().y*8+moffy, this);
        g2.drawImage(pimg.i,l.getPlayer().x*8+moffx,l.getPlayer().y*8+moffy,this);
    }
}
