package loading;

/**
 * @author Torri
 */
public class LoadLoop{
    static LoadThread gt = new LoadThread();
    public static void start(){
        gt.start();
    }
    public static void pause(){
        gt.running=false;
    }
}
