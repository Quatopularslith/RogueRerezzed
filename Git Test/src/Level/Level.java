package Level;

import Entity.Player;
import Entity.RogueEntity;

/**
 * @author Torri
 */
public class Level {
    // Level size is 500 by 500
    private int maxEntities=100,maxPlayers=4, index;
    private RogueEntity[] re = new RogueEntity[maxEntities];
    private Player[] p = new Player[maxPlayers];
    private Player hp;
    private int currentity = 0;
    public Level(int lvl){
        
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
