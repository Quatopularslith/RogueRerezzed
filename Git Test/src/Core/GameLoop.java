package Core;
/**
 * @author Torri
 */
public class GameLoop extends Thread implements Runnable{
    boolean running=true;
    Game g = new Game() {};
    @Override
    public void run() {
        while(running){
            g.tick();
            try{
                Thread.sleep(50);
            }catch(Exception e){
                System.err.println(e.toString());
            }
        }
    }
}