package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Torri
 */
public class Keyboard implements KeyListener{
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
            System.out.println("YOU GOT AN ERROR! HAHAHAHHAHAHHAHA!");
        }
        return g;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
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