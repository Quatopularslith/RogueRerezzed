
package dungeon;

import entity.RogueEntity;
import entity.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Torri
 */
public class Level {
    private List<RogueEntity> re;
    private Room[] rooms;
    private Player p;
    public static int renderlevel;
    private static int rows=10,cols=10;
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
     * @param lvl level of difficulty
     */
    public Level(int lvl){
        this(100,100,lvl);
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
        int roomnum=0;
        maxRoomSX=(sx/rows);
        maxRoomSY=(sy/cols);
        numRooms=(rows)*(cols);
        System.out.println(numRooms);
        re=new ArrayList<>();
        rooms=new Room[numRooms];
        for(int x=0;x<sx-1;x++){
            for(int y=0;y<sy-1;y++){
                if(((int) x*(1/maxRoomSX)==0 || (int) y*(1/maxRoomSY)==0) && roomnum<numRooms){
                    rooms[roomnum]=new Room(x*(1/maxRoomSX),y*(1/maxRoomSY),maxRoomSX,maxRoomSY,lvl,this);
                    roomnum++;
                }
            }
        }
        p=new Player(this);
        this.addEntity(p);
        renderlevel=Math.round(numLevels/16)*16;
        if(renderlevel==0){
            renderlevel+=16;
        }
        System.out.println(re.size());
        numLevels++;
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
        re.add(e);
    }
    /**
     * gets rid of an entity
     * @param e the entity to be removed 
     */
    public void removeEntity(RogueEntity e){
        re.remove(e.uuid);
    }
    /**
     * @return player
     */
    public Player getPlayer(){
        return p;
    }
}
