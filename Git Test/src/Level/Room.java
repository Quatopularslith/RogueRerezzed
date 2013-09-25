package Level;

import java.util.Random;

/**
 * @author Torri
 */
public class Room{
    private Random r = new Random();
    private int[] size;
    private int[] pos;
    private int[][] entpos;
    public Room(int[] posin,int[] sizein,int[][] entrancepos, int minlvl, int maxlvl){
        size=sizein;
        pos=posin;
        entpos=entrancepos;
        
    }
}
