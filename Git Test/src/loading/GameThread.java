package loading;
/**
 * @author Torri
 */
public class GameThread extends Thread implements Runnable{
    boolean running = true;
    Game g = new Game();
    @Override
    public void run(){
        while(running){
            g.tick();
            try{
                Thread.sleep(20);
            }catch(Exception e){
                System.err.println(e.toString());
            }
        }
    }
}