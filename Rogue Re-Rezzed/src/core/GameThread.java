package core;

/**
 * @author Torri
 */
public class GameThread implements Runnable{
    boolean running = true;
    GameTick g = new GameTick();
    long start = System.currentTimeMillis();
    @Override
    public void run(){
        while(running){
//            if((System.currentTimeMillis()-start)%150==0){
//                g.tick();
//            }
            try{
                Thread.sleep(150);
                g.tick();
            }catch (Exception e){}
        }
    }
}