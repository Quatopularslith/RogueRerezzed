
package input;

import core.Rogue;
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
        for(int i=0;i<mb.size();i++){
            if(mb.get(i).visible || mb.get(i).parent.isVisible()){
                mb.get(i).update(mx+5, my-30);
            }
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