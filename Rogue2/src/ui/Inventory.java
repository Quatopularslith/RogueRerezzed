
package ui;

import entity.item.Item;
import entity.player.Player;
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
    public void update(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        ind = 0;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.drawString("INVENTORY", (getWidth()/2)-45, 20);
        for(Item i:Player.pinv){
            g.drawString(i.name, 10, (ind*20)+40);
            ind++;
        }
    }
}
