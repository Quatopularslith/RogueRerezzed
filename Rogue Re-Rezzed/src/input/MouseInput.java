
package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Torri
 */
public class MouseInput implements MouseListener{
    public double mx;
    public double my;
    @Override
    public void mouseClicked(MouseEvent e) {
        mx=e.getXOnScreen();
        my=e.getYOnScreen();
    }
    @Override
    public void mousePressed(MouseEvent e) {
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
