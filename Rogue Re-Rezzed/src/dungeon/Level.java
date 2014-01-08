package dungeon;

import art.LoadArt;
import core.GameLoop;
import core.Rogue;
import entity.RogueEntity;
import entity.Spawner;
import entity.Stairway;
import entity.item.Item;
import entity.mob.MortuusTrabajos;
import entity.mob.Quatopularslith;
import entity.npc.Trader;
import entity.npc.Warrior;
import entity.player.Player;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import render.Sprite;
import render.SpriteSheet;
import ui.GamePlay;

/**
 * Handles everything having to do with spawning the world
 * @author Torri
 */
public class Level {
    protected final Random rand = new Random();
    protected final ArrayList<RogueEntity> re =new ArrayList<>();
    protected final ArrayList<Item> items = new ArrayList<>();
    protected ArrayList<Player> pl = new ArrayList<>();
    protected static int rows=10,cols=20;
    protected Stairway st;
    protected int nument=0;
    protected final LoadArt la = new LoadArt();
    protected LevelMode mode;
    protected LevelType type;
    public int lvl;
    /**
     * multiple of 16
     */
    public int renderlevel;
    /**
     * solid and non-solid objects
     */
    public boolean[][] board;
    /**
     * Maximum Room size
     */
    public int maxRoomSX=0,maxRoomSY=0;
    /**
     * Maximum Rooms
     */
    public int numRooms;
    /**
     * Creates a level
     */
    public Level(){
        generateLevel(100,100,Rogue.numLevels,LevelMode.STORY,LevelType.TURN,0);
    }
    /**
     * Creates a level
     * @param lvl
     * @param mode * @param lvl level of difficulty
     * @param type
     * @param render
     */
    public Level(int lvl,LevelMode mode,LevelType type,int render){
        generateLevel(100,100,lvl,mode,type,render);
    }
    /**
     * Creates a level
     * @param sx the X size of the new Level
     * @param sy the Y
     * @param mode
     * @param type
     * @param render
     */
    public Level(int sx, int sy,LevelMode mode,LevelType type,int render){
        generateLevel(sx,sy,Rogue.numLevels,mode,type,render);
    }
    /**
     * Creates a level
     * @param sx the X size of the new Level
     * @param sy the Y size of the new Level
     * @param lvl the level of difficulty
     * @param mode1
     * @param type1
     * @param render1
     */
    public void generateLevel(int sx,int sy,int lvl,LevelMode mode1,LevelType type1,int render1){
        GameLoop.pause();
        if(lvl==0){
            lvl=1;
        }
        Rogue.mm.gp.currTrade=null;
        Rogue.mm.gp.w=null;
        mode=mode1;
        type=type1;
        nument=0;
        board=new boolean[sx][sy];
        maxRoomSX=(sx/rows);
        maxRoomSY=(sy/cols);
        numRooms=(rows)*(cols);
        //Mode Selection
        if(mode1==LevelMode.STORY){
            renderlevel=Math.round(lvl/5)*16;
            if(renderlevel>48){
                renderlevel=48;
            }else if(renderlevel<16 && lvl>3){
                renderlevel=16;
            }
        }else{
            renderlevel=render1;
        }
        Rogue.renderlevel=renderlevel;
        this.lvl=lvl;
        if(Rogue.mm != null) Rogue.mm.ki.turn = type1 != LevelType.EVOLVED;
        //Sprite Change
        GamePlay.fsp = new Sprite(SpriteSheet.FLOOR);
        GamePlay.dialogue = new Sprite(SpriteSheet.DIALOGUE,256);
        GamePlay.floorimg=new Sprite(SpriteSheet.FLOOR,8);
        GamePlay.pimg=new Sprite(SpriteSheet.PLAYER,8);
        GamePlay.stimg=new Sprite(SpriteSheet.STAIRWAY,8);
        //Roomgen
        int area = generateRooms(sx,sy);
        //Player
        Player p=new Player(this);
        if(Rogue.getLastLevel()!=null){
            p.currinv=Rogue.getLastLevel().getPlayer().currinv;
            p.inv=Rogue.getLastLevel().getPlayer().inv;
            p.gold=Rogue.getLastLevel().getPlayer().gold;
            p.health=Rogue.getLastLevel().getPlayer().health;
            p.lvl=Rogue.getLastLevel().getPlayer().lvl;
            p.xp=Rogue.getLastLevel().getPlayer().xp;
            p.mana=Rogue.getLastLevel().getPlayer().mana;
            p.rep=Rogue.getLastLevel().getPlayer().rep;
            p.kills=Rogue.getLastLevel().getPlayer().kills;
        }
        p.updateStats();
        pl.add(p);
        //Stariway
        st = new Stairway(this);
        //Safety Rooms
        for(int i=0;i<board.length;i++){
            board[p.x][i]=true;
            if(p.x<board.length) board[p.x+1][i]=true;
            if(p.x<board.length) board[p.x+2][i]=true;
            board[i][p.y]=true;
            if(p.y<board[0].length) board[i][p.y+1]=true;
            if(p.y<board[0].length) board[i][p.y+2]=true;
        }
        for(int i=0;i<board.length;i++){
            board[st.x][i]=true;
            if(st.x<board.length) board[st.x+1][i]=true;
            if(st.x<board.length) board[st.x+2][i]=true;
            board[i][st.y]=true;
            if(st.y<board[0].length) board[i][st.y+1]=true;
            if(st.y<board[0].length) board[i][st.y+2]=true;
        }
        //population
        rePopulate(area);
        //Non-turn-based
        if(type1==LevelType.EVOLVED){
            GameLoop.start();
        }else{
            GameLoop.pause();
        }
        //Favicon
        Image s = (new Sprite(SpriteSheet.QUATOPULARSLITH)).i;
        if(Rogue.mm!=null) Rogue.mm.setIconImage(s);
    }
    public int generateRooms(int sx,int sy){
        int out=0;
        for(boolean[] b1:board){
            for(boolean b:b1){
                b=false;
            }
        }
        int rx,ry;
        for(int x=0;x<sx;x+=maxRoomSX){
            for(int y=0;y<sy;y+=maxRoomSY){
                rx=rand.nextInt(maxRoomSX)+1+x;
                ry=rand.nextInt(maxRoomSY)+1+y;
                for(int xx=0;xx<maxRoomSX;xx++){
                    for(int yy=0;yy<maxRoomSY;yy++){
                        if(xx+rx>=board.length || yy+ry>=board[0].length) continue;
                        board[xx+rx][yy+ry]=true;
                        out++;
                    }
                }
            }
        }
        return out;
    }
    public void rePopulate(int area){
        re.clear();
        int mult = rows*cols;
        if(lvl*mult<=area/4) Spawner.spawner(rand.nextInt(lvl*mult-1)+1,lvl,this);
        if(lvl*mult>area/4) Spawner.spawner(rand.nextInt(area/4),lvl,this);
        //Bosses
        MortuusTrabajos mt = new MortuusTrabajos(lvl,this);
        this.addEntity(mt);
        
        Quatopularslith qt = new Quatopularslith(lvl,this);
        this.addEntity(qt);
        //NPCs
        Trader t = new Trader(this);
        this.addEntity(t);
        
        Warrior w = new Warrior(this);
        this.addEntity(w);
    }
    public void rePopulate(){
        int area=0;
        for(boolean[] ba:board){
            for(boolean b:ba){
                if(b) area++;
            }
        }
        re.clear();
        if(lvl<=area/4) Spawner.spawner(rand.nextInt(lvl+1),lvl,this);
        if(lvl>area/4) Spawner.spawner(rand.nextInt(area/4),lvl,this);
        //Bosses
        MortuusTrabajos mt = new MortuusTrabajos(lvl,this);
        this.addEntity(mt);
        
        Quatopularslith qt = new Quatopularslith(lvl,this);
        this.addEntity(qt);
        //NPCs
        Trader t = new Trader(this);
        this.addEntity(t);
        
        Warrior w = new Warrior(this);
        this.addEntity(w);
    }
    /**
     * Gets all Entities
     * @return all Entities
     */
    public List<RogueEntity> getEntities(){
        return re;
    }
    /**
     * Gets a specific entity
     * @param uuid the universally unique identifier
     * @return the specified entity
     */
    public RogueEntity getEntity(int uuid){
        return re.get(uuid);
    }
    /**
     * Add an entity
     * @param e the entity to add
     */
    public void addEntity(RogueEntity e){
        e.uuid=nument;
        re.add(e);
        nument++;
    }
    /**
     * gets rid of an entity
     * @param e the entity to be removed 
     */
    public void removeEntity(RogueEntity e){
        re.set(e.uuid, null);
    }
    /**
     * @return player
     */
    public Player getPlayer(){
        return pl.get(0);
    }
    /**
     * gets current set of stairs
     * @return stairway of this level
     */
    public Stairway getStairWay(){
        return st;
    }
    /**
     * gets all items
     * @return 
     */
    public ArrayList<Item> getItems(){
        return items;
    }
    /**
     * adds item
     * @param i 
     */
    public void addItem(Item i){
        items.add(i);
    }
    /**
     * removes items
     * @param i 
     */
    public void removeItem(Item i){
        items.remove(i);
    }
    /**
     * Gets mode of Level
     * @return 
     */
    public LevelMode getMode(){
        return mode;
    }
    /**
     * Gets type of level
     * @return 
     */
    public LevelType getType(){
        return type;
    }
    public void addPlayer(Player p){
        pl.add(p);
    }
    public void addPlayers(Player[] p){
        pl.addAll(Arrays.asList(p));
    }
    public Player[] getPlayers(){
        return (Player[]) pl.toArray();
    }
    public void setPlayer(Player p){
        p.l = this;
        pl.set(0, p);
    }
}
