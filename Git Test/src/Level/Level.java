package Level;

import Entity.Player;
import Entity.RogueEntity;
import java.util.Random;

/**
 * @author Torri
 */
public class Level {
    // Level size is 500 by 500
    private Random rand = new Random();
    private int maxEntities=100,maxPlayers=4, currentity = 0, index;
    private RogueEntity[] re = new RogueEntity[maxEntities];
    private Player[] p = new Player[maxPlayers];
    private Room[] r = new Room[4];
    private Player hp;
    public int size = 500;
    public Level(int lvl){
        for(int i=0;i<4;i++){
             r[i] = new Room(i,{rand.nextInt(10),rand.nextInt(10)},rand.nextInt(10), this);
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
