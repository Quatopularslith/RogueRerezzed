
package client;

import core.Rogue;
import entity.player.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Torri
 */
public class ServerKeyboardInput implements KeyListener{
    private final boolean[] keys = new boolean[1000];
    private int[] keyn = new int[6];
    public boolean[] keyBind = new boolean[6];
    public static boolean b;
    /**
     * Sets up a keyboard input listener
     * @param keybinds 
     */
    public ServerKeyboardInput(int[] keybinds){
        keyn=keybinds;
        for(boolean k: keys){
            k=false;
        }
    }
    /**
     * changes settings w/o making new instance
     * @param keybinds 
     */
    public void updateKB(int[] keybinds){
        keyn=keybinds;
    }
    /**
     * changes settings w/o making new instance
     * @param kb 
     */
    public void checkSettings(String[] kb){
        for(int i=0;i<kb.length;i++){
            keyn[i]=Integer.parseInt(kb[i]);
        }
    }
    /**
     * Sends info to the server
     */
    private void turn(){
        b = false;
        
    }
    /**
     * Receives when key is typed
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        turn();
    }
    /**
     * Receives when key is pressed
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
        for(int i=0;i<keyBind.length;i++){
            keyBind[i]=keys[keyn[i]];
        }
    }
    /**
     * Receives when key is released
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
        for(int i=0;i<keyBind.length;i++){
            keyBind[i]=keys[keyn[i]];
        }
    }
}
