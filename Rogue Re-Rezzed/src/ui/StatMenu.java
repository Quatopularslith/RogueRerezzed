

package ui;
import javax.swing.JPanel;
import core.Rogue;
import dungeon.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 *
 * @author Quatopularslith
 */
public class StatMenu extends JPanel{
    public void StatMenu(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.drawString("You were on level: "+Level.numLevels, 10, 20);
        g2.drawString("You killed "+Rogue.getLevel().getPlayer().kills+" Enemies",10,120);
        g2.drawString("Your Items were: "+Player.inv());
}
    
}
