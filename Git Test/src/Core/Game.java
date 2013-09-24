package Core;

import Input.Keyboard;

/**
 * @author Torri
 */
public class Game extends MainMenu implements Runnable{
    private MainMenu m;
    private Keyboard k;
    private static int turnnum = 0;
    private Thread gt;
    private boolean running=false,turn=false;
    public void tick(){
        turn=k.nextTurn();
        if(turn==true){
            System.out.println(k.nextTurn());
        }
    }
    public void turn(){
        turnnum++;
        System.out.println(turnnum);
    }
    public synchronized void start(){
        m = new MainMenu(750,500);
        running = true;
        k = new Keyboard();
        m.addKeyListener(k);
        gt = new Thread(this,"Game");
        gt.start();
    }
    public synchronized void stop(){
        running = false;
        try {
            gt.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
        long lt = System.nanoTime(),now;
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        while(running==true){
            now = System.nanoTime();
            delta += (now-lt)/ns;
            while(delta >= 1){
                tick();
                delta--;
            }
        }
        stop();
    }
}
