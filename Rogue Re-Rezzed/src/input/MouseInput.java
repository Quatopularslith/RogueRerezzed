/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ui.Menu;

/**
 *
 * @author Torri
 */
public class MouseInput implements MouseListener{
    boolean onscreen=false;
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(((e.getX()-Menu.x)-(Menu.sx/2))+" "+((e.getY()-Menu.y)-(Menu.sy/2)));
//        if(RenderPanel.itempickup && onscreen){
//            
//        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        onscreen=true;
    }
    @Override
    public void mouseExited(MouseEvent e) {
        onscreen=false;
    }
}
