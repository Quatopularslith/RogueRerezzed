
package ui;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import entity.npc.Trader;
import entity.npc.Warrior;
import input.MButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import render.Sprite;
import render.SpriteSheet;

/**
 *
 * @author Torri
 */
public class GamePlay extends JPanel{
    private Level l = Rogue.getCurrentLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    public static Item pickup;
    public static Sprite fsp = new Sprite(SpriteSheet.FLOOR);
    public static Sprite dialogue = new Sprite(SpriteSheet.DIALOGUE,144);
    private List<RogueEntity> current;
    private FontMetrics fm;
    MButton amb = new MButton(getWidth()/2-16,getHeight()/2,100,20,"Pick Up",this);
    MButton dmb = new MButton(getWidth()/2-16,getHeight()/2+32,100,20,"Leave It",this);
    int moffx=0,moffy=0;
    public static Sprite floorimg = new Sprite(SpriteSheet.FLOOR,8);
    public static Sprite pimg = new Sprite(SpriteSheet.PLAYER,8);
    public static Sprite stimg = new Sprite(SpriteSheet.STAIRWAY,8);
    public MButton[] equip = new MButton[10];
    public MButton[] drop = new MButton[10];
    public MButton quit = new MButton(650,460,100,30,"Quit",this);
    public MButton settings  = new MButton(650,460,100,30,"Settings",this);
    public MButton[] tradeMB = new MButton[3];
    public Trader currTrade;
    public Warrior w;
    public MButton hire = new MButton(650,460,100,30,"Hire",this);
    private Dimension d = getSize();
    private boolean check=false;
    public GamePlay(){
        repaint();
        l=Rogue.getCurrentLevel();
        if(l!=null){
            current = l.getEntities();
            currTrade = new Trader(l);
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
        for(int i=0;i<3;i++){
            tradeMB[i]=new MButton(5,5,50,10,"Trade",this);
            tradeMB[i].setData(Integer.toString(i));
            tradeMB[i].hide();
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        repaint();
        update();
        checkButtons();
        repaint();
        d=getSize();
    }
    /**
     * The core updater
     */
    public void update(){
        if(!d.equals(getSize())){
            d=getSize();
            repaint();
            checkButtons();
            repaint();
        }
        l=Rogue.getCurrentLevel();
        if(l==null) return;
        l.getStairWay().turn();
        current=l.getEntities();
        pickup=null;
        l.getPlayer().turn();
        if(pickup instanceof Gold){
            l.getPlayer().gold+=pickup.id;
            pickup.death();
            pickup=null;
        }
        if(pickup!=null){
            check=true;
        }else if(check){
            checkButtons();
            check=false;
        }
        Thread t1 = new Thread("Entities Updating"){
            @Override
            public void run(){
                for(int i=0;i<l.getEntities().size();i++){
                    if(l.getEntities().get(i) != null){
                        if(l.getEntities().get(i).health<=0){
                            l.getEntities().get(i).death();
                        }else{
                            l.getEntities().get(i).turn();
                        }
                    }
                }
            }
        };
        t1.start();
        if(l.getPlayer().dead){
            setVisible(false);
            Rogue.mm.sm.setVisible(true);
        }
        repaint();
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*64;
        offy=(getHeight()/2)-e.y*64;
        moffx=getWidth()/8+getWidth()-getWidth()/4-10-e.x*8;
        moffy=(int) (10+(0.3515625*getHeight())/2-e.y*8);
    }
    void checkButtons(){
        System.out.println("INVENTORY");
        Thread t2 =new Thread("Inventory Updating"){
            @Override
            public void run(){
                if(fm==null) return;
                for(int i=0;i<l.getPlayer().inv.length;i++){
                    int width = fm.stringWidth(l.getPlayer().inv[i].name);
                    if(!l.getPlayer().inv[i].name.equalsIgnoreCase("Empty")){
                        equip[i].setPos(width+getWidth()-(int) (0.25*getWidth())+5,(int) (((i+1)*(0.032*getHeight())-11)+(int) (0.3515625*getHeight())+74),(int) (0.05859375*getWidth()),12);
                        drop[i].setPos(width+getWidth()-(int) (0.25*getWidth())+(int) (0.05859375*getWidth()*1.2),(int) (((i+1)*(0.032*getHeight())-11)+(int) (0.3515625*getHeight())+74),(int) (0.05859375*getWidth()),12);
                    }else{
                        equip[i].hide();
                        drop[i].hide();
                    }
                }
                repaint();
            }
        };
        t2.start();
    }
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        setSize(Rogue.mm.getWidth(),Rogue.mm.getHeight());
        fm = g2.getFontMetrics();
        //Main Graphic
        if(l==null) return;
        setReletiveTo(l.getPlayer());
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        current = l.getEntities();
        //Floors
        for(int xx=0;xx<l.board.length;xx++){
            for(int yy=0;yy<l.board[xx].length;yy++){
                if(l.board[xx][yy] && !(xx*64+offx<=-64 || xx*64+offx>=getWidth() || yy*64+offy<=-64 || yy>=getHeight())) g2.drawImage(fsp.i, xx*64+offx,yy*64+offy, this);
            }
        }
        g2.drawImage(l.getStairWay().sp.i, l.getStairWay().x*64+offx,l.getStairWay().y*64+offy, this);
        //Items
        for(Item i:l.getItems()){
            g2.drawImage(i.sp.i, i.x*64+offx+i.ofx,i.y*64+offy+i.ofy, this);
        }
        //Entities
        for (int i=0;i<current.size();i++) {
            if(current.get(i)!=null){
                g2.setColor(Color.BLACK);
                g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-15, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
                if(current.get(i).x*64+offx<=-64 || current.get(i).x*64+offx>=getWidth() || current.get(i).y*64+offy<=-64 || current.get(i).y>=getHeight()) continue;
                g2.setColor(Color.RED);
                g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-15, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
                g2.setColor(Color.WHITE);
                g2.drawString(current.get(i).name, current.get(i).x*64+offx+3, current.get(i).y*64+offy);
                g2.drawImage(current.get(i).sp.i, current.get(i).x*64+offx, current.get(i).y*64+offy, this);
                g2.setColor(Color.GREEN);
                g2.drawString(Integer.toString(current.get(i).lvl), current.get(i).x*64+offx+59, current.get(i).y*64+offy+53);
                if(current.get(i) instanceof Trader){
                    Trader t = (Trader) current.get(i);
                    if(t.trade){
                        currTrade=t;
                    }else{
                        currTrade=null;
                    }
                }
                if(current.get(i) instanceof Warrior){
                    Warrior w1 = (Warrior) current.get(i);
                    if(w1.hireD){
                        w = w1;
                    }else{
                        w = null;
                    }
                }
            }
        }
        //Player
        g2.drawImage(l.getPlayer().sp.i,l.getPlayer().x*64+offx,l.getPlayer().y*64+offy,this);
        g2.setColor(Color.GREEN);
        g2.drawString(Integer.toString(l.getPlayer().lvl), l.getPlayer().x*64+offx+59, l.getPlayer().y*64+offy+53);
        //Pickup
        if(pickup==null){
            pickup = new Item(0,l.getPlayer(),0,l);
        }
        if(!pickup.name.equalsIgnoreCase("Empty")){
            amb.setParent(this);
            dmb.setParent(this);
            g2.setColor(Color.BLACK);
            g2.drawImage(dialogue.i, getWidth()/2-96, getHeight()/2-96, this);
            g2.drawString(pickup.name, getWidth()/2-48,getHeight()/2-10);
            amb.setPos(getWidth()/2-24,getHeight()/2,100,20);
            dmb.setPos(getWidth()/2-24,getHeight()/2+30,100,20);
            g2.drawImage(amb.img, amb.x,amb.y, this);
            g2.drawImage(dmb.img, dmb.x,dmb.y, this);
        }else{
            amb.hide();
            dmb.hide();
        }
        //Trade
        if(currTrade!=null){
            if(currTrade.trade){
                g2.drawImage(currTrade.img, getWidth()/2-96, getHeight()/2-96, this);
                for(int i=0;i<tradeMB.length;i++){
                    tradeMB[i].setPos(currTrade.buttons[i][0]+getWidth()/2-96, currTrade.buttons[i][1]+getHeight()/2-96, 50, 20);
                    g2.drawImage(tradeMB[i].img, tradeMB[i].x,tradeMB[i].y, this);
                }
            }
        }
        //Hire
        if(w!=null){
            if(w.hireD){
                g2.drawImage(w.img, getWidth()/2-96, getHeight()/2-96, this);
                hire.setPos(w.buttons[0]+getWidth()/2-96, w.buttons[1]+getHeight()/2-96, 50, 20);
                g2.drawImage(hire.img, hire.x, hire.y, this);
            }
        }
        //Map
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, 10, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        for(int xx=0;xx<l.board.length;xx++){
            for(int yy=0;yy<l.board[xx].length;yy++){
                if(!(xx*8+moffx>getWidth()-(getWidth()/4)-8 && xx*8+moffx<getWidth()-18)) continue;
                if(!(yy*8+moffy>10 && yy*8+moffy<10+(getHeight()*0.3515625-10))) continue;
                if(l.board[xx][yy]) g2.drawImage(floorimg.i, xx*8+moffx,yy*8+moffy, this);
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
        for(int i=0;i<equip.length;i++){
            g2.drawString(l.getPlayer().inv[i].name, getWidth()-(int) (0.25*getWidth()), (int) (((i+1)*(0.032*getHeight()))+(int) (0.3515625*getHeight())+74));
            if(equip[i].visible){
                g2.drawImage(equip[i].img, equip[i].x,equip[i].y, this);
            }
            if(drop[i].visible){
                g2.drawImage(drop[i].img, drop[i].x,drop[i].y, this);
            }
        }
        //Stats
        g2.setColor(Color.BLACK);
        g2.fillRect(5, 5, 140, 170);
        g2.setColor(Color.RED);
        g2.fillRect(10, 25, (int) ((l.getPlayer().health/l.getPlayer().maxhealth)*100), 20);
        g2.setColor(Color.BLUE);
        g2.fillRect(10, 45, (int) ((l.getPlayer().mana/l.getPlayer().maxMana)*100)-9, 20);
        g2.setColor(Color.GREEN);
        g2.fillRect(10, 65, (int) (100*l.getPlayer().xp/(10*l.getPlayer().lvl)), 20);
        g2.setColor(Color.WHITE);
        g2.drawString("You are in dungeon: "+l.lvl, 10, 20);
        g2.drawString("Health: "+(int) l.getPlayer().health,10,40);
        g2.drawString("Mana: "+(int) l.getPlayer().mana, 10, 60);
        g2.drawString("You are Level: "+l.getPlayer().lvl, 10, 80);
        g2.drawString("Max Attack: "+l.getPlayer().maxAtt, 10, 100);
        g2.drawString("Defence: "+l.getPlayer().maxDefence, 10, 120);
        g2.drawString("Kills: "+l.getPlayer().kills+" Enemies", 10, 140);
        g2.drawString("Gold: "+l.getPlayer().gold, 10, 160);
        //Buttons
        quit.setPos(getWidth()-(int) (0.1875*getWidth()), getHeight()-(int) (0.05859375*getHeight())-50, (int) (0.078125*getWidth()), (int) (0.048828125*getHeight()));
        settings.setPos(getWidth()-(int) (0.09375*getWidth()), getHeight()-(int) (0.05859375*getHeight())-50, (int) (0.078125*getWidth()), (int) (0.048828125*getHeight()));
        g2.drawImage(quit.img, quit.x, quit.y, this);
        g2.drawImage(settings.img, settings.x, settings.y, this);
        //Pause Screen
        if(Rogue.mm.ki.pause){
            g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
            fm = g2.getFontMetrics();
            g2.setColor(Color.RED);
            g2.drawString("PAUSED", getWidth()/2-fm.stringWidth("PAUSED")/2, getHeight()/2);
        }
    }
}
