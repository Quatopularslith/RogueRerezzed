package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private final Random r = new Random();
    private final int[][] walls = new int[40][2];
    public final int[][] area = new int[100][2];
    private int rs;
    public Room(int num,int[] size, int minlvl, Level l){
        if(num==0){
            area[0][0] = r.nextInt(l.maxRoom-size[0]);
            area[0][1] = r.nextInt(l.maxRoom-size[1]);
            for(int i=0;i<size[0];i++){
                for(int j=0;j<size[1];j++){
                }
            }
        }
        rs = r.nextInt(99);
        Spawner s = new Spawner(area[rs][0],area[rs][1],2,minlvl,10,l,"Snake");
        rs = r.nextInt(99);
        Spawner s1 = new Spawner(area[rs][0],area[rs][1],2,minlvl,10,l,"Bandit");
    }
}
