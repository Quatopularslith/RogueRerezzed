package loading;

/**
 * @author Torri
 */
public class LoadThread extends Thread implements Runnable {

    boolean running = true;
    Load g = new Load();

    @Override
    public void run() {
        while (running) {
            g.tick();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }
        }
    }
}
