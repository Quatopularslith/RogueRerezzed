/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import core.Rogue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Torri
 */
public class KeyboardInput implements KeyListener{
    boolean[] keys = new boolean[1000];
    int[] keyn = new int[6];
    public boolean[] keyBind = new boolean[6];
    /**
     * Sets up a keyboard input listener
     * @param keybinds 
     */
    public KeyboardInput(int[] keybinds){
        keyn=keybinds;
    }
    /**
     * changes settings w/o making new instance
     * @param keybinds 
     */
    public void updateKB(int[] keybinds){
        keyn=keybinds;
    }
    public void checkSettings(String[] kb){
        for(int i=0;i<kb.length;i++){
            keyn[i]=Integer.parseInt(kb[i]);
        }
    }
    private void turn(){
        for(boolean kb:keyBind){
            if(kb){
                System.out.println(kb);
                Rogue.mm.d.gp.update();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        turn();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
        for(int i=0;i<keyBind.length;i++){
            if(keyn[i]==e.getKeyCode()){
                keyBind[i]=true;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
        for(int i=0;i<keyBind.length;i++){
            if(keyn[i]==e.getKeyCode()){
                keyBind[i]=false;
            }
        }
    }
}
