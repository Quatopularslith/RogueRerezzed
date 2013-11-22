
package ui;

import core.Rogue;
import entity.item.Item;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class Inventory extends JPanel{
    int ind = 0;
    public Inventory() {
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        ind = 0;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        for(Item i:Rogue.getLevel().getPlayer().inv){
            g.drawString(i.name, 10, (ind*20)+40);
            ind++;
        }
    }
}
