package Level;

import Entity.Player;
import Entity.RogueEntity;

/**
 * @author Torri
 */
public class Level {
    private int maxEntities=100,maxPlayers=4;
    private int sizeX,sizeY,index;
    private RogueEntity[] re = new RogueEntity[maxEntities];
    private Player[] p = new Player[maxPlayers];
    private Player hp;
    public RogueEntity[] getEntities(){
        return re;
    }
    public Player[] getPlayers(){
        return p;
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
