
package ui;

import core.Rogue;
import dungeon.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class Status extends JPanel{
    public void update(){
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.drawString("You are on level: "+Level.numLevels, 10, 10);
        g.drawString("Your Health: "+Rogue.getLevel().getPlayer().health, 10, 30);
    }
}
