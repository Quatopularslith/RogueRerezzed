package Core;
/**
 * @author Torri
 */
public abstract class Game {
    static int turnnum = 0;
    public void tick(){
    }
    public void turn(){
        turnnum++;
        System.out.println(turnnum);
    }
}
