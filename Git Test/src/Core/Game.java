package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Torri
 */
public class Game extends MainMenu implements Runnable, KeyListener{
    private MainMenu m;
    private static int turnnum = 0;
    private Thread gt;
    private boolean running=false,turn=false;
    public void tick(){
        turn=nextTurn();
        if(turn==true){
            System.out.println(nextTurn());
        }
    }
    public void turn(){
        turnnum++;
        System.out.println(turnnum);
    }
    public synchronized void start(int x, int y){
        m = new MainMenu(x,y);
        running = true;
        m.addKeyListener(this);
        gt = new Thread(this,"Game");
        gt.start();
    }
    public synchronized void stop(){
        running = false;
        try {
            gt.join();
        } catch (InterruptedException ex) {
            System.out.println("LOLFISHES");
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
    public int upn=KeyEvent.VK_UP,downn=KeyEvent.VK_DOWN,leftn=KeyEvent.VK_LEFT,rightn=KeyEvent.VK_RIGHT,spelln=KeyEvent.VK_Q,eatn=KeyEvent.VK_E,out=0;// 38 = up, 37 = left, 39 = right, 40 = down
    public boolean up=false,down=false,left=false,right=false,spell=false,eat=false;
    public boolean[] keys = new boolean[150];
    private boolean g=true;
    public boolean nextTurn() {
        try{
            up = keys[upn];
            down = keys[downn];
            left = keys[leftn];
            right = keys[rightn];
            spell = keys[spelln];
            eat = keys[eatn];
            if(up==true || down==true || left==true || right==true || spell==true || eat==true){
                g=true;
            }else{
                g=false;
            }
        }catch(Exception e){
            System.out.println("YOU GOT AN ERROR! HAHAHAHHAHAHHAHA!"+e.getMessage());
        }
        return g;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.err.println(e.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
