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
    // Level size is 500 by 500
    public final int numRooms = 8;
    public final int maxRoom = 10;
    public final int cols = 2;
    private final Random rand;
    private final int maxEntities=104,maxPlayers=4;
    private int currentity = 0;
    private int index;
    private final List<RogueEntity> re = new ArrayList<>();
    private final Player[] p = new Player[maxPlayers];
    public Room[] r = new Room[numRooms];
    private Player hp;//hostile player
    public int[] size = {(numRooms/cols)*maxRoom,cols*maxRoom};
    private static int[] what;
    public Level(int lvl){
        this.rand = new Random();
        what = new int[2];
        for(int i=0;i<numRooms;i++){
            what[0] = rand.nextInt(maxRoom-2)+2;
            what[1] = rand.nextInt(maxRoom-2)+2;
            r[i] = new Room(i,what,rand.nextInt(10), this);
        }
        System.out.println("Level Generated.");
    }
    public List<RogueEntity> getEntities(){
        return re;
    }
    public Player[] getPlayers(){
        return p;
    }
    public void addEntity(RogueEntity e){
        re.add(currentity, e);
        currentity++;
    }
    public void addEntities(RogueEntity[] e){
        for (RogueEntity e1 : e) {
            addEntity(e1);
        }
    }
    public Player getHostilePlayer(){
        for (Player p1 : p) {
            for (index=0; index<p.length; index++) {
                if (p1.taunt < p[index].taunt) {
                    break;
                }
            }
            if (index==p.length-1) {
                hp = p1;
                break;
            }
        }
        return hp;
    }
}
