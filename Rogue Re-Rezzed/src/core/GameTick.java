package core;

/**
 * @author Torri
 */
public class GameTick {
    private static long ticknum = 0;
    public void tick(){
        ticknum++;
        if(Rogue.getCurrentLevel()!=null && Rogue.mm.gp!=null && Rogue.numLevels>0){
            Rogue.mm.gp.update();
        }else{
            ticknum=0;
        }
    }
    public static long getTickNum(){
        return ticknum;
    }
}
