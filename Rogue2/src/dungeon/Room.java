
package dungeon;

import entity.Spawner;
import java.util.Random;

/**
 *
 * @author Torri
 */
public class Room {
    private final Random rand = new Random();
    public int[][][] area;
    public Level l;
    /**
     * Creates a room
     * @param x1 X co-ordinate of the room's top left corner
     * @param y1 Y co-ordinate of the room's top left corner
     * @param sizeX X size of the room
     * @param sizeY Y size of the room
     * @param lvl Level of the room
     * @param l1 level of which is contained in
     */
    public Room(int x1,int y1,int sizeX,int sizeY,int lvl,Level l1){
        l=l1;
        x1=rand.nextInt(l.maxRoomSX)+1+x1;
        y1=rand.nextInt(l.maxRoomSY)+1+y1;
        area = new int[sizeX][sizeY][2];
        for(int x=0;x<sizeX;x++){
            for(int y=0;y<sizeY;y++){
                area[x][y][0]=x+x1;
                area[x][y][1]=y+y1;
            }
        }
        Spawner.spawner(rand.nextInt(5),lvl,this);
    }
}
