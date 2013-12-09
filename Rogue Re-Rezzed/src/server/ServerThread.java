package server;

/**
 * @author Torri
 */
public class ServerThread extends Thread implements Runnable{
    boolean running = true;
    ServerTick g = new ServerTick();
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