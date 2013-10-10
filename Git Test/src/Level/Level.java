package Level;

import Entity.Player;
import Entity.RogueEntity;
import java.util.Random;

/**
 * @author Torri
 */
public class Level {
    // Level size is 500 by 500
    public int numRooms = 8;
    public int maxRoom = 10;
    public int cols = 2;
    private Random rand = new Random();
    private int maxEntities=100,maxPlayers=4, currentity = 0, index;
    private RogueEntity[] re = new RogueEntity[maxEntities];
    private Player[] p = new Player[maxPlayers];
    private Room[] r = new Room[numRooms];
    private Player hp;//hostile player
    public int[] size = {(numRooms/cols)*maxRoom,cols*maxRoom};
    private int[] what;
    public Level(int lvl){
        for(int i=0;i<numRooms;i++){
            //what = {rand.nextInt(maxRoom),rand.nextInt(maxRoom)};
             r[i] = new Room(i,what,rand.nextInt(10), this);
        }
    }
    public RogueEntity[] getEntities(){
        return re;
    }
    public Player[] getPlayers(){
        return p;
    }
    public void addEntity(RogueEntity e){
        re[currentity]=e;
    }
    public void addEntities(RogueEntity[] e){
        for(int i=0;i<e.length;i++){
            addEntity(e[i]);
        }
    }
    public Player getHostilePlayer(){
        for(int i=0;i<p.length;i++){
            for(index=0;index<p.length;index++){
                if(p[i].taunt<p[index].taunt){
                    break;
                }
            }
            if(index==p.length-1){
                hp=p[i];
                break;
            }
        }
        return hp;
    }
}
