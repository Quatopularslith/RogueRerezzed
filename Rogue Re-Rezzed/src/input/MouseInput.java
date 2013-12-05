
package input;

import core.Rogue;
import dungeon.Level;
import entity.player.Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Torri
 */
public class MouseInput implements MouseListener{
    public int mx;
    public int my;
    public List<MButton> mb = new ArrayList<>();
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mx=e.getX();
        my=e.getY();
        System.out.println(mx +" "+ my);
        for(MButton m:mb){
            m.update(mx, my);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}