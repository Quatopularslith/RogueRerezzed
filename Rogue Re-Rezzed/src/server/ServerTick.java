package server;

import core.Rogue;

/**
 * @author Torri
 */
public class ServerTick {
    private static long ticknum = 0;
    public void tick(){
        ticknum++;
        if(Rogue.mm!=null){
            if(Rogue.mm.load!=null){
                Rogue.mm.load.update();
            }else{
                ticknum=0;
            }
        }else{
            ticknum=0;
        }
    }
    public static long getTickNum(){
        return ticknum;
    }
}
