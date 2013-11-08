package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private final Random r = new Random();
    public final int[][] render = new int[Level.maxRoomS][2];
    public int rl=0;
    private final int[][][] area = new int[Level.maxRoomS][Level.maxRoomS][2];
    private final int rs,ra;
    public Room(int[] pos,int[] size, int minlvl, Level l){
        for(int x=0;x<size[0];x++){
            for(int y=0;y<size[1];y++){
                area[x][y][0]=x+pos[0];
                area[x][y][1]=x+pos[1];
                if(x%16==0 && y%16==0 || x==0 && y==0){
                    render[rl][0]=x+pos[0];
                    render[rl][1]=x+pos[1];
                    rl++;
                }
            }
        }
        rs = r.nextInt(Level.maxRoomS);
        ra = r.nextInt(Level.maxRoomS);
        Spawner s = new Spawner(area[rs][ra][0],area[rs][ra][1],2,minlvl,10,l,"Snake");
//        rs = r.nextInt(Level.maxRoomS);
//        ra = r.nextInt(Level.maxRoomS);
//        Spawner s1 = new Spawner(area[rs][ra][0],area[rs][ra][1],2,minlvl,10,l,"Bandit");
    }
}
