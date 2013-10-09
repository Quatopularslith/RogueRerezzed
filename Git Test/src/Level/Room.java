package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private Random r = new Random();
    private int[][] walls = new int[40][2];
    private int[][] area = new int[100][2];
    private int rs;
    public Room(int quad,int[] size, int minlvl, Level l){
        if(quad==0){
            walls[0][0] = r.nextInt(l.maxRoom-size[0]);
            walls[0][1] = r.nextInt(l.maxRoom-size[1]);
            for(int i=0;i<size[0];i++){
                
            }
        }
        rs = r.nextInt(99);
        Spawner s = new Spawner(area[rs][0],area[rs][1],r.nextInt(20),minlvl,10,l,r.nextInt(2));
        rs = r.nextInt(99);
        Spawner s1 = new Spawner(area[rs][0],area[rs][1],r.nextInt(20),minlvl,10,l,r.nextInt(2));
    }
}
