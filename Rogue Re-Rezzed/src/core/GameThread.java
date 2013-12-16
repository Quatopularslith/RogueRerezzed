package core;

/**
 * @author Torri
 */
public class GameThread extends Thread implements Runnable{
    boolean running = true;
    GameTick g = new GameTick();
    @Override
    public void run(){
        while(running){
            g.tick();
            try{
                Thread.sleep(30);
            }catch(InterruptedException e){
                System.err.println(e.toString());
            }
        }
    }
}