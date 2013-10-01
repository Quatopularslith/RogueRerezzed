package loading;
/**
 * @author Torri
 */
public class Game {
    private static long ticknum = 0;
    public void tick(){
        ticknum++;
        System.out.println(ticknum);
    }
    public static long getTickNum(){
        return ticknum;
    }
}
