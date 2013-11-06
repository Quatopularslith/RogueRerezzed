package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private final Random r = new Random();
    private final int[][] render = new int[100][2];
    public final int[][] area = new int[100][2];
    private int rs;
    public Room(int[] pos,int[] size, int minlvl, Level l){
        rs = r.nextInt(99);
        Spawner s = new Spawner(area[rs][0],area[rs][1],2,minlvl,10,l,"Snake");
        rs = r.nextInt(99);
        Spawner s1 = new Spawner(area[rs][0],area[rs][1],2,minlvl,10,l,"Bandit");
    }
}
