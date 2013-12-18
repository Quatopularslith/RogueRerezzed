package core;

/**
 * @author Torri
 */
public class GameThread implements Runnable{
    boolean running = true;
    GameTick g = new GameTick();
    long start = System.currentTimeMillis();
    long now;
    @Override
    public void run(){
        while(running){
            now=System.currentTimeMillis()-start;
            if(now%300==0) g.tick();
        }
    }
}