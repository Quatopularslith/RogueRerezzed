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
    private final int maxPlayers=4;
    private int currentity = 0;
    private int index=0;
    private int currplayer = 0;
    private final List<RogueEntity> re = new ArrayList<>();
    private Player[] p = new Player[maxPlayers];
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
    public void addPlayer(Player p1){
        p[currplayer]=p1;
        currplayer++;
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
        Player p1 = new Player(1,1);
        for(int i=0;i<p.length;i++){
            if(p[i]!=null){
                p1 = p[i];
            }else{
                continue;
            }
            for (index=0; index<p.length; index++) {
                System.out.println(p1.taunt);
                System.out.println(p[index].taunt);
                if (p1.taunt > p[index].taunt) {
                    break;
                }
            }
            if (index==p.length-1) {
                System.out.println(p1.taunt);
                hp.taunt = p1.taunt;
            }
        }
        if(hp==null){
            System.out.println(hp.taunt);
            System.err.println("nullnull nullnull");
            hp= new Player(50,50);
        }
        return hp;
    }
}
