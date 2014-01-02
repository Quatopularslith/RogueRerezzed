package dungeon;

import art.LoadArt;
import core.GameLoop;
import core.Rogue;
import entity.RogueEntity;
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
import ui.GamePlay;

/**
 * Handles everything having to do with spawning the world
 * @author Torri
 */
public class Level {
    protected final Random rand = new Random();
    protected final ArrayList<RogueEntity> re =new ArrayList<>();
    protected final ArrayList<Item> items = new ArrayList<>();
    protected Room[] rooms;
    protected ArrayList<Player> pl = new ArrayList<>();
    protected static int rows=10,cols=10;
    protected Stairway st;
    protected int nument=0;
    protected final LoadArt la = new LoadArt();
    protected LevelMode mode;
    protected LevelType type;
    public int lvl;
    /**
     * multiple of 16
     */
    public static int renderlevel;
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
        mode=mode1;
        type=type1;
        nument=0;
        int roomnum=0;
        board=new boolean[2*sx][2*sy];
        maxRoomSX=(sx/rows);
        maxRoomSY=(sy/cols);
        numRooms=(rows)*(cols);
        rooms=new Room[numRooms+4];
        //Mode Selection
        if(mode1==LevelMode.STORY){
            renderlevel=Math.round(Rogue.numLevels/5)*16;
            if(renderlevel>48){
                renderlevel=48;
            }else if(renderlevel<16 && Rogue.numLevels>3){
                renderlevel=16;
            }
        }else{
            renderlevel=render1;
        }
        this.lvl=lvl;
        if(Rogue.mm != null) Rogue.mm.ki.turn = type1 != LevelType.EVOLVED;
        //Sprite Change
        GamePlay.fsp = new Sprite("DungeonFloor");
        GamePlay.dialogue = new Sprite("Dialogue",256);
        GamePlay.floorimg=new Sprite("DungeonFloor",8);
        GamePlay.pimg=new Sprite("Player",8);
        GamePlay.stimg=new Sprite("Stairway",8);
        //Roomgen
        for(int x=0;x<sx;x+=maxRoomSX){
            for(int y=0;y<sy;y+=maxRoomSY){
                rooms[roomnum]=new Room(x,y,maxRoomSX,maxRoomSY,lvl,this);
                roomnum++;
            }
        }
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
        System.out.println(p);
        rooms[roomnum]=new Room(0,rooms[p.roomnum].area[0][0][1]-2,sx,3,lvl,this);
        rooms[roomnum+1]=new Room(rooms[p.roomnum].area[0][0][0]-2,0,sy,3,lvl,this);
        rooms[roomnum+2]=new Room(0,rooms[st.room].area[0][0][1],sx,3,lvl,this);
        rooms[roomnum+3]=new Room(rooms[st.room].area[0][0][0],0,3,sy,lvl,this);
        //Board
        for(boolean[] b1:board){
            for(boolean b:b1){
                b=false;
            }
        }
        for(Room r:rooms){
            for(int[][] a:r.area){
                for(int[] a1:a){
                    if(a1[0]>=board.length || a1[1]>=board[0].length) continue;
                    board[a1[0]][a1[1]]=true;
                }
            }
        }
        //Bosses
        MortuusTrabajos mt = new MortuusTrabajos(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(mt);
        
        Quatopularslith qt = new Quatopularslith(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(qt);
        
        //NPCs
        Trader t = new Trader(this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(t);
        
        Warrior w = new Warrior(this.rooms[rand.nextInt(rooms.length)], this);
        this.addEntity(w);
        //Non-turn-based
        if(type1==LevelType.EVOLVED){
            GameLoop.start();
        }else{
            GameLoop.pause();
        }
        //Favicon
        Image s = la.createBufferedImage("Quatopularslith"+renderlevel+".png", 64, 64);
        if(Rogue.mm!=null) Rogue.mm.setIconImage(s);
    }
    /**
     * Gets Room Array
     * @return current Rooms array
     */
    public Room[] getRooms(){
        return rooms;
    }
    /**
     * Gets a Room
     * @param roomnum number of the room
     * @return number room
     */
    public Room getRoom(int roomnum){
        return rooms[roomnum];
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
}
