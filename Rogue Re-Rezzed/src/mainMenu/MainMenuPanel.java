
package mainMenu;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import input.MButton;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static ui.GamePlay.floorimg;
import static ui.GamePlay.fsp;
import static ui.GamePlay.pickup;
import static ui.GamePlay.pimg;
import static ui.GamePlay.stimg;
import util.Astar;
import util.Direction;
import util.Node;
import util.Vector2i;

/**
 * 
 * @author Mnenmenth
 */
public class MainMenuPanel extends javax.swing.JPanel{
    double[] bypos = {0.3,0.42,0.54};
    double[] size = {0.26666666666666667,0.1};
    LoadArt la = new LoadArt();
    public int numlevels=1;
    int offx,offy,moffx,moffy;
    ArrayList<Level> l = new ArrayList<>();
    int sx,sy;
//    Image img = la.createImage("MainMenu.png", "MMP", 750, 500);
    MButton newGame = new MButton(getWidth()/2-100,150,200,50,"New Game",this);
    MButton options = new MButton(getWidth()/2-100,210,200,50,"Options",this);
    MButton quit = new MButton(getWidth()/2-100,270,200,50,"Quit",this);
    public MainMenuPanel(){
        this.repaint();
        l.add(new Level(numlevels,LevelMode.STORY,LevelType.TURN,0));
    }
    public void go(){
        l.get(l.size()-1).getStairWay().isMenu(true);
        while(isVisible()){
            l.get(l.size()-1).getPlayer().move(pathFind(l.get(l.size()-1).getStairWay()));
            update();
        }
    }
    public void update(){
        if(l==null) return;
        l.get(l.size()-1).getStairWay().turn();
        pickup=null;
        l.get(l.size()-1).getPlayer().turn();
        if(pickup instanceof Gold){
            l.get(l.size()-1).getPlayer().gold+=pickup.id;
            pickup.death();
            pickup=null;
        }
        for(int i=0;i<l.get(l.size()-1).getEntities().size();i++){
            if(l.get(l.size()-1).getEntities().get(i) != null){
                if(l.get(l.size()-1).getEntities().get(i).health<=0){
                    l.get(l.size()-1).getEntities().get(i).death();
                }else{
                    l.get(l.size()-1).getEntities().get(i).turn();
                }
            }
        }
        repaint();
        if(l.get(l.size()-1).getPlayer().dead){
            numlevels=1;
            l.add(new Level(numlevels,LevelMode.STORY,LevelType.TURN,0));
            update();
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(sy!=Rogue.mm.getHeight() && sx!=Rogue.mm.getWidth()){
            newGame.setParent(this);
            options.setParent(this);
            quit.setParent(this);
            sy=Rogue.mm.getHeight();
            sx=Rogue.mm.getWidth();
//            img = la.createImage("MainMenu.png","MMP",Rogue.mm.getWidth(), Rogue.mm.getHeight());
            this.setSize(Rogue.mm.getWidth(), Rogue.mm.getHeight());
            newGame.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[0]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            options.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[1]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            quit.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[2]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            newGame.addListener(Rogue.mm.mbi,this);
            options.addListener(Rogue.mm.mbi,this);
            quit.addListener(Rogue.mm.mbi,this);
        }
//        g2.drawImage(img, 0,0, this);
        FontMetrics fm = g2.getFontMetrics();
        //Main Graphic
        if(l==null) return;
        setReletiveTo(l.get(l.size()-1).getPlayer());
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        //Floors
        for (Room r1 : l.get(l.size()-1).getRooms()) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    if(area1[0]*64+offx<=-64 || area1[0]*64+offx>=getWidth() || area1[1]*64+offy<=-64 || area1[1]>=getHeight()) continue;
                    g2.drawImage(fsp.i, area1[0]*64+offx,area1[1]*64+offy, this);
                }
            }
        }
        g2.drawImage(l.get(l.size()-1).getStairWay().sp.i, l.get(l.size()-1).getStairWay().x*64+offx,l.get(l.size()-1).getStairWay().y*64+offy, this);
        //Items
        for(Item i:l.get(l.size()-1).getItems()){
            g2.drawImage(i.sp.i, i.x*64+offx+i.ofx,i.y*64+offy+i.ofy, this);
        }
        //Entities
        for (int i=0;i<l.get(l.size()-1).getEntities().size();i++) {
            if(l.get(l.size()-1).getEntities().get(i)!=null){
                g2.setColor(Color.BLACK);
                g2.fillRect(l.get(l.size()-1).getEntities().get(i).x*64+offx+2, l.get(l.size()-1).getEntities().get(i).y*64+offy-15, (int) ((l.get(l.size()-1).getEntities().get(i).health/l.get(l.size()-1).getEntities().get(i).maxhealth)*61), 20);
                if(l.get(l.size()-1).getEntities().get(i).x*64+offx<=-64 || l.get(l.size()-1).getEntities().get(i).x*64+offx>=getWidth() || l.get(l.size()-1).getEntities().get(i).y*64+offy<=-64 || l.get(l.size()-1).getEntities().get(i).y>=getHeight()) continue;
                g2.setColor(Color.RED);
                g2.fillRect(l.get(l.size()-1).getEntities().get(i).x*64+offx+2, l.get(l.size()-1).getEntities().get(i).y*64+offy-15, (int) ((l.get(l.size()-1).getEntities().get(i).health/l.get(l.size()-1).getEntities().get(i).maxhealth)*61), 20);
                g2.setColor(Color.WHITE);
                g2.drawString(l.get(l.size()-1).getEntities().get(i).name+" ("+(int) l.get(l.size()-1).getEntities().get(i).health+"/"+(int) l.get(l.size()-1).getEntities().get(i).maxhealth+")", l.get(l.size()-1).getEntities().get(i).x*64+offx+3, l.get(l.size()-1).getEntities().get(i).y*64+offy);
                g2.drawImage(l.get(l.size()-1).getEntities().get(i).sp.i, l.get(l.size()-1).getEntities().get(i).x*64+offx, l.get(l.size()-1).getEntities().get(i).y*64+offy, this);
                g2.setColor(Color.GREEN);
                g2.drawString(Integer.toString(l.get(l.size()-1).getEntities().get(i).lvl), l.get(l.size()-1).getEntities().get(i).x*64+offx+59, l.get(l.size()-1).getEntities().get(i).y*64+offy+53);
            }
        }
        //Player
        g2.drawImage(l.get(l.size()-1).getPlayer().sp.i,l.get(l.size()-1).getPlayer().x*64+offx,l.get(l.size()-1).getPlayer().y*64+offy,this);
        g2.setColor(Color.GREEN);
        g2.drawString(Integer.toString(l.get(l.size()-1).getPlayer().lvl), l.get(l.size()-1).getPlayer().x*64+offx+59, l.get(l.size()-1).getPlayer().y*64+offy+53);
        //Map
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, 10, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        for (Room r1 : l.get(l.size()-1).getRooms()) {
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
        if(l.get(l.size()-1).getStairWay().x*8+moffx>getWidth()-(getWidth()/4)-8 && l.get(l.size()-1).getStairWay().x*8+moffx<getWidth()-18){
            if((l.get(l.size()-1).getStairWay().y*8+moffy)>10 && l.get(l.size()-1).getStairWay().y*8+moffy<10+(getHeight()*0.3515625)){
                g2.drawImage(stimg.i, l.get(l.size()-1).getStairWay().x*8+moffx,l.get(l.size()-1).getStairWay().y*8+moffy, this);
            }
        }
        g2.drawImage(pimg.i,l.get(l.size()-1).getPlayer().x*8+moffx,l.get(l.size()-1).getPlayer().y*8+moffy,this);
        //Inventory
        g2.setColor(Color.BLACK);
        g2.fillRect(getWidth()-(int) (0.25*getWidth())-10, (int) (0.3515625*getHeight())+64, (int) (0.25*getWidth()), (int) (0.3515625*getHeight()));
        g2.setColor(Color.WHITE);
        for(int i=0;i<l.get(l.size()-1).getPlayer().inv.length;i++){
            g2.drawString(l.get(l.size()-1).getPlayer().inv[i].name, getWidth()-(int) (0.25*getWidth()), (int) (((i+1)*(0.032*getHeight()))+(int) (0.3515625*getHeight())+74));
        }
        //Stats
        g2.setColor(Color.BLACK);
        g2.fillRect(5, 5, 140, 170);
        g2.setColor(Color.RED);
        g2.fillRect(10, 25, (int) ((l.get(l.size()-1).getPlayer().health/l.get(l.size()-1).getPlayer().maxhealth)*100), 20);
        g2.setColor(Color.BLUE);
        g2.fillRect(10, 45, (int) ((l.get(l.size()-1).getPlayer().mana/l.get(l.size()-1).getPlayer().maxMana)*100)-9, 20);
        g2.setColor(Color.GREEN);
        g2.fillRect(10, 65, (int) (100*l.get(l.size()-1).getPlayer().xp/(10*l.get(l.size()-1).getPlayer().lvl)), 20);
        g2.setColor(Color.WHITE);
        g2.drawString("You are in dungeon: "+Rogue.numLevels, 10, 20);
        g2.drawString("Health: "+(int) l.get(l.size()-1).getPlayer().health,10,40);
        g2.drawString("Mana: "+(int) l.get(l.size()-1).getPlayer().mana, 10, 60);
        g2.drawString("You are Level: "+l.get(l.size()-1).getPlayer().lvl, 10, 80);
        g2.drawString("Max Attack: "+l.get(l.size()-1).getPlayer().maxAtt, 10, 100);
        g2.drawString("Defence: "+l.get(l.size()-1).getPlayer().maxDefence, 10, 120);
        g2.drawString("Kills: "+l.get(l.size()-1).getPlayer().kills+" Enemies", 10, 140);
        g2.drawString("Gold: "+l.get(l.size()-1).getPlayer().gold, 10, 160);
        
        
        g2.drawImage(newGame.img, newGame.x, newGame.y, this);
        g2.drawImage(options.img, options.x, options.y, this);
        g2.drawImage(quit.img, quit.x, quit.y, this);
        g2.dispose();
    }
    /**
     * FINALY A*
     * @param e 
     * @return  
     */
    public Direction pathFind(RogueEntity e){
        Direction out;
        Astar a = new Astar();
        List<Node> path = a.findPath(new Vector2i(l.get(l.size()-1).getPlayer().x,l.get(l.size()-1).getPlayer().y), new Vector2i(e.x,e.y),l.get(l.size()-1));
        if(path==null) return Direction.STOP;
        out = pointTowards(path.get(1));
        return out;
    }
    public Direction pointTowards(Node e){
        Direction pdir=Direction.STOP;
        if(l.get(l.size()-1).getPlayer().x<e.tile.getX())pdir = Direction.LEFT;
        if(l.get(l.size()-1).getPlayer().x>=e.tile.getX())pdir = Direction.RIGHT;
        if(l.get(l.size()-1).getPlayer().y<e.tile.getY())pdir = Direction.DOWN;
        if(l.get(l.size()-1).getPlayer().y>=e.tile.getY())pdir =Direction.UP;
        return pdir;
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*64;
        offy=(getHeight()/2)-e.y*64;
        moffx=getWidth()/8+getWidth()-getWidth()/4-10-e.x*8;
        moffy=(int) (10+(0.3515625*getHeight())/2-e.y*8);
    }
}
