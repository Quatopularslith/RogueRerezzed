package core;

/**
 * @author Torri
 */
public class GameThread implements Runnable{
    boolean running = true;
    long start = System.currentTimeMillis();
    long now;
    @Override
    public void run(){
        while(running){
            now = System.currentTimeMillis();
            if((now-start)%50==0){
                tick();
            }
        }
    }
    private void tick(){
        if(Rogue.getCurrentLevel()!=null && Rogue.mm!=null){
            Rogue.mm.gp.update();
        }
    }
}