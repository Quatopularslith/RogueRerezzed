package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private final Random r = new Random();
    public int[][][] area = new int[Level.maxRoomS][Level.maxRoomS][2];
    private int rs,ra;
    public Room(int[] pos,int[] size, int minlvl, Level l){
        for(int x=0;x<size[0];x++){
            for(int y=0;y<size[1];y++){
                area[x][y][0]=x+pos[0];
                area[x][y][1]=y+pos[1];
            }
        }
        rs = r.nextInt(Level.maxRoomS);
        ra = r.nextInt(Level.maxRoomS);
        Spawner s = new Spawner(area[rs][ra][0],area[rs][ra][1],2,minlvl,10,l,"Snake");
        rs = r.nextInt(Level.maxRoomS);
        ra = r.nextInt(Level.maxRoomS);
        Spawner s1 = new Spawner(area[rs][ra][0],area[rs][ra][1],2,minlvl,10,l,"Bandit");
    }
}
