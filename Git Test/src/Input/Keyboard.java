package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Torri
 */
public class Keyboard implements KeyListener{
    public int upn=38,downn=40,leftn=37,rightn=39,spelln=81,eatn=69;// 38 = up, 37 = left, 39 = right, 40 = down
    public boolean up=false,down=false,left=false,right=false,spell=false,eat=false;
    public boolean[] keys = new boolean[120];
    private boolean g=false;
    public boolean next() {
        up = keys[upn];
        down = keys[downn];
        left = keys[leftn];
        right = keys[rightn];
        spell = keys[spelln];
        eat = keys[eatn];
        if(up==true || down==true || left==true || right==true || spell==true || eat==true){
            g=true;
        }
        return g;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}