package Entity;
/**
 * @author Torri
 */
public class Player extends RogueEntity{
    public int taunt;
    public int[] inventory = new int[10];// may need bigger inv size in the futrure
    public Player(int spawnX, int spawnY){
        health = 100;
        taunt = 3;
        armour = 0;
        dir = 0;
        x=spawnX;
        y=spawnY;
    }
    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void turn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}