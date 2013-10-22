package loading;
/**
 * @author Torri
 */
public class Game {
    private static long ticknum = 0;
    public void tick(){
        ticknum++;
    }
    public static long getTickNum(){
        return ticknum;
    }
}
