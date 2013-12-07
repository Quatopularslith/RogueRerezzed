
package input;

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
        boolean b = false;
        System.out.println(mx+" "+my);
        for(int i=0;i<mb.size();i++){
            for(int j=0;j<mb.size();j++){
                if(j!=i && i!=0 && mb.get(i).equals(mb.get(j))){
                    mb.remove(i);
                    b=true;
                    break;
                }
            }
            if(b) continue;
            mb.get(i).update(mx, my);
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