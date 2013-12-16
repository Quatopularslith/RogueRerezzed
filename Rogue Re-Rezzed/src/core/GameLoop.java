package core;

/**
 * @author Torri
 */
public class GameLoop{
    static GameThread gt = new GameThread();
    public static void start(){
        gt.start();
    }
    public static void pause(){
        gt.running=false;
    }
}
