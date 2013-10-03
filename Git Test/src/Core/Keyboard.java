package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Torri
 */
public class Keyboard implements KeyListener{
    public boolean[] keys = new boolean[100];
    public int[] keyn = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_S,KeyEvent.VK_A};
    public boolean up=false,down=false,left=false,right=false,spell=false,eat=false,go=false;
    public void checkSettings(int[] keys){
        keyn=keys;
    }
    public void update(){
        up=keys[keyn[0]];
        down=keys[keyn[1]];
        left=keys[keyn[2]];
        right=keys[keyn[3]];
        spell=keys[keyn[4]];
        eat=keys[keyn[5]];
    }
    public boolean turn(){
        boolean out = true;
        for(int i=0;i<6;i++){
            if(keys[keyn[i]]==true){
                out=true;
                System.out.println("Uhhhhhhh");
                break;
            }else{
                out=false;
            }
        }
        return out;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
        up=keys[keyn[0]];
        down=keys[keyn[1]];
        left=keys[keyn[2]];
        right=keys[keyn[3]];
        spell=keys[keyn[4]];
        eat=keys[keyn[5]];
        System.out.println("key pressed "+e.getKeyChar());
        go=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
        up=keys[keyn[0]];
        down=keys[keyn[1]];
        left=keys[keyn[2]];
        right=keys[keyn[3]];
        spell=keys[keyn[4]];
        eat=keys[keyn[5]];
        go=false;
    }
}
