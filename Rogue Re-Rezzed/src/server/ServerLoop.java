package server;

/**
 * @author Torri
 */
public class ServerLoop{
    static ServerThread gt = new ServerThread();
    public static void start(){
        gt.start();
    }
    public static void pause(){
        gt.running=false;
    }
}
