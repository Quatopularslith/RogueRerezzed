
package ui;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import entity.npc.Trader;
import entity.player.Player;
import input.MButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
    public static Sprite floorimg = new Sprite("DungeonFloor",8);
    public static Sprite pimg = new Sprite("Player",8);
    public static Sprite stimg = new Sprite("Stairway",8);
    public MButton[] equip = new MButton[10];
    public MButton[] drop = new MButton[10];
    public MButton quit = new MButton(650,460,100,30,"Quit",this);
    public MButton settings  = new MButton(650,460,100,30,"Settings",this);
    public static boolean trade;
    private Image tradeimg;
    public GamePlay(){
        l=Rogue.getLevel();
        if(l!=null){
            room = l.getRooms();
            current = l.getEntities();
        }
        amb = new MButton(getWidth()/2-16,getHeight()/2,100,20,"Pick Up",this);
        dmb = new MButton(getWidth()/2-16,getHeight()/2+32,100,20,"Leave It",this);
        amb.hide();
        dmb.hide();
        for(int i=0;i<10;i++){
            equip[i]=new MButton(750-(int) (0.25*750)+30,(int) (((i)*(0.032*500))+(int) (0.3515625*500)+74),50,10,"Equip",this);
            equip[i].setData(Integer.toString(i));
            drop[i]=new MButton(750-(int) (0.25*750)+60,(int) (((i)*(0.032*500))+(int) (0.3515625*500)+74),50,10,"Drop",this);
            drop[i].setData(Integer.toString(i));
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
    /**
     * The core updater
     */
    public void update(){
        l=Rogue.getLevel();
        if(l==null) return;
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
        //Main Graphic
        if(l==null) return;
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
                g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-15, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
                g2.setColor(Color.WHITE);
                g2.drawString("Health:"+(int)current.get(i).health, current.get(i).x*64+offx+3, current.get(i).y*64+offy);
                g2.drawImage(current.get(i).sp.i, current.get(i).x*64+offx, current.get(i).y*64+offy, this);
                g2.setColor(Color.ORANGE);
                g2.drawString(Integer.toString(current.get(i).lvl), current.get(i).x*64+offx+59, current.get(i).y*64+offy+53);
                if(current.get(i) instanceof Trader){
                    Trader t = (Trader) current.get(i);
                    tradeimg=t.img;
                }
            }
        }
        g2.setColor(Color.RED);
        g2.fillRect(l.getPlayer().x*64+offx+2, l.getPlayer().y*64+offy-15, (int) ((l.getPlayer().health/l.getPlayer().maxhealth)*61), 20);
        g2.setColor(Color.WHITE);
        g2.drawString("Health:"+(int)l.getPlayer().health, l.getPlayer().x*64+offx+3, l.getPlayer().y*64+offy);
        g2.drawImage(l.getPlayer().sp.i,l.getPlayer().x*64+offx,l.getPlayer().y*64+offy,this);
        g2.setColor(Color.ORANGE);
        g2.drawString(Integer.toString(Player.xplevels), l.getPlayer().x*64+offx+59, l.getPlayer().y*64+offy+53);
        //Pickup
        if(pickup==null){
            pickup = new Item(0,Rogue.getLevel().getPlayer(),0,Rogue.getLevel());
        }
        if(pickup.name.equalsIgnoreCase("Empty")==false){
            amb.setParent(this);
            dmb.setParent(this);
            g2.setColor(Color.BLACK);
            g2.drawImage(dialogue.i, getWidth()/2-96, getHeight()/2-96, this);
            g2.drawString(pickup.name, getWidth()/2-48,getHeight()/2-10);
            amb.setPos(getWidth()/2-24,getHeight()/2,100,20);
            dmb.setPos(getWidth()/2-24,getHeight()/2+30,100,20);
            g2.drawImage(amb.img, amb.x,amb.y, this);
            g2.drawImage(dmb.img, dmb.x,dmb.y, this);
            amb.addListener(Rogue.mm.mbi);
            dmb.addListener(Rogue.mm.mbi);
        }else{
            amb.hide();
            dmb.hide();
        }
        //Trade
        if(trade){
            g2.drawImage(tradeimg, getWidth()/2-96, getHeight()/2-96, this);
        }
        //Map
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, 10, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    if(area1[0]*8+moffx>getWidth()-(getWidth()/4)-8 && area1[0]*8+moffx<getWidth()-18){
                        if((area1[1]*8+moffy)>10 && area1[1]*8+moffy<10+(getHeight()*0.3515625-10)){
                            g2.drawImage(floorimg.i, area1[0]*8+moffx,area1[1]*8+moffy, this);
                        }
                    }
                }
            }
        }
        if(l.getStairWay().x*8+moffx>getWidth()-(getWidth()/4)-8 && l.getStairWay().x*8+moffx<getWidth()-18){
            if((l.getStairWay().y*8+moffy)>10 && l.getStairWay().y*8+moffy<10+(getHeight()*0.3515625)){
                g2.drawImage(stimg.i, l.getStairWay().x*8+moffx,l.getStairWay().y*8+moffy, this);
            }
        }
        g2.drawImage(pimg.i,l.getPlayer().x*8+moffx,l.getPlayer().y*8+moffy,this);
        //Inventory
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, (int) (0.3515625*getHeight())+64, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        g2.setColor(Color.WHITE);
        for(int i=0;i<Player.pinv.length;i++){
            g2.drawString(Player.pinv[i].name, getWidth()-(int) (0.25*getWidth()), (int) (((i+1)*(0.032*getHeight()))+(int) (0.3515625*getHeight())+74));
            FontMetrics fm = g2.getFontMetrics(g2.getFont());
            int width = fm.stringWidth(Player.pinv[i].name);
            if(Player.pinv[i].id>0){
                equip[i].addListener(Rogue.mm.mbi);
                drop[i].addListener(Rogue.mm.mbi);
                equip[i].setPos(width+getWidth()-(int) (0.25*getWidth())+5,(int) (((i+1)*(0.032*getHeight())-11)+(int) (0.3515625*getHeight())+74),(int) (0.05859375*getWidth()),12);
                drop[i].setPos(width+getWidth()-(int) (0.25*getWidth())+(int) (0.05859375*getWidth()*1.2),(int) (((i+1)*(0.032*getHeight())-11)+(int) (0.3515625*getHeight())+74),(int) (0.05859375*getWidth()),12);
                g2.drawImage(drop[i].img, drop[i].x,drop[i].y, this);
                g2.drawImage(equip[i].img, equip[i].x,equip[i].y, this);
            }else{
                equip[i].hide();
                drop[i].hide();
            }
        }
        //Stats
        g2.setColor(Color.BLACK);
        g2.fillRect(5, 5, 140, 170);
        g2.setColor(Color.RED);
        g2.fillRect(10, 25, (int) ((Rogue.getLevel().getPlayer().health/l.getPlayer().maxhealth)*100), 20);
        g2.setColor(Color.BLUE);
        g2.fillRect(10, 45, (int) ((l.getPlayer().mana/l.getPlayer().maxMana)*100), 20);
        g2.setColor(Color.GREEN);
        g2.fillRect(10, 65, (int) (100*Player.xp/(10*Player.xplevels)), 20);
        g2.setColor(Color.WHITE);
        g2.drawString("You are in dungeon: "+Level.numLevels, 10, 20);
        g2.drawString("Health: "+(int) Rogue.getLevel().getPlayer().health,10,40);
        g2.drawString("Mana: "+l.getPlayer().mana, 10, 60);
        g2.drawString("You are Level: "+Player.xplevels, 10, 80);
        g2.drawString("Max Attack: "+l.getPlayer().maxAtt, 10, 100);
        g2.drawString("Defence: "+l.getPlayer().maxDefence, 10, 120);
        g2.drawString("Kills: "+Player.kills+" Enemies", 10, 140);
        g2.drawString("Gold: "+Player.gold, 10, 160);
        //Buttons
        quit.setPos(getWidth()-(int) (0.1875*getWidth()), getHeight()-(int) (0.05859375*getHeight()), (int) (0.078125*getWidth()), (int) (0.048828125*getHeight()));
        settings.setPos(getWidth()-(int) (0.09375*getWidth()), getHeight()-(int) (0.05859375*getHeight()), (int) (0.078125*getWidth()), (int) (0.048828125*getHeight()));
        quit.addListener(Rogue.mm.mbi);
        settings.addListener(Rogue.mm.mbi);
        g2.drawImage(quit.img, quit.x, quit.y, this);
        g2.drawImage(settings.img, settings.x, settings.y, this);
    }
}
