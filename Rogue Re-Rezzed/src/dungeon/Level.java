package dungeon;

import entity.RogueEntity;
import entity.Stairway;
import entity.item.Item;
import entity.mob.MortuusTrabajos;
import entity.mob.Quatopularslith;
import entity.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import render.Sprite;
import ui.GamePlay;

/**
 * Handles everything having to do with spawning the world
 * @author Torri
 */
public class Level {
    private final Random rand = new Random();
    private final ArrayList<RogueEntity> re =new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private Room[] rooms;
    private Player p;
    private static int rows=10,cols=10;
    private Stairway st;
    private int nument=0;
    /**
     * multiple of 16
     */
    public static int renderlevel;
    /**
     * solid and non-solid objects
     */
    public boolean[][] board;
    /**
     * Number of levels
     */
    public static int numLevels;
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
        this(100,100,numLevels);
    }
    /**
     * Creates a level
     * @param lvl level of difficulty
     */
    public Level(int lvl){
        this(100,150,lvl);
    }
    /**
     * Creates a level
     * @param sx the X size of the new Level
     * @param sy the Y size of the new Level
     */
    public Level(int sx, int sy){
        this(sx,sy,numLevels);
    }
    /**
     * Creates a level
     * @param sx the X size of the new Level
     * @param sy the Y size of the new Level
     * @param lvl the level of difficulty
     */
    public Level(int sx,int sy,int lvl){
        Player.dead=false;
        numLevels++;
        nument=0;
        int roomnum=0;
        board=new boolean[2*sx][2*sy];
        maxRoomSX=(sx/rows);
        maxRoomSY=(sy/cols);
        numRooms=(rows)*(cols);
        rooms=new Room[numRooms+3];
        renderlevel=Math.round(numLevels/5)*16;
        if(renderlevel>48){
            renderlevel=48;
        }else if(renderlevel<16 && numLevels>3){
            renderlevel=16;
        }
        GamePlay.fsp = new Sprite("DungeonFloor");
        GamePlay.dialogue = new Sprite("Dialogue",144);
        GamePlay.floorimg=new Sprite("DungeonFloor",8);
        GamePlay.pimg=new Sprite("Player",8);
        GamePlay.stimg=new Sprite("Stairway",8);
        for(int x=0;x<sx;x+=maxRoomSX){
            for(int y=0;y<sy;y+=maxRoomSY){
                rooms[roomnum]=new Room(x,y,maxRoomSX,maxRoomSY,lvl,this);
                roomnum++;
            }
        }
        st = new Stairway(this);
        rooms[roomnum]=new Room(rooms[0].area[0][0][0]-2,rooms[0].area[0][0][1]-2,sx,3,lvl,this);
        rooms[roomnum+1]=new Room(0,rooms[st.room].area[0][0][1],sx,3,lvl,this);
        rooms[roomnum+2]=new Room(rooms[st.room].area[0][0][0],0,3,sy,lvl,this);
        p=new Player(this);
        for(boolean[] b1:board){
            for(boolean b:b1){
                b=false;
            }
        }
        for(Room r:rooms){
            for(int[][] a:r.area){
                for(int[] a1:a){
                    board[a1[0]][a1[1]]=true;
                }
            }
        }
               
        MortuusTrabajos mt = new MortuusTrabajos(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(mt);
        
        Quatopularslith qt = new Quatopularslith(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(qt);
        
        p.updateStats();
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
        return p;
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
}
