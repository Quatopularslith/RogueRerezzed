package Entity;
/**
 * @author Torri
 */
public class Player extends RogueEntity{
    public int taunt;
    public Player(int spawnX, int spawnY){
        health = 100;
        taunt = 3;
        armour = 0;
        dir = 0;
        inv = new int[10];
        x=spawnX;
        y=spawnY;
    }
    public void turn(){
        
    }
}