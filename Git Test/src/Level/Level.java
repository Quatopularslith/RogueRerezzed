package Level;

import Entity.Player;
import Entity.RogueEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Torri
 */
public class Level {
    public static final int sizex=100,sizey=100, numRooms = 25,maxRoomS = 16,cols = 2,kx=(int) (sizex/Math.sqrt(numRooms)),ky=(int) (sizey/Math.sqrt(numRooms));
    private final Random rand;
    private int currentity = 0;
    private final List<RogueEntity> re = new ArrayList<>();
    private Player p;
    public Room[] r = new Room[numRooms];
    public int[] size = {(numRooms/cols)*maxRoomS,cols*maxRoomS};
    private static int[] roomSize,roomPos;
    public Level(int lvl){
        p = new Player(10,10);
        this.addEntity(p);
        this.rand = new Random();
        roomSize = new int[2];
        roomPos = new int[2];
        for(int i=0;i<numRooms;i++){
            roomSize[0] = rand.nextInt(maxRoomS-3)+2;
            roomSize[1] = rand.nextInt(maxRoomS-3)+2;
            roomPos[0] = rand.nextInt(kx-maxRoomS)+kx*i;
            roomPos[1] = rand.nextInt(ky-maxRoomS)+ky*i;
            r[i] = new Room(roomPos,roomSize,rand.nextInt(10), this);
        }
        System.out.println("Level Generated.");
    }
    public List<RogueEntity> getEntities(){
        return re;
    }
    public void addEntity(RogueEntity e){
        re.add(currentity, e);
        e.uuid=currentity;
        currentity++;
    }
    public void addEntities(RogueEntity[] e){
        for (RogueEntity e1 : e) {
            addEntity(e1);
        }
    }
    public Player getHostilePlayer(){
        return p;
    }
    public Room[] getRooms(){
        return r;
    }
}