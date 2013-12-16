package core;

/**
 * @author Torri
 */
public class GameLoop{
    static Thread t = null;
    static GameThread gt = null;
    public static void start(){
        gt = new GameThread();
        gt.running=true;
        t=new Thread(gt);
        t.start();
    }
    public static void pause(){
        if(t!=null){
            try {
                gt.running=false;
                t.join();
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
