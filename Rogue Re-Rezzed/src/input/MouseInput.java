
package input;

import core.Rogue;
import dungeon.Level;
import entity.player.Player;
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
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mx=e.getXOnScreen();
        my=e.getYOnScreen();
        System.out.println(mx +" "+ my);
        if(Rogue.mm.mmp.isVisible()){
//            if(mx < 750 && my > 950 && mx < 450 && my > 500){
            if(mx > 480 && my > 300 && mx < 680 && my < 350){
                Player.pinv = null;
                Player.xplevels = 1;
                Player.xp = 0;
                Player.kills = 0;
                Level.numLevels=0;
                Rogue.setLevel(new Level(1));
                Rogue.mm.d=new ui.Display();
                Rogue.mm.add(Rogue.mm.d);
                Rogue.mm.d.setSize(750, 500);
                Rogue.mm.d.optionsD.addActionListener(Rogue.mm.bi);
                Rogue.mm.d.save.addActionListener(Rogue.mm.bi);
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.omp.setVisible(false);
                Rogue.mm.d.setVisible(true);
            }
            if(Rogue.mm.omp.isVisible()){
                
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
