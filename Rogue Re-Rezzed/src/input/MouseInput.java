package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Torri
 */
public class MouseInput implements MouseListener {

    public int mx;
    public int my;
    public List<MButton> mb = new ArrayList<>();
    boolean go = false;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX() + 5;
        my = e.getY() - 30;
        for (int i = 0; i < mb.size(); i++) {
            if (mb.get(i).update(mx, my)) {
                break;
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
