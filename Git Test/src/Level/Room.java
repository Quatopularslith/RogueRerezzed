package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private final Random r = new Random();
    public int[][][] area = new int[Level.maxRoomS][Level.maxRoomS][2];
    public int[][][] walls;
    private int wallx=0,wally=0;
    private int rs,ra;
    public Room(int[] pos,int[] size, int minlvl, Level l){
        System.out.println(size[1]+" "+size[1]);
        walls=new int[size[0]][size[1]][2];
        for(int x=0;x<size[0];x++){
            for(int y=0;y<size[1];y++){
                area[x][y][0]=x+pos[0];
                area[x][y][1]=y+pos[1];
//                if(x==0 || x==size[0]-1){
//                    walls[wallx][wally][0]=wallx+pos[0];
//                    walls[wallx][wally][1]=wally+pos[1];
//                    wallx++;
//                }
//                if(y==0 || y==size[1]-1){
//                    walls[wallx][wally][0]=wallx+pos[0];
//                    walls[wallx][wally][1]=wally+pos[1];
//                    wally++;
//                }
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
