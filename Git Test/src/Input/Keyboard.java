package Input;

import Core.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Torri
 */
public class Keyboard extends Game implements KeyListener{
    Game g = new Game() {};
    public int[] control = {38,37,39,40};// 38 = up, 37 = left, 39 = right, 40 = down
    public boolean[] controls = new boolean[6];
    @Override
    public void tick() {
        for(int i=0;i<6;i++){
            if(controls[i]=true){
                g.turn();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        for(int i=0;i<6;i++){
            if(control[i]==e.getKeyCode()){
                controls[i]=true;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        for(int i=0;i<6;i++){
            if(control[i]==e.getKeyCode()){
                controls[i]=false;
            }
        }
    }
}