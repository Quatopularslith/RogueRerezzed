package core;

/**
 * @author Torri
 */
public class GameLoop {

    static GameThread gt = null;
    static Thread t = null;

    public static void start() {
        if (gt == null || !gt.running) {
            gt = new GameThread();
            gt.running = true;
            t = new Thread(gt);
            t.start();
            Rogue.mm.gp.update();
        }
    }

    public static void pause() {
        if (gt != null) {
            gt.running = false;
            t.interrupt();
        }
    }

    public static boolean isRunning() {
        boolean is = false;
        if (gt != null) {
            is = gt.running;
        }
        return is;
    }
}
